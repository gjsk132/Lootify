package com.gjsk.lootify.title;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gjsk.lootify.R;
import com.gjsk.lootify.customview.DialogBase;
import com.gjsk.lootify.customview.SmallButton;
import com.gjsk.lootify.databinding.ActivityLobbyBinding;

public class Lobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ActivityLobbyBinding binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DialogBase dialogBase = binding.dialogBase;

        LinearLayout buttonContents = dialogBase.findViewById(R.id.button_contents);
        addButtons(buttonContents);

        FrameLayout dialogContents = dialogBase.findViewById(R.id.dialog_contents);
        setDialogContents(dialogContents);
    }

    private void setDialogContents(FrameLayout dialogContents){
        LinearLayout contents = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_room_select, dialogContents, false);

        dialogContents.addView(contents);
    }

    private void addButtons(LinearLayout buttonContents) {

        SmallButton findButton = new SmallButton(this);
        findButton.setText("FIND");
        findButton.setEnabled(true);
        findButton.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getApplicationContext(), "Finding Room...", Toast.LENGTH_SHORT);
            toast.show();
        });

        buttonContents.addView(findButton);
    }
}