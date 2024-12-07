package com.gjsk.lootify.title;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.gjsk.lootify.customview.SmallButton;

public class Lobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.gjsk.lootify.databinding.ActivityLobbyBinding binding = com.gjsk.lootify.databinding.ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SmallButton roomFindButton = binding.roomFindButton;

        roomFindButton.setOnClickListener(v->{
            Toast toast = Toast.makeText(getApplicationContext(), "Finding Room...",Toast.LENGTH_SHORT);
            toast.show();
        });
    }
}