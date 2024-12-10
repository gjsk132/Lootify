package com.gjsk.lootify.map_creator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gjsk.lootify.R;
import com.gjsk.lootify.customview.CreateMapSettingButton;
import com.gjsk.lootify.customview.SmallButton;
import com.gjsk.lootify.databinding.ActivityCreateMapBinding;

public class CreateMap extends AppCompatActivity {

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
            treasureSettingButton.completeSetting();
            updateButtonStates();
        });

        testSettingButton.setOnClickListener(view ->{
            testSettingButton.completeSetting();
            updateButtonStates();
        });

        positionSettingButton.setOnClickListener(view -> {
            positionSettingButton.completeSetting();
            updateButtonStates();
        });
    }

    private void updateButtonStates(){
        boolean allSettingsComplete = areAllSettingsComplete(
                binding.treasureSettingButton,
                binding.testSettingButton,
                binding.positionSettingButton
        );

        boolean anySettingsComplete = areAnySettingsComplete(
                binding.treasureSettingButton,
                binding.testSettingButton,
                binding.positionSettingButton
        );

        binding.saveMapButton.setEnabled(anySettingsComplete);
        binding.previewButton.setEnabled(allSettingsComplete);
    }

    private boolean areAllSettingsComplete(CreateMapSettingButton... buttons){
        for (CreateMapSettingButton button : buttons){
            if (!button.isSetting()){
                return false;
            }
        }
        return true;
    }

    private boolean areAnySettingsComplete(CreateMapSettingButton... buttons){
        for (CreateMapSettingButton button: buttons){
            if (button.isSetting()){
                return true;
            }
        }
        return false;
    }
}