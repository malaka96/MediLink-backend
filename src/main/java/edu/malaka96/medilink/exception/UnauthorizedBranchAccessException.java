package edu.malaka96.medilink.exception;

public class UnauthorizedBranchAccessException extends RuntimeException {
    public UnauthorizedBranchAccessException(String message) {
        super(message);
    }
}