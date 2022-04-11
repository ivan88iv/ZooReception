package com.zuhlke.bg.camp.api.exception;

import com.zuhlke.bg.camp.client.exception.NonExistentAnimalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<Void> handlesNonExistingAnimalsException(NonExistentAnimalException neae) {
        LOGGER.warn("No information about an animal was discovered", neae);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Void> handlesThrowable(Throwable t) {
        LOGGER.error("Exception occurred", t);
        return ResponseEntity.internalServerError().build();
    }
}
