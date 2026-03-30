package edu.malaka96.medilink.exception;

public class PharmacyNotFoundException extends RuntimeException {
    public PharmacyNotFoundException(String message) {
        super(message);
    }
}