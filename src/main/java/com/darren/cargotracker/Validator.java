package com.darren.cargotracker.exception;


public class Validator {
    //METHOD FOR ALL THE CUSTOM EXCEPTION CLASSES

    public void validatephoneNumber(String clientNumber) throws IncompleteNumberException {
        if (clientNumber == null || clientNumber.length() != 11) {
            throw new IncompleteNumberException("Phone number is incomplete. It must be 11 digits");
        }
    }
}
