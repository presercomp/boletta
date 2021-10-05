package com.presercomp.boletta.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "boleteador.db";
    protected static final String TABLE_USERS = "tbl_users";
    protected static final String TABLE_TICKETS = "tbl_tickets";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_USERS + "(id_user INTEGER PRIMARY KEY AUTOINCREMENT, mail TEXT NOT NULL, pass TEXT NOT NULL, name TEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_TICKETS+ "(folio INTEGER PRIMARY KEY AUTOINCREMENT, id_user INTEGER NOT NULL, date TEXT NOT NULL, amount INTEGER NOT NULL, active INTEGER NOT NULL);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_TICKETS);
        onCreate(sqLiteDatabase);
    }
}
