package com.mobiquityinc.packer.validation;

import com.mobiquityinc.packer.util.Messages;

/**
 * This class is responsible to provide some validation utilities
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class Preconditions {

    public static final Messages MESSAGES = Messages.getInstance();

    /**
     * Check if the object is null, if true rise a NullPointerException
     * @param reference the object to be validate
     */
    public static void checkNotNull(Object reference) {
        if(reference == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Check an argument boolean expression, if false rise an IllegalArgumentException with a given message
     * @param expression an boolean expression
     * @param errorMessage the String with the message error
     */
    public static void checkArgument(boolean expression, String errorMessage) {
        if(!expression) {
            throw new IllegalArgumentException(MESSAGES.getString(errorMessage));
        }
    }

    /**
     * @see Preconditions#checkArgument(boolean, String)
     * @param expression an boolean expression
     * @param errorMessage the String with the message error
     * @param params list of object to be passed in the message
     */
    public static void checkArgument(boolean expression, String errorMessage, Object... params) {
        checkArgument(expression, MESSAGES.getString(errorMessage, params));
    }
}
