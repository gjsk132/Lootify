package com.gjsk.lootify.map_creator;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gjsk.lootify.BaseActivity;
import com.gjsk.lootify.R;
import com.gjsk.lootify.customview.CreateMapSettingButton;
import com.gjsk.lootify.customview.DialogBase;
import com.gjsk.lootify.customview.SmallButton;
import com.gjsk.lootify.databinding.ActivityCreateMapBinding;
import com.gjsk.lootify.recyclerview.ARIconAdapter;
import com.gjsk.lootify.recyclerview.Board;
import com.gjsk.lootify.recyclerview.BoardAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CreateMap extends BaseActivity {

    private ActivityCreateMapBinding binding;
    private FrameLayout dialogContents;
    private DialogBase dialogBase;
    private LinearLayout buttonContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initButtons();
    }

    private void initButtons() {
        binding.treasureSettingButton.setOnClickListener(view -> {
            showCustomDialog();
            binding.treasureSettingButton.completeSetting();
            updateButtonStates();
        });

        binding.testSettingButton.setOnClickListener(view -> {
            if (binding.treasureSettingButton.isSetting()) {
                binding.testSettingButton.completeSetting();
                updateButtonStates();
            } else {
                showToast("Treasure 먼저 생성하기!");
            }
        });

        binding.positionSettingButton.setOnClickListener(view -> {
            if (binding.testSettingButton.isSetting()) {
                binding.positionSettingButton.completeSetting();
                updateButtonStates();
            } else {
                showToast("Test 먼저 생성하기!");
            }
        });
    }

    private void updateButtonStates() {
        boolean anySettingsComplete = areAnySettingsComplete(
                binding.treasureSettingButton,
                binding.testSettingButton,
                binding.positionSettingButton
        );

        binding.saveMapButton.setEnabled(anySettingsComplete);
        binding.previewButton.setEnabled(binding.positionSettingButton.isSetting());
    }

    private boolean areAnySettingsComplete(CreateMapSettingButton... buttons) {
        for (CreateMapSettingButton button : buttons) {
            if (button.isSetting()) {
                return true;
            }
        }
        return false;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void showCustomDialog() {
        dialogBase = new DialogBase(this);
        dialogContents = dialogBase.findViewById(R.id.dialog_contents);
        buttonContents = dialogBase.findViewById(R.id.button_contents);

        dialogBase.setTitle("Treasure");
        dialogBase.setDescription("Step1");
        dialogBase.setIconVisibility(true);
        dialogBase.setButtonsVisibility(false);

        showSettingList();

        dialogBase.findViewById(R.id.icon).setOnClickListener(view -> {
            showToast("보물 만들기!");
            showCreateTreasure();
        });

        Dialog dialog = new Dialog(CreateMap.this);
        dialog.setContentView(dialogBase);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(
                (int) (getResources().getDisplayMetrics().widthPixels * 0.8),
                (int) (getResources().getDisplayMetrics().heightPixels * 0.6)
        );

        dialog.setCancelable(true);
        dialog.show();
    }

    private void showSettingList() {
        dialogContents.removeAllViews();

        List<Board> datas = new ArrayList<>();
        datas.add(new Board("BOMUL (1)", "엄청난 보물입니다!"));
        datas.add(new Board("Jewel (ALL)", "반짝반짝 빛나는 보석!"));

        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_setting_list, dialogContents, false);
        dialogContents.addView(contents);

        RecyclerView recyclerView = contents.findViewById(R.id.list_recycler_view);

        recyclerView.setAdapter(new BoardAdapter(datas));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dialogBase.setIconVisibility(true);
        dialogBase.setButtonsVisibility(false);
    }

    private void showCreateTreasure() {
        dialogContents.removeAllViews();

        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_treasure_info, dialogContents, false);
        dialogContents.addView(contents);

        addButtons(this::showSettingList, this::showIconSelect);
    }

    private void showIconSelect(){
        dialogContents.removeAllViews();

        List<Integer> iconList = Arrays.asList(
                R.drawable.ar_ic_1, R.drawable.ar_ic_2, R.drawable.ar_ic_3, R.drawable.ar_ic_4
        );

        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_treasure_ar_icon, dialogContents, false);
        dialogContents.addView(contents);

        RecyclerView recyclerView = contents.findViewById(R.id.ar_icon_recycler_view);

        recyclerView.setAdapter(new ARIconAdapter(iconList));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        addButtons(this::showCreateTreasure, this::showCheckTreasure);

    }

    private void showCheckTreasure(){
        dialogContents.removeAllViews();

        addButtons(this::showIconSelect, this::showSettingList);
    }

    private void addButtons(Runnable onBack, Runnable onNext) {
        buttonContents.removeAllViews();

        SmallButton beforeButton = new SmallButton(this);
        beforeButton.setText("BEFORE");
        beforeButton.setOnClickListener(view -> onBack.run());

        SmallButton nextButton = new SmallButton(this);
        nextButton.setText("NEXT");
        nextButton.setOnClickListener(view-> onNext.run());

        buttonContents.addView(beforeButton);
        buttonContents.addView(nextButton);

        dialogBase.setIconVisibility(false);
        dialogBase.setButtonsVisibility(true);
    }
}