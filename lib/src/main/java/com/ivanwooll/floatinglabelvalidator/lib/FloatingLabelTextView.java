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
    private String validationMessage;
    private String hintText;
    private boolean allowEmpty;
    private int validatorType;
    private Validator validator;
    private boolean isEmpty;
    private int mMainTextSize;

    private int red;
    private int green;

    public FloatingLabelTextView(Context context) {
        super(context);
    }

    public FloatingLabelTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FloatingLabelTextView, 0, 0);
        try {
            validationMessage = a.getString(R.styleable.FloatingLabelTextView_validationMessage);
            hintText = a.getString(R.styleable.FloatingLabelTextView_hint);
            allowEmpty = a.getBoolean(R.styleable.FloatingLabelTextView_allowEmpty, true);
            validatorType = a.getInt(R.styleable.FloatingLabelTextView_validatorType, -1);
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
        mTextViewHintTop.setText(hintText);
        red = getResources().getColor(android.R.color.holo_red_light);
        green = getResources().getColor(android.R.color.holo_green_light);
        validator = new Validator(allowEmpty, validatorType);

        mTextViewHintTop.setY(70);
        mEditText.setOnFocusChangeListener(this);
        isEmpty = true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isEmpty = s.length() < 1;
        validationMessage = validator.validate(s);
        boolean isValid = TextUtils.isEmpty(validationMessage);
        mTextViewHintTop.setTextColor(isValid ? green : red);
        mTextViewHintTop.setText(isValid ? hintText + "" : hintText + " - " + validationMessage);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            mTextViewHintTop.animate().translationY(0);
        } else {
            if (isEmpty) {
                mTextViewHintTop.animate().translationY(70);
            }

        }
    }

    public Editable getText() {
        return mEditText.getText();
    }
}
