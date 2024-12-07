package com.gjsk.lootify.title;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;;import com.gjsk.lootify.R;
import com.gjsk.lootify.customview.DialogBase;
import com.gjsk.lootify.customview.SmallButton;

public class Lobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.gjsk.lootify.databinding.ActivityLobbyBinding binding = com.gjsk.lootify.databinding.ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DialogBase dialogBase = binding.dialogBase;

        addButtons(dialogBase);
    }

    private void addButtons(DialogBase dialogBase) {
        LinearLayout buttonContents = findViewById(R.id.button_contents);

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