package com.coaching.skilldevelopment.exception;

public class AuthorizationException extends Exception{

    private static String message = "Unauthorized";
    public AuthorizationException(){
        super(message);
    }

    public AuthorizationException(String errorMessage){
        super(errorMessage);
    }
}
