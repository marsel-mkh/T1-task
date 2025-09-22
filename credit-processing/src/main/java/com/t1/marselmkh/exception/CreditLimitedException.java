package com.t1.marselmkh.exception;

public class CreditLimitedException extends RuntimeException {
    public CreditLimitedException(String message) {
        super(message);
    }
}
