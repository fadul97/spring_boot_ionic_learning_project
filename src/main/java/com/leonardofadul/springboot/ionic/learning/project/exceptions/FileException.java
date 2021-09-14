package com.leonardofadul.springboot.ionic.learning.project.exceptions;

public class FileException extends RuntimeException{

    public FileException(String msg){
        super(msg);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
