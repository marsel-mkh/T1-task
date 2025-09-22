package com.t1.marselmkh.exception;

public class ClientCreditIsExpiredException extends RuntimeException {
    public ClientCreditIsExpiredException(String message) {
        super(message);
    }
}
