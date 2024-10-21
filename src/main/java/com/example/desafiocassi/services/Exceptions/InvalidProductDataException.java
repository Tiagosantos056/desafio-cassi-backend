package com.example.desafiocassi.services.Exceptions;

import java.io.Serial;

public class InvalidProductDataException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;


    public static class InvalidProductDataExceptionOne extends Throwable {
        public InvalidProductDataExceptionOne(String s) {
        }
    }
}
