package br.com.macorin.spark.infra.exceptions;

public class InvalidArgumentException extends RuntimeException {
    
    /**
     *
     */
    private static final long serialVersionUID = 4947590214322146023L;

    public InvalidArgumentException(String message) {
        super(message);
    }
}
