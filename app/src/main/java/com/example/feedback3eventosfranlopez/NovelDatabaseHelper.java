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
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NOVELS = "novels";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_IS_FAVORITE = "is_favorite";

    public NovelDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOVELS);
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NOVELS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_IS_FAVORITE + " INTEGER DEFAULT 0)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOVELS);
        onCreate(db);
    }

    public void addNovel(Novel novel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, novel.getTitle());
        values.put(COLUMN_AUTHOR, novel.getAuthor());
        values.put(COLUMN_IS_FAVORITE, novel.isFavorite() ? 1 : 0);
        db.insert(TABLE_NOVELS, null, values);
        db.close();
    }

    public List<Novel> getAllNovels() {
        List<Novel> novelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_NOVELS, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Obtén el índice de cada columna y luego extrae el valor
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                    String author = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR));
                    boolean isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE)) == 1;

                    // Crea una instancia de Novel con los valores obtenidos
                    Novel novel = new Novel(id, title, author, isFavorite);
                    novelList.add(novel);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return novelList;
    }

}

