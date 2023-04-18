package be.technifutur.java.airport.exceptions;

public class FormValidationException extends RuntimeException {
    public FormValidationException(String message) {
        super(message);
    }
}