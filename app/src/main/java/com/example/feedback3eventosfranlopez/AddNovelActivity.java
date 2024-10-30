package com.example.feedback3eventosfranlopez;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNovelActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextAuthor;
    private NovelDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_novel);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        dbHelper = new NovelDatabaseHelper(this);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String author = editTextAuthor.getText().toString();

                if (!title.isEmpty() && !author.isEmpty()) {
                    // Crear la novela sin el `id`
                    Novel novel = new Novel(title, author, false);  // `false` para no favorito inicialmente
                    dbHelper.addNovel(novel);
                    Toast.makeText(AddNovelActivity.this, "Novela agregada", Toast.LENGTH_SHORT).show();
                    finish();  // Regresa a la pantalla principal
                } else {
                    Toast.makeText(AddNovelActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
            private void saveNovel() {
                String title = editTextTitle.getText().toString();
                String author = editTextAuthor.getText().toString();

                if (title.isEmpty() || author.isEmpty()) {
                    Toast.makeText(AddNovelActivity.this, "Por favor ingrese título y autor.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Novel novel = new Novel(title, author, false);
                dbHelper.addNovel(novel);
                Toast.makeText(AddNovelActivity.this, "Por favor ingrese título y autor.", Toast.LENGTH_SHORT).show();

                finish(); // Cerrar la actividad después de guardar
            }

        });
    }
}

