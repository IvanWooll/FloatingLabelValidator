package com.fieldsoftware.floatinglabelvalidator;

/**
 * Created by ivanwooll on 28/03/15.
 */
public class Validator {
    public static final int ALPHA = 0;
    public static final int NUMERIC = 1;
    public static final int ALPHA_NUMERIC = 2;
    public static final int EMAIL = 3;
    public static final int PHONE = 4;

    private boolean mAllowEmpty;
    private int mValidatorType;

    public Validator(boolean allowEmpty, int validatorType) {
        this.mAllowEmpty = allowEmpty;
        this.mValidatorType = validatorType;
    }

    public String validate(CharSequence s) {
        if (mAllowEmpty && mValidatorType < 0) {
            return "";
        }

        if (s.length() > 0) {
            return "";
        } else {
            return "must not be empty";
        }
    }

}
