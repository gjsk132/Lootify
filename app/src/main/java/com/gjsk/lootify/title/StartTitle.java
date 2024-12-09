package com.gjsk.lootify.title;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class StartTitle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.gjsk.lootify.databinding.ActivityStartTitleBinding binding = com.gjsk.lootify.databinding.ActivityStartTitleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startScreen.setOnClickListener(view->{
            Intent intent = new Intent(StartTitle.this, Lobby.class);
            startActivity(intent);
        });
    }
}