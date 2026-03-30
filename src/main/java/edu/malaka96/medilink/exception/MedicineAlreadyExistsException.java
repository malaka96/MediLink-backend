package edu.malaka96.medilink.exception;

public class MedicineAlreadyExistsException extends RuntimeException {
    public MedicineAlreadyExistsException(String message) {
        super(message);
    }
}