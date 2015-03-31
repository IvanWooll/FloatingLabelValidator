# FloatingLabelValidator
A small library including an example app demonstrating a concept of combining the 'floating label' pattern with form validation.
[Youtube video demo](https://youtu.be/9O6cJpybySg)
---
This is more a proof of concept than a full blown library but if you want to use it all the files you need are in the lib folder.

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical">

    <com.ivanwooll.floatinglabelvalidator.lib.FloatingLabelTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hint="alpha"
        app:allowEmpty="false"
        app:validatorType="alpha" />

    <com.ivanwooll.floatinglabelvalidator.lib.FloatingLabelTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hint="numeric"
        app:allowEmpty="false"
        app:validatorType="numeric" />

    <com.ivanwooll.floatinglabelvalidator.lib.FloatingLabelTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hint="alpha numeric"
        app:allowEmpty="false"
        app:validatorType="alphaNumeric" />

    <com.ivanwooll.floatinglabelvalidator.lib.FloatingLabelTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hint="email"
        app:allowEmpty="false"
        app:validatorType="email" />

    <com.ivanwooll.floatinglabelvalidator.lib.FloatingLabelTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hint="phone number"
        app:allowEmpty="false"
        app:validatorType="phone" />

</LinearLayout>
