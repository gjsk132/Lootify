package com.gjsk.lootify.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gjsk.lootify.R;

public class InputField extends LinearLayout {
    TextView question;
    TextView required;
    EditText userInput;

    public InputField(Context context){
        super(context);
        initView();
    }

    public InputField(Context context, AttributeSet attrs){
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public InputField(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }

    private void initView(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.input_field, this, false);
        addView(v);

        question = (TextView) findViewById(R.id.question);
        required = (TextView) findViewById(R.id.required_field_indicator);
        userInput = (EditText) findViewById(R.id.user_input);
    }

    private void getAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.InputField);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.InputField, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray){
        String questionString = typedArray.getString(R.styleable.InputField_question);
        String inputHint = typedArray.getString(R.styleable.InputField_hint);
        boolean isRequired = typedArray.getBoolean(R.styleable.InputField_is_required, false);

        setQuestion(questionString);
        setInputHint(inputHint);
        setRequiredVisibility(isRequired);
    }

    private void setQuestion(String questionString){
        question.setText(questionString);
    }

    private void setInputHint(String inputHint){
        userInput.setHint(inputHint);
    }

    private void setRequiredVisibility(boolean isRequired){
        required.setVisibility(isRequired? View.VISIBLE : View.GONE);
    }

}
