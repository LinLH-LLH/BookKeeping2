package com.example.bookkeeping;

/*透過SqliteDatabase物件操縱資料庫*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyBookKeepDBManage {
    private static MyBookKeepHelper helper;
    private static SQLiteDatabase db;

    public MyBookKeepDBManage(Context context) {
        helper = new MyBookKeepHelper(context);
        db = helper.getWritableDatabase();
        //        get sqlitedatabase object
    }

    /*資料庫2.0 新增資料*/
    public void insertData(String tablename, String title, int value, String type
            , int year, int month, int date) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("value", value);
        values.put("type", type);
        values.put("year", year);
        values.put("month", month);
        values.put("date", date);
        db.insert(tablename, null, values);
    }

    /*資料庫1.0 新增資料*/
    public void insertData(String tablename, String title, int value, String type) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("value", value);
        values.put("type", type);
        db.insert(tablename, null, values);
    }

    public void deleteData(String tablename, int _id) {
        db.delete(tablename, "_id=" + _id, null);
    }

    public void updateData(String tablename, int _id, String title, int value, String type) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("value", value);
        values.put("type", type);
        db.update(tablename, values, "_id=" + _id, null);
    }

    public Cursor selectData(String tablename, int _id) {
        Cursor cursor;
        cursor = db.query(tablename, null, String.valueOf(_id), null, null, null, null);
        return cursor;
    }

    public Cursor selectDate(String tablename, int date) {
        Cursor cursor;
        cursor = db.query(tablename, null, String.valueOf(date), null, null, null, null);
        return cursor;
    }

    public Cursor selectAllData(String tablename) {
        Cursor cursor;
        cursor = db.query(tablename, null, null, null, null, null, null);
        return cursor;
    }

}
