package com.t1.marselmkh.service;

import com.t1.marselmkh.dto.ClientProductEventDto;
import com.t1.marselmkh.dto.ClientInfoDto;
import com.t1.marselmkh.entity.PaymentRegistry;
import com.t1.marselmkh.entity.ProductRegistry;
import com.t1.marselmkh.exception.ClientCreditIsExpiredException;
import com.t1.marselmkh.exception.ClientNotFoundException;
import com.t1.marselmkh.exception.CreditLimitedException;
import com.t1.marselmkh.mapper.ProductRegistryMapper;
import com.t1.marselmkh.repository.PaymentRegistryRepository;
import com.t1.marselmkh.repository.ProductRegistryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditProcessingService {
    private final ProductRegistryRepository productRegistryRepository;
    private final PaymentRegistryRepository paymentRegistryRepository;
    private final ProductRegistryMapper productRegistryMapper;
    private final RestTemplate restTemplate;

    @Value("${credit.limit}")
    private BigDecimal creditLimit;

    @Value("${ms1.getUrl}")
    private String ms1GetUrl;

    @Transactional
    public void processCredit(ClientProductEventDto clientProductEventDto) {
        ClientInfoDto client = getClient(clientProductEventDto);
        checkCreditLimitAndOverdue(clientProductEventDto);

        ProductRegistry productRegistry = productRegistryMapper.toEntity(clientProductEventDto);
        productRegistry = productRegistryRepository.save(productRegistry);


        List<PaymentRegistry> paymentRegistries = generatePaymentSchedule(clientProductEventDto.getLoanAmount(),
                clientProductEventDto.getInterestRate(),
                clientProductEventDto.getMonthCount(),
                productRegistry.getId());

        paymentRegistryRepository.saveAll(paymentRegistries);
    }

    private void checkCreditLimitAndOverdue(ClientProductEventDto clientProductEventDto) {
        List<ProductRegistry> credits =
                productRegistryRepository.findAllByClientId(clientProductEventDto.getClientId());
        List<Long> productIds = credits.stream()
                .map(ProductRegistry::getId)
                .toList();

        BigDecimal totalSumOfCredits = BigDecimal.ZERO;
        if (!productIds.isEmpty()) {
            totalSumOfCredits = paymentRegistryRepository.sumOutstandingByProductIds(productIds);
        }

        BigDecimal newCreditAmount = clientProductEventDto.getLoanAmount();
        if (totalSumOfCredits.add(newCreditAmount).compareTo(creditLimit) > 0) {
            throw new CreditLimitedException("Credit limit exceeded for client id: " + clientProductEventDto.getClientId());
        }

        boolean hasExpiredCredits =
                paymentRegistryRepository.existsOverduePayments(productIds, LocalDate.now());

        if (hasExpiredCredits) {
            throw new ClientCreditIsExpiredException("Client has overdue payments, id: " + clientProductEventDto.getClientId());
        }
    }


    private ClientInfoDto getClient(ClientProductEventDto clientProductEventDto) {
        String url = ms1GetUrl + clientProductEventDto.getClientId();
        ClientInfoDto client = restTemplate.getForObject(url, ClientInfoDto.class);

        if (client == null) {
            throw new ClientNotFoundException("Client not found with id: " + clientProductEventDto.getClientId());
        }

        return client;
    }

    private List<PaymentRegistry> generatePaymentSchedule(
            BigDecimal loanAmount,
            double annualInterestRate,
            int months,
            long productRegistryId) {
        List<PaymentRegistry> schedule = new ArrayList<>();

        BigDecimal monthlyRate = BigDecimal.valueOf(annualInterestRate)
                .divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);

        BigDecimal onePlusI = BigDecimal.ONE.add(monthlyRate);
        BigDecimal pow = onePlusI.pow(months);
        BigDecimal annuityCoefficient = monthlyRate.multiply(pow)
                .divide(pow.subtract(BigDecimal.ONE), 10, RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = loanAmount.multiply(annuityCoefficient)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal remainingDebt = loanAmount;

        for (int m = 1; m <= months; m++) {
            // проценты за месяц = остаток * i
            BigDecimal interest = remainingDebt.multiply(monthlyRate)
                    .setScale(2, RoundingMode.HALF_UP);

            // основная часть платежа = A - проценты
            BigDecimal principal = monthlyPayment.subtract(interest)
                    .setScale(2, RoundingMode.HALF_UP);

            // уменьшаем остаток долга
            remainingDebt = remainingDebt.subtract(principal)
                    .setScale(2, RoundingMode.HALF_UP);

            // создаём запись о платеже
            PaymentRegistry payment = new PaymentRegistry();
            payment.setProductRegistryId(productRegistryId);
            payment.setPaymendDate(LocalDate.now().plusMonths(m));
            payment.setAmount(monthlyPayment);
            payment.setInterestRateAmount(interest);
            payment.setDebtAmount(remainingDebt.max(BigDecimal.ZERO));
            payment.setExpired(false);
            payment.setPaymentExpirationDate(LocalDate.now().plusMonths(m));

            schedule.add(payment);
        }
        return schedule;
    }
}
