package com.example.myhomeworkapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_NAME = "currate.db";
    public static  final String TB_NAME = "currate";


    public  DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                     int version){
        super(context, name, factory, version);
    }

    public DBHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE" + TB_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CURNAME TEXT,CURRATE REAL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
