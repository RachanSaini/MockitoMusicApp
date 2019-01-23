package com.stackroute.musicApplicationTask1.globalException;

import com.stackroute.musicApplicationTask1.exceptions.EmptyFieldException;
import com.stackroute.musicApplicationTask1.exceptions.TrackAlreadyExistsException;
import com.stackroute.musicApplicationTask1.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST , reason = "Not all mandatory fields are filled")
    @ExceptionHandler(EmptyFieldException.class)
    public void handleEmptyFieldException(EmptyFieldException e){
        System.out.println("Not all mandatory fields filled");
    }

    @ResponseStatus(value = HttpStatus.CONFLICT , reason = "Track already exists.")
    @ExceptionHandler(TrackAlreadyExistsException.class)
    public void handleTrackAlreadyExixtsException(TrackAlreadyExistsException e){
        System.out.println("Track already exists");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(TrackNotFoundException.class)
    public void handleTrackNotFoundException(TrackNotFoundException e){
        System.out.println("Track doesn't exists");
    }
}
