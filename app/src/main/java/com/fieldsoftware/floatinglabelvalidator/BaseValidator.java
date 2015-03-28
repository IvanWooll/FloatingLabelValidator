package com.fieldsoftware.floatinglabelvalidator;

import android.text.TextUtils;

/**
 * Created by Ivan on 14/02/2015.
 */
public class BaseValidator {

    public class ValidationState {
        private boolean valid;
        private String errorMessage;
    }

    public interface ValidationCallbacks {
        public void onValidate(ValidationState state);
    }

    private static final String EMPTY_MESSAGE = "cannot be empty";

    protected String validationMessage;


    public boolean isValid(String input) {
        if (!TextUtils.isEmpty(input)) {
            validationMessage = EMPTY_MESSAGE;
            return true;
        }
        return false;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

}
