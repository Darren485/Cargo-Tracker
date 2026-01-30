package com.darren;

import com.darren.exception.IncompleteNumberException;
public class Validator {
    //METHOD FOR ALL THE CUSTOM EXCEPTION CLASSES

    public  static void validatePhoneNumber(String clientNumber) throws IncompleteNumberException {
        if (clientNumber == null || clientNumber.length() != 11) {
            throw new IncompleteNumberException("Phone number is incomplete. It must be 11 digits");
        }
    }
}

