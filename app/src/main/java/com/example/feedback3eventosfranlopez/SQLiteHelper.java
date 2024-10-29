package com.example.feedback3eventosfranlopez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
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

    public List<Novela> getAllNovelas() {
        List<Novela> novelas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Novela novela = new Novela(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REVIEW)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FAVORITE)) == 1
                );
                novelas.add(novela);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return novelas;
    }

    public void updateNovela(Novela novela) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAVORITE, novela.isFavorito() ? 1 : 0);
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(novela.getId())});
    }
}

