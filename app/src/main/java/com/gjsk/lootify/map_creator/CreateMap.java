package com.gjsk.lootify.map_creator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
}