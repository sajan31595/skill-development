package com.coaching.skilldevelopment.exception;

public class InvalidRequestException extends Exception{

    private static String message = "Invalid request";
    public InvalidRequestException(){
        super(message);
    }

    public InvalidRequestException(String errorMessage){
        super(errorMessage);
    }
}
