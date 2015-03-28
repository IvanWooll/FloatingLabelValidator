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

    private static final String ALPHA_ERROR_MESSAGE = "should only contain letters";
    private static final String NUMERIC_ERROR_MESSAGE = "should only contain numbers";

    private boolean mAllowEmpty;
    private int mValidatorType;
    private String mString;

    public Validator(boolean allowEmpty, int validatorType) {
        this.mAllowEmpty = allowEmpty;
        this.mValidatorType = validatorType;
    }

    public String validate(CharSequence s) {
        String result = "";

        final StringBuilder sb = new StringBuilder(s.length());
        sb.append(s);
        mString = sb.toString();
        if (mAllowEmpty && mValidatorType < 0) {
            return "";
        }
        switch (mValidatorType) {
            case ALPHA:
                result += validateAlpha();
                break;
            case NUMERIC:
                result += validateNumeric();
                break;
            case ALPHA_NUMERIC:

                break;
            case EMAIL:

                break;
            case PHONE:

                break;
            default:
                break;
        }
        result += validateNotEmpty();
        return result;

    }

    private String validateNotEmpty() {
        if (mString.length() > 0) {
            return "";
        } else {
            return "must not be empty";
        }
    }

    private String validateAlpha() {
        char[] chars = mString.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return ALPHA_ERROR_MESSAGE;
            }
        }
        return "";
    }

    private String validateNumeric() {
        char[] chars = mString.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return NUMERIC_ERROR_MESSAGE;
            }
        }
        return "";
    }

}
