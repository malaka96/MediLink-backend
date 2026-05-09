package edu.malaka96.medilink.exception;

public class PharmacyNotFoundForUserException extends RuntimeException {
    public PharmacyNotFoundForUserException(String message) {
        super(message);
    }
}
