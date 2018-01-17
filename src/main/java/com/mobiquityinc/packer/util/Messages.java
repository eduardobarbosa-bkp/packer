package com.mobiquityinc.packer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class is a singleton responsible to provide the messages
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class Messages {
    private static Logger log = LoggerFactory.getLogger(Messages.class);
    public static final String ERROR_WEIGHT_KEY = "error.weight";
    public static final String ERROR_COST_KEY = "error.cost";
    public static final String ERROR_ITEMS_SIZE_KEY = "error.items.size";
    private static final String BUNDLE_NAME = "message";
    private final ResourceBundle resourceBundle;

    private Messages() {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    private static Messages instance;

    public static Messages getInstance(){
        if(instance == null){
            instance = new Messages();
        }
        return instance;
    }


    /**
     * Find the value of key in the message resource bundle
     * @param key the identifier of the entry
     * @return String with the value of key with exists, otherwise the key value
     */
    public String getString(String key) {
        try {
            return key == null ? key : resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            log.warn(e.getMessage(), e);
            return key;
        }
    }

    /**
     * Find the value of key in the message resource bundle
     * @param key the identifier of the entry with some format
     * @param params object(s) to format
     * @return String with the value of key with exists, otherwise the key value
     */
    public String getString(String key, Object... params) {
       return MessageFormat.format(getString(key), params);
    }
}
