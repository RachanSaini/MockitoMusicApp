package com.stackroute.musicApplicationTask1.exceptions;

public class EmptyFieldException extends Exception {
    private String message;

    public EmptyFieldException(String message) {
        this.message = message;
    }
}
