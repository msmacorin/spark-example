package br.com.macorin.spark.infra.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    /**
     *
     */
    private static final long serialVersionUID = -2668041775023439576L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
