package com.gjsk.lootify.title;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gjsk.lootify.customview.SmallButton;

public class Lobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.gjsk.lootify.databinding.ActivityLobbyBinding binding = com.gjsk.lootify.databinding.ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SmallButton button1 = binding.button1;
        SmallButton button2 = binding.button2;

        button1.setOnClickListener(v->{
            button1.setEnabled(false);
            button2.setEnabled(true);
        });

        button2.setOnClickListener(v->{
            button1.setEnabled(true);
            button2.setEnabled(false);
        });
    }
}