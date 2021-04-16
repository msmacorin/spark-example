package br.com.macorin.spark.infra.exceptions;

public class OpenUrlException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 92968873516390459L;

    public OpenUrlException(String message) {
        super(message);
    }
    
    public OpenUrlException(Throwable throwable) {
        super(throwable);
    }
}
