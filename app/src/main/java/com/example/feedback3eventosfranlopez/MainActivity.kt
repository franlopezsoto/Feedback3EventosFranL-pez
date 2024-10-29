package com.example.feedback3eventosfranlopez

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NovelaAdapter
    private lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = SQLiteHelper(this)
        recyclerView = findViewById(R.id.recyclerViewNovelas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar y mostrar las novelas
        val novelas = dbHelper.getAllNovelas()
        adapter = NovelaAdapter(novelas, this)
        recyclerView.adapter = adapter
    }
}
