package com.example.feedback3eventosfranlopez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class NovelDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "novel_db";
    private static final int DATABASE_VERSION = 2; // Cambia la versión para aplicar el método onUpgrade

    private static final String TABLE_NOVELS = "novels";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_IS_FAVORITE = "is_favorite"; // El nombre de columna no puede tener espacios

    public NovelDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NOVELS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " + // Añadir coma al final
                COLUMN_IS_FAVORITE + " INTEGER DEFAULT 0" + // Declaración correcta de la columna `is_favorite`
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) { // Aumenta la versión de la base de datos si es necesario
            db.execSQL("ALTER TABLE " + TABLE_NOVELS + " ADD COLUMN " + COLUMN_IS_FAVORITE + " INTEGER DEFAULT 0");
        }
    }

    // Método para actualizar el estado de favorito
    public void updateFavoriteStatus(int novelId, boolean isFavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_FAVORITE, isFavorite ? 1 : 0);

        db.update(TABLE_NOVELS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(novelId)});
        db.close();
    }

    // Método para agregar una nueva novela
    public void addNovel(Novel novel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, novel.getTitle());
        values.put(COLUMN_AUTHOR, novel.getAuthor());
        values.put(COLUMN_IS_FAVORITE, novel.isFavorite() ? 1 : 0);

        db.insert(TABLE_NOVELS, null, values);
        db.close();
    }

    // Método para obtener todas las novelas
    public List<Novel> getAllNovels() {
        List<Novel> novelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOVELS, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Novel novel = new Novel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE)) == 1
                );
                novelList.add(novel);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return novelList;
    }
}

