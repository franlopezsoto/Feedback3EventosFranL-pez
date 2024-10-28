package com.example.feedback3eventosfranlopez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NovelasDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "novelas";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "titulo";
    private static final String COLUMN_AUTHOR = "autor";
    private static final String COLUMN_REVIEW = "resena";
    private static final String COLUMN_FAVORITE = "favorito";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_AUTHOR + " TEXT, "
                + COLUMN_REVIEW + " TEXT, "
                + COLUMN_FAVORITE + " INTEGER DEFAULT 0)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNovela(Novela novela) {
        // Código para añadir novela
    }

    public List<Novela> getAllNovelas() {
        // Código para obtener todas las novelas
        return java.util.Collections.emptyList();
    }



    // Métodos adicionales para actualizar y eliminar novelas
}
