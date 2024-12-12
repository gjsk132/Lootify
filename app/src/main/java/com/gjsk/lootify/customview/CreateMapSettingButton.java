package com.gjsk.lootify.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.gjsk.lootify.R;

import java.util.Objects;

public class CreateMapSettingButton extends LinearLayout {
    LinearLayout background;
    ImageView icon;
    TextView step;
    TextView settingValue;
    TextView showCount;
    int count;

    public CreateMapSettingButton(Context context){
        super(context);
        initView();
    }

    public CreateMapSettingButton(Context context, AttributeSet attrs){
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public CreateMapSettingButton(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }

    private void initView(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.create_map_setting_button, this, false);
        addView(v);

        background = (LinearLayout) findViewById(R.id.background);
        icon = (ImageView) findViewById(R.id.icon);
        step = (TextView) findViewById(R.id.step);
        settingValue = (TextView) findViewById(R.id.setting_value);
        showCount = (TextView) findViewById(R.id.setting_count);
    }

    private void getAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CreateMapSettingButton);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CreateMapSettingButton, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray){
        String settingTextString = typedArray.getString(R.styleable.CreateMapSettingButton_main_text);
        String settingDescString = typedArray.getString(R.styleable.CreateMapSettingButton_sub_text);
        setSetting(settingTextString, settingDescString);

        int iconID = setIcon(settingTextString);
        icon.setImageResource(iconID);

        count = typedArray.getInt(R.styleable.CreateMapSettingButton_setting_cnt,0);
        setCount(count);
    }

    private void setSetting(String settingTextString, String settingDescString){
        settingValue.setText(settingTextString);
        step.setText(settingDescString);
    }

    private int setIcon(String settingTextString){
        return switch (Objects.requireNonNull(settingTextString)) {
            case "Treasure" -> R.drawable.treasure_ic;
            case "Test" -> R.drawable.test_ic;
            case "Position" -> R.drawable.position_ic;
            default -> R.drawable.white_frame_mid;
        };
    }

    private void setCount(int count){
        showCount.setText(String.valueOf(count));
        if (count == 0){
            background.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.white));
            showCount.setVisibility(View.GONE);
        } else if (count == 1){
            background.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.lightBlue));
            showCount.setVisibility(View.VISIBLE);
        }
    }

    public void completeSetting(){
        count++;
        setCount(count);
    }
    
    public boolean isSetting(){
        return (count != 0);
    }
}
