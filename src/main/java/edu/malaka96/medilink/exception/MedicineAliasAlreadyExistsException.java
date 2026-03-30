package edu.malaka96.medilink.exception;

public class MedicineAliasAlreadyExistsException extends RuntimeException {
    public MedicineAliasAlreadyExistsException(String message) {
        super(message);
    }
}