package com.gjsk.lootify.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gjsk.lootify.R;

public class DialogBase extends LinearLayout {
    TextView subTitle;
    TextView description;
    ImageView addIcon;
    FrameLayout dialogContents;
    View buttonLine;
    LinearLayout buttonContents;

    public DialogBase(Context context){
        super(context);
        initView();
    }

    public DialogBase(Context context, AttributeSet attrs){
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public DialogBase(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }

    private void initView(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.dialog_base, this, false);
        addView(v);

        subTitle = (TextView) findViewById(R.id.sub_title);
        description = (TextView) findViewById(R.id.description);
        addIcon = (ImageView) findViewById(R.id.icon);
        dialogContents = (FrameLayout) findViewById(R.id.dialog_contents);
        buttonLine = (View) findViewById(R.id.button_line);
        buttonContents = (LinearLayout) findViewById(R.id.button_contents);
    }

    private void getAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DialogBase);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DialogBase, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray){
        String subTitleString = typedArray.getString(R.styleable.DialogBase_sub_title);
        String descriptionString = typedArray.getString(R.styleable.DialogBase_description);
        boolean isAddIcon = typedArray.getBoolean(R.styleable.DialogBase_add_icon_visible, false);
        boolean isButtons = typedArray.getBoolean(R.styleable.DialogBase_is_buttons, false);

        setTitle(subTitleString);
        setDescription(descriptionString);
        setIconVisibility(isAddIcon);
        setButtonsVisibility(isButtons);

        typedArray.recycle();
    }

    public void setTitle(String text){
        subTitle.setText(text);
    }

    public void setDescription(String text){
        description.setText(text);
    }

    public void setIconVisibility(boolean isAddIcon){
        addIcon.setVisibility(isAddIcon ? View.VISIBLE : View.GONE);
    }

    public void setButtonsVisibility(boolean isButtons){
        if(isButtons){
            buttonLine.setVisibility(View.VISIBLE);
            buttonContents.setVisibility(View.VISIBLE);
        }else{
            buttonLine.setVisibility(View.GONE);
            buttonContents.setVisibility(View.GONE);
        }
    }
}
