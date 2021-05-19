package com.example.hp.myapplication_9;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler  extends SQLiteOpenHelper {
    //DB info
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "info.db";
    //Tables and its columns
    public static final String TABLE_NAME = "Library";
    public static final String COLUMN_RECID = "ID";
    public static final String COLUMN_ID = "Stu_ID";
    public static final String COLUMN_ESPN = "Book_ESPN";
    public static final String COLUMN_mDisplayDate = "Length";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // A SQL statement to create a table of three columns
        String sqlStmt = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_RECID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_ID + " TEXT," +
                COLUMN_ESPN + " TEXT, " +
                COLUMN_mDisplayDate + " TEXT " + ");";
        db.execSQL(sqlStmt);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.d("DB", "The table has been removed!");
        onCreate(db);
    }


}
