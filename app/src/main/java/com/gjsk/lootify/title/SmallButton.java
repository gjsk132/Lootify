package com.gjsk.lootify.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gjsk.lootify.R;

public class SmallButton extends FrameLayout {
    TextView text;
    FrameLayout smallButton;

    public SmallButton(Context context){
        super(context);
        initView();

    }

    public SmallButton(Context context, AttributeSet attrs){
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public SmallButton(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }

    private void initView(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.small_button, this, false);
        addView(v);

        text = (TextView) findViewById(R.id.text);
        smallButton = (FrameLayout) findViewById(R.id.smallButton);

    }

    private void getAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SmallButton);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SmallButton, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {

        String textString = typedArray.getString(R.styleable.SmallButton_text);
        text.setText(textString);

        int textColor = typedArray.getColor(R.styleable.SmallButton_text_color, 0);
        text.setTextColor(textColor);

        typedArray.recycle();
    }

}
