package com.zuhlke.bg.camp.api.exception;

public class InvalidCriteriaException extends RuntimeException {
    public InvalidCriteriaException(String criteria) {
        super("The provided criteria " + criteria + " is not valid!");
    }
}
