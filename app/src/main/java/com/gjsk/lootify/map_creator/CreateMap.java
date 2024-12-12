package com.gjsk.lootify.map_creator;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.gjsk.lootify.recyclerview.Treasure;
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

    private List<Treasure> treasures = new ArrayList<>();
    private String currentTreasureName;
    private int currentTreasureIconRes = -1;

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
            updateButtonStates();
        });

        binding.testSettingButton.setOnClickListener(view -> {
            if (binding.treasureSettingButton.isSetting()) {
                updateButtonStates();
            } else {
                showToast("Treasure 먼저 생성하기!");
            }
        });

        binding.positionSettingButton.setOnClickListener(view -> {
            if (binding.testSettingButton.isSetting()) {
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

        dialog.setOnDismissListener(dialogInterface -> resetTreasureState());

        dialog.setCancelable(true);
        dialog.show();
    }

    private void showSettingList() {
        dialogContents.removeAllViews();

        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_setting_list, dialogContents, false);
        dialogContents.addView(contents);

        RecyclerView recyclerView = contents.findViewById(R.id.list_recycler_view);

        List<Board> boards = new ArrayList<>();
        for (Treasure treasure : treasures){
            boards.add(new Board(treasure.getName(), "선택된 아이콘: " + treasure.getIconRes()));
        }

        BoardAdapter adapter = new BoardAdapter(boards, binding.treasureSettingButton);

        adapter.setOnItemDeletedListener(position -> {
            treasures.remove(position);
            adapter.notifyItemRemoved(position);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dialogBase.setIconVisibility(true);
        dialogBase.setButtonsVisibility(false);
    }

    private void showCreateTreasure() {
        dialogContents.removeAllViews();

        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_treasure_info, dialogContents, false);
        dialogContents.addView(contents);

        EditText treasureNameInput = contents.findViewById(R.id.user_input);

        if (currentTreasureName != null){
            treasureNameInput.setText(currentTreasureName);
        }

        buttonContents.removeAllViews();

        SmallButton beforeButton = new SmallButton(this);
        beforeButton.setText("BEFORE");
        beforeButton.setOnClickListener(view -> showSettingList());

        SmallButton nextButton = new SmallButton(this);
        nextButton.setText("NEXT");
        nextButton.setOnClickListener(view-> {
            currentTreasureName = treasureNameInput.getText().toString().trim();
            if (currentTreasureName.isEmpty()){
                showToast("보물 이름을 입력하세요.");
            }else{
                showIconSelect();
            }
        });

        buttonContents.addView(beforeButton);
        buttonContents.addView(nextButton);

        dialogBase.setIconVisibility(false);
        dialogBase.setButtonsVisibility(true);
    }

    private void showIconSelect(){
        dialogContents.removeAllViews();

        List<Integer> iconList = Arrays.asList(
                R.drawable.ar_ic_1, R.drawable.ar_ic_2, R.drawable.ar_ic_3, R.drawable.ar_ic_4
        );

        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_treasure_ar_icon, dialogContents, false);
        dialogContents.addView(contents);

        RecyclerView recyclerView = contents.findViewById(R.id.ar_icon_recycler_view);
        ARIconAdapter adapter = new ARIconAdapter(iconList);

        if (currentTreasureIconRes == -1){
            currentTreasureIconRes = iconList.get(0);
        }
        adapter.setSelectedIcon(currentTreasureIconRes);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter.setOnItemClickListener(iconRes->{
            currentTreasureIconRes = iconRes;
        });

        buttonContents.removeAllViews();

        SmallButton beforeButton = new SmallButton(this);
        beforeButton.setText("BEFORE");
        beforeButton.setOnClickListener(view -> showCreateTreasure());

        SmallButton nextButton = new SmallButton(this);
        nextButton.setText("NEXT");
        nextButton.setOnClickListener(view-> showCheckTreasure());

        buttonContents.addView(beforeButton);
        buttonContents.addView(nextButton);

        dialogBase.setIconVisibility(false);
        dialogBase.setButtonsVisibility(true);

    }

    private void showCheckTreasure(){
        dialogContents.removeAllViews();

        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_treasure_check, dialogContents, false);
        dialogContents.addView(contents);

        TextView nameView = contents.findViewById(R.id.name);
        ImageView iconView = contents.findViewById(R.id.img);

        nameView.setText(currentTreasureName);
        if (currentTreasureIconRes != -1){
            iconView.setImageResource(currentTreasureIconRes);
        }

        buttonContents.removeAllViews();

        SmallButton beforeButton = new SmallButton(this);
        beforeButton.setText("BEFORE");
        beforeButton.setOnClickListener(view -> showIconSelect());

        SmallButton nextButton = new SmallButton(this);
        nextButton.setText("NEXT");
        nextButton.setOnClickListener(view-> {
            saveTreasure();
            showSettingList();
        });

        buttonContents.addView(beforeButton);
        buttonContents.addView(nextButton);

        dialogBase.setIconVisibility(false);
        dialogBase.setButtonsVisibility(true);
    }

    private void saveTreasure() {
        if (currentTreasureName != null && currentTreasureIconRes != -1){
            treasures.add(new Treasure(currentTreasureName, currentTreasureIconRes));
            currentTreasureName = null;
            currentTreasureIconRes = -1;
            updateRecyclerView();
        }else{
            showToast("데이터를 완성하세요.");
        }
    }

    private void updateRecyclerView() {
        dialogContents.removeAllViews();

        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_setting_list, dialogContents, false);
        dialogContents.addView(contents);

        RecyclerView recyclerView = contents.findViewById(R.id.list_recycler_view);

        List<Board> boards = new ArrayList<>();
        for (Treasure treasure : treasures) {
            boards.add(new Board(treasure.getName(), "선택된 아이콘: " + treasure.getIconRes()));
        }

        BoardAdapter adapter = new BoardAdapter(boards, binding.treasureSettingButton);

        adapter.setOnItemDeletedListener(position -> {
            treasures.remove(position);
            adapter.notifyItemRemoved(position);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void resetTreasureState(){
        currentTreasureName = null;
        currentTreasureIconRes = -1;
    }
}