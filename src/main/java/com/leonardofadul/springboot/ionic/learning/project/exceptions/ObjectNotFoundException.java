package com.leonardofadul.springboot.ionic.learning.project.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String msg){
        super(msg);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
