package com.zuhlke.bg.camp.client.exception;

import org.springframework.web.client.HttpClientErrorException;

public class NonExistentAnimalException extends RuntimeException {

    public NonExistentAnimalException(String name, HttpClientErrorException.NotFound nfe) {
        super("No animal with name " + name + " was found");
    }
}
