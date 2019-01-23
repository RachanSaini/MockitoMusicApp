package com.stackroute.musicApplicationTask1.exceptions;

public class TrackAlreadyExistsException extends Exception{
    private String message;



    public TrackAlreadyExistsException(String message) {
        this.message = message;
    }
}
