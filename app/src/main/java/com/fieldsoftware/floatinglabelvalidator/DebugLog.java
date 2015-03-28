package com.fieldsoftware.floatinglabelvalidator;

/**
 * Created by Ivan on 14/02/2015.
 */

import android.util.Log;

public class DebugLog {
    public final static boolean DEBUG = BuildConfig.DEBUG;

    public static void log(String message) {
        if (DEBUG) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.d(className + "." + methodName + "():" + lineNumber, message);
        }
    }
}
