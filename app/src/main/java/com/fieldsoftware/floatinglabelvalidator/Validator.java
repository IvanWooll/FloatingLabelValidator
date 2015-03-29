package com.fieldsoftware.floatinglabelvalidator;

import android.text.TextUtils;

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
    private static final String ALPHA_NUMERIC_ERROR_MESSAGE = "should only contain letters or numbers";
    private static final String EMAIL_ERROR_MESSAGE = "is not a valid email address";
    private static final String PHONE_ERROR_MESSAGE = "is not a valid phone number";

    private boolean mAllowEmpty;
    private int mValidatorType;
    private String mString;
    private char[] mChars;
    private CharSequence mCharSequence;

    public Validator(boolean allowEmpty, int validatorType) {
        this.mAllowEmpty = allowEmpty;
        this.mValidatorType = validatorType;
    }

    public String validate(CharSequence s) {
        mCharSequence = s;
        String result = "";
        mString = stringFromCharSequence(s);
        mChars = mString.toCharArray();
        if (mAllowEmpty && mValidatorType < 0) {
            return "";
        }
        if (mString.length() < 1) {
            return " must not be empty";
        }
        switch (mValidatorType) {
            case ALPHA:
                result += validateAlpha();
                break;
            case NUMERIC:
                result += validateNumeric();
                break;
            case ALPHA_NUMERIC:
                result += validateAlphaNumeric();
                break;
            case EMAIL:
                result += validateEmail();
                break;
            case PHONE:
                result += validatePhoneNO();
                break;
            default:
                break;
        }
//        result += validateNotEmpty();
        return result;
    }


    private String validateNotEmpty() {
        if (mString.length() > 0) {
            return "";
        } else {
            return " must not be empty";
        }
    }

    private String validateAlpha() {
        for (char c : mChars) {
            if (!Character.isLetter(c)) {
                return ALPHA_ERROR_MESSAGE;
            }
        }
        return "";
    }

    private String validateNumeric() {
        for (char c : mChars) {
            if (!Character.isDigit(c)) {
                return NUMERIC_ERROR_MESSAGE;
            }
        }
        return "";
    }

    private String validateAlphaNumeric() {
        for (char c : mChars) {
            if (!Character.isLetterOrDigit(c)) {
                return ALPHA_NUMERIC_ERROR_MESSAGE;
            }
        }
        return "";
    }

    private String validateEmail() {
        if (!isValidEmail(mCharSequence)) {
            return EMAIL_ERROR_MESSAGE;
        } else {
            return "";
        }
    }

    private String validatePhoneNO() {
        if (!isValidPhoneNo(mCharSequence)) {
            return PHONE_ERROR_MESSAGE;
        } else {
            return "";
        }
    }


    private boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean isValidPhoneNo(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }

    private String stringFromCharSequence(CharSequence s) {
        String str;
        final StringBuilder sb = new StringBuilder(s.length());
        sb.append(s);
        str = sb.toString();
        return str;
    }

}
