package br.com.macorin.spark.infra.exceptions;

public class FieldValidationException extends RuntimeException {
    
    /**
     *
     */
    private static final long serialVersionUID = -7437788043497534852L;

    public FieldValidationException(String message) {
        super(message);
    }
}
