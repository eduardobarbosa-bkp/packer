package com.mobiquityinc.exception;

/**
 * This class is responsible to provide wrapper exceptions in the API
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class APIException extends RuntimeException {

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }
}
