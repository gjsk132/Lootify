package com.gjsk.lootify.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gjsk.lootify.R;

import java.util.Objects;

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
        setText(textString);

        int textColor = typedArray.getColor(R.styleable.SmallButton_text_color, 0);
        setTextColor(textColor);

        boolean buttonEnable = typedArray.getBoolean(R.styleable.SmallButton_enable, false);
        setEnabled(buttonEnable);

        typedArray.recycle();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        smallButton.setEnabled(enabled);
        smallButton.setAlpha(enabled ? 1f : 0.7f);
    }

    public void setText(String textString) {
        text.setText(textString);
        smallButton.setBackgroundResource(setButtonColor(textString));
    }

    private void setTextColor(int textColor) {
        text.setTextColor(textColor);
    }

    private int setButtonColor(String textString){

        switch (Objects.requireNonNull(textString)){
            case "FIND":
            case "SELECT":
            case "NEXT" :
                return R.drawable.light_yellow_button;

            case "JOIN":
            case "LOAD":
            case "SAVE" :
                return R.drawable.light_green_button;

            case "CANCLE":
            case "BEFORE":
            case "BACK":
                return R.drawable.gray_button;

            case "SAVE MAP":
            case "CREATE MAP":
                return R.drawable.light_black_button;

            case "MY MAP":
                return R.drawable.light_brown_button;

            case "HISTORY":
                return R.drawable.dark_yellow_button;

            case "PREVIEW":
                return R.drawable.mid_green_button;

            default:
                return R.drawable.white_frame;
        }
    }
}
