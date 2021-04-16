package br.com.macorin.spark.infra.exceptions;

public class RequiredArgumentException extends RuntimeException {
    
    /**
     *
     */
    private static final long serialVersionUID = -2539882342073065873L;

    public RequiredArgumentException(String message) {
        super(message);
    }
}
