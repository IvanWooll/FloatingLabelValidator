package com.ivanwooll.floatinglabelvalidator.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Ivan on 15/03/2015.
 */
public class FloatingLabelTextView extends FrameLayout implements TextWatcher, View.OnFocusChangeListener {
    private EditText mEditText;
    private TextView mTextViewHintTop;
    private String mValidationMessage;
    private String mHintText;
    private boolean mAllowEmpty;
    private int mValidatorType;
    private Validator mValidator;
    private boolean mIsEmpty;
    private int mMainTextSize;
    private boolean mIsValid;

    private int red;
    private int green;

    public FloatingLabelTextView(Context context) {
        super(context);
    }

    public FloatingLabelTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FloatingLabelTextView, 0, 0);
        try {
            mValidationMessage = a.getString(R.styleable.FloatingLabelTextView_validationMessage);
            mHintText = a.getString(R.styleable.FloatingLabelTextView_hint);
            mAllowEmpty = a.getBoolean(R.styleable.FloatingLabelTextView_allowEmpty, true);
            mValidatorType = a.getInt(R.styleable.FloatingLabelTextView_validatorType, -1);
            mMainTextSize = a.getInt(R.styleable.FloatingLabelTextView_textSize, 18);
        } finally {
            a.recycle();
        }
        init();
    }

    public FloatingLabelTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.floating_label_text_view, this);
        mTextViewHintTop = (TextView) findViewById(R.id.textViewHintTop);
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText.setTextSize((float) mMainTextSize);
        mEditText.addTextChangedListener(this);
        mTextViewHintTop.setTextSize(mMainTextSize * .7f);
        mTextViewHintTop.setText(mHintText);
        red = getResources().getColor(android.R.color.holo_red_light);
        green = getResources().getColor(android.R.color.holo_green_light);
        mValidator = new Validator(mAllowEmpty, mValidatorType);

        mTextViewHintTop.setY(70);
        mEditText.setOnFocusChangeListener(this);
        mIsEmpty = true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mIsEmpty = s.length() < 1;
        mValidationMessage = mValidator.validate(s);
        mIsValid = TextUtils.isEmpty(mValidationMessage);
        mTextViewHintTop.setTextColor(mIsValid ? green : red);
        mTextViewHintTop.setText(mIsValid ? mHintText + "" : mHintText + " - " + mValidationMessage);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            mTextViewHintTop.animate().translationY(0);
        } else {
            if (mIsEmpty) {
                mTextViewHintTop.animate().translationY(70);
            }

        }
    }

    public Editable getText() {
        return mEditText.getText();
    }

    public boolean isValid() {
        return mIsValid;
    }
}
