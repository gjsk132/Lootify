package com.gjsk.lootify.map_creator;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gjsk.lootify.BaseActivity;
import com.gjsk.lootify.R;
import com.gjsk.lootify.customview.CreateMapSettingButton;
import com.gjsk.lootify.customview.DialogBase;
import com.gjsk.lootify.databinding.ActivityCreateMapBinding;

import java.util.Objects;

public class CreateMap extends BaseActivity {

    private ActivityCreateMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CreateMapSettingButton treasureSettingButton = binding.treasureSettingButton;
        CreateMapSettingButton testSettingButton = binding.testSettingButton;
        CreateMapSettingButton positionSettingButton = binding.positionSettingButton;

        treasureSettingButton.setOnClickListener(view ->{

            showCustomDialog();

            treasureSettingButton.completeSetting();
            updateButtonStates();
        });

        testSettingButton.setOnClickListener(view ->{
            if (treasureSettingButton.isSetting()){
                testSettingButton.completeSetting();
                updateButtonStates();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Treasure 먼저 생성하기!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        positionSettingButton.setOnClickListener(view -> {
            if (testSettingButton.isSetting()) {
                positionSettingButton.completeSetting();
                updateButtonStates();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Test 먼저 생성하기!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void updateButtonStates(){
        boolean anySettingsComplete = areAnySettingsComplete(
                binding.treasureSettingButton,
                binding.testSettingButton,
                binding.positionSettingButton
        );

        binding.saveMapButton.setEnabled(anySettingsComplete);
        binding.previewButton.setEnabled(binding.positionSettingButton.isSetting());
    }

    private boolean areAnySettingsComplete(CreateMapSettingButton... buttons){
        for (CreateMapSettingButton button: buttons){
            if (button.isSetting()){
                return true;
            }
        }
        return false;
    }

    private void showCustomDialog(){
        Dialog dialog = new Dialog(CreateMap.this);

        DialogBase dialogBase = new DialogBase(this);

        FrameLayout dialogContents = dialogBase.findViewById(R.id.dialog_contents);
        LinearLayout contents = (LinearLayout)  getLayoutInflater().inflate(R.layout.dialog_room_select, dialogContents, false);

        dialogContents.addView(contents);

        dialogBase.setTitle("Treasure");
        dialogBase.setDescription("Step1");
        dialogBase.setIconVisibility(true);
        dialogBase.setButtonsVisibility(false);

        dialog.setContentView(dialogBase);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

        if (dialog.getWindow() != null){
            dialog.getWindow().setLayout(
                (int) (getResources().getDisplayMetrics().widthPixels * 0.8),
                (int) (getResources().getDisplayMetrics().heightPixels * 0.6)
            );
        }

        dialog.setCancelable(true);
        dialog.show();
    }
}