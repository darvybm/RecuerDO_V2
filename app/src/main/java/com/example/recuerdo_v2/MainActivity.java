package com.example.recuerdo_v2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import com.example.recuerdo_v2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button btnRecyclerToDo;
    private Button btnListViewToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnRecyclerToDo = findViewById(R.id.btnRecyclerToDo);
        btnRecyclerToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerToDoActivity.class);
                startActivity(intent);
            }
        });
    }
}