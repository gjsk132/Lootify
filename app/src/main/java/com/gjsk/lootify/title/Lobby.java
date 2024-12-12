package com.gjsk.lootify.title;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gjsk.lootify.BaseActivity;
import com.gjsk.lootify.R;
import com.gjsk.lootify.customview.DialogBase;
import com.gjsk.lootify.customview.SmallButton;
import com.gjsk.lootify.databinding.ActivityLobbyBinding;
import com.gjsk.lootify.history.GameHistory;
import com.gjsk.lootify.map_creator.CreateMap;
import com.gjsk.lootify.map_creator.MyMays;

public class Lobby extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ActivityLobbyBinding binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DialogBase dialogBase = binding.dialogBase;

        LinearLayout buttonContents = dialogBase.findViewById(R.id.button_contents);
        FrameLayout dialogContents = dialogBase.findViewById(R.id.dialog_contents);

        addButtons(buttonContents);
        setDialogContents(dialogContents);

        SmallButton createMapButton = binding.createMapButton;
        SmallButton historyButton = binding.historyButton;
        SmallButton myMapButton = binding.myMapButton;

        createMapButton.setOnClickListener(view -> {
            setStartActivity(CreateMap.class);
        });

        historyButton.setOnClickListener(view -> {
            setStartActivity(GameHistory.class);
        });

        myMapButton.setOnClickListener(view -> {
            setStartActivity(MyMays.class);
        });

    }

    private void setDialogContents(FrameLayout dialogContents){
        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_room_select, dialogContents, false);

        dialogContents.addView(contents);
    }

    private void addButtons(LinearLayout buttonContents) {

        SmallButton findButton = new SmallButton(this);
        findButton.setText("FIND");
        findButton.setEnabled(true);
        findButton.setOnClickListener(view -> {
            Toast toast = Toast.makeText(getApplicationContext(), "Finding Room...", Toast.LENGTH_SHORT);
            toast.show();
        });

        buttonContents.addView(findButton);
    }

    private void setStartActivity(Class<?> targetActivity){
        Intent intent = new Intent(Lobby.this, targetActivity);
        startActivity(intent);
    }

}