package com.gjsk.lootify.title;

import android.content.Intent;
import android.os.Bundle;

import com.gjsk.lootify.BaseActivity;

public class StartTitle extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.gjsk.lootify.databinding.ActivityStartTitleBinding binding = com.gjsk.lootify.databinding.ActivityStartTitleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startScreen.setOnClickListener(view -> {
            Intent intent = new Intent(StartTitle.this, Lobby.class);
            startActivity(intent);
        });
    }
}