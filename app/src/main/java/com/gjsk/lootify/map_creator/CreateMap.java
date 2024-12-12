package com.gjsk.lootify.map_creator;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gjsk.lootify.BaseActivity;
import com.gjsk.lootify.R;
import com.gjsk.lootify.customview.CreateMapSettingButton;
import com.gjsk.lootify.customview.DialogBase;
import com.gjsk.lootify.customview.SmallButton;
import com.gjsk.lootify.databinding.ActivityCreateMapBinding;
import com.gjsk.lootify.recyclerview.Board;
import com.gjsk.lootify.recyclerview.BoardAdapter;

import java.util.ArrayList;
import java.util.List;
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

    private void showCustomDialog() {

        Dialog dialog = new Dialog(CreateMap.this);

        DialogBase dialogBase = new DialogBase(this);

        FrameLayout dialogContents = dialogBase.findViewById(R.id.dialog_contents);
        showSettingList(dialogContents);

        dialogBase.setTitle("Treasure");
        dialogBase.setDescription("Step1");
        dialogBase.setIconVisibility(true);
        dialogBase.setButtonsVisibility(false);

        dialogBase.findViewById(R.id.icon).setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "보물 만들기!", Toast.LENGTH_SHORT).show();
            dialogContents.removeAllViews();
            showCreateTreasure(dialogBase, dialogContents);
        });

        dialog.setContentView(dialogBase);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(
                    (int) (getResources().getDisplayMetrics().widthPixels * 0.8),
                    (int) (getResources().getDisplayMetrics().heightPixels * 0.6)
            );
        }

        dialog.setCancelable(true);
        dialog.show();
    }

    private void showSettingList(FrameLayout dialogContents){
        List<Board> datas = new ArrayList<>();
        datas.add(new Board("BOMUL (1)", "엄청난 보물입니다!"));
        datas.add(new Board("Jewel (ALL)", "반짝반짝 빛나는 보석!"));
        datas.add(new Board("BOMUL (1)", "엄청난 보물입니다!"));
        datas.add(new Board("Jewel (ALL)", "반짝반짝 빛나는 보석!"));
        datas.add(new Board("BOMUL (1)", "엄청난 보물입니다!"));
        datas.add(new Board("Jewel (ALL)", "반짝반짝 빛나는 보석!"));
        datas.add(new Board("BOMUL (1)", "엄청난 보물입니다!"));
        datas.add(new Board("Jewel (ALL)", "반짝반짝 빛나는 보석!"));

        BoardAdapter boardAdapter = new BoardAdapter(datas);

        LinearLayout contents = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dialog_setting_list, dialogContents, false);

        RecyclerView itemRecyclerView = contents.findViewById(R.id.list_recycler_view);
        itemRecyclerView.setAdapter(boardAdapter);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dialogContents.addView(contents);
    }

    private void showCreateTreasure(DialogBase dialogBase, FrameLayout dialogContents){
        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_treasure_info, dialogContents, false);
        dialogContents.addView(contents);

        LinearLayout buttonContents = dialogBase.findViewById(R.id.button_contents);
        addButtons(buttonContents);

        dialogBase.setIconVisibility(false);
        dialogBase.setButtonsVisibility(true);
    }

    private void addButtons(LinearLayout buttonContents) {

        SmallButton beforeButton = new SmallButton(this);
        beforeButton.setText("BEFORE");
        beforeButton.setEnabled(true);

        SmallButton nextButton = new SmallButton(this);
        nextButton.setText("NEXT");
        nextButton.setEnabled(true);

        buttonContents.addView(beforeButton);
        buttonContents.addView(nextButton);
    }
}