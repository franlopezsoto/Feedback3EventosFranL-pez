package com.example.feedback3eventosfranlopez;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NovelAdapter adapter;
    private NovelDatabaseHelper dbHelper;
    private Button buttonAddBook, buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Aplicar configuración de tema antes de setContentView
        applyUserSettings();

        setContentView(R.layout.activity_main);

        dbHelper = new NovelDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        buttonAddBook = findViewById(R.id.buttonAddBook);
        buttonSettings = findViewById(R.id.buttonSettings);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadNovels();

        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNovelActivity.class);
                startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void applyUserSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.feedback3eventosfranlopez_preferences", MODE_PRIVATE);
        boolean darkMode = sharedPreferences.getBoolean("dark_mode", false);
        if (darkMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.Theme_Feedback3EventosFranLopez);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNovels();
    }

    private void loadNovels() {
        List<Novel> novels = dbHelper.getAllNovels();
        Log.d("MainActivity", "Novels loaded: " + novels.size());

        if (adapter == null) {
            adapter = new NovelAdapter(this, novels);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setNovels(novels);
        }
    }
}
