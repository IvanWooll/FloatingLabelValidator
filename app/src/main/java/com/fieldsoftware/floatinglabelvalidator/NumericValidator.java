package com.fieldsoftware.floatinglabelvalidator;

import android.text.TextUtils;

/**
 * Created by Ivan on 14/02/2015.
 */
public class NumericValidator extends BaseValidator {

    private static final String NUMERICAL_MESSAGE = "can only contain numbers";

    public boolean isValid(String input) {
        if (super.isValid(input)) {
            if ((TextUtils.isDigitsOnly(input))) {
                return true;
            } else {
                validationMessage = NUMERICAL_MESSAGE;
                return false;
            }
        }
        return false;
    }
}
