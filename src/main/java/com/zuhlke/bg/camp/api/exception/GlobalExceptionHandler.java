package com.zuhlke.bg.camp.api.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidCriteriaException.class)
    public ResponseEntity<Void> handleInvalidCriteriaException(InvalidCriteriaException ice) {
        LOGGER.error("Invalid exception criteria was specified: ", ice);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Void> handlesThrowable(Throwable t) {
        LOGGER.error("Unexpected exception occurred: ", t);
        return ResponseEntity.internalServerError().build();
    }
}
