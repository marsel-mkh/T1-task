package com.t1.marselmkh.dto;

import com.t1.marselmkh.entity.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientProductEventDto {
     String clientId;
     String productId;
     Status status;
     BigDecimal loanAmount;
     Double interestRate;
     Integer monthCount;

}
