package com.stackroute.musicApplicationTask1.exceptions;

public class TrackNotFoundException extends Exception {

    private String message;

    public TrackNotFoundException(String message) {
        this.message = message;
    }
}
