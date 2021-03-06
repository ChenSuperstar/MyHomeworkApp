package com.example.myhomeworkapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RateManager {
    private SQLiteDatabase.CursorFactory factory;
    private DBHelper dbHelper;
    private  String TBNAME;

    public  RateManager(Context context){
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;

    }

    public RateManager() {}

    public void add(RateItem rateItem){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("curname", rateItem.getCurName());
        values.put("currate", rateItem.getCurRate());

        db.insert(TBNAME, null, values);
        db.close();
    }

    public  RateItem findById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null,"ID=?", new String[]{String.valueOf(id)},
                null,null,null);

        RateItem item = null;
        if(cursor!=null && cursor.moveToFirst()){
            item = new RateItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
            item.setCurRate(cursor.getFloat(cursor.getColumnIndex("CURRATE")));
            cursor.close();
        }
        db.close();
        return item;

    }

}
