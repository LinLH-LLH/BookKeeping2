package com.example.bookkeeping;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/*資料庫建立和版本管理*/
public class MyBookKeepHelper extends SQLiteOpenHelper {
    private static final String TAG="MyBookKeepHelper";
    private static int DB_VERSION = 1;
    private static String DB_NAME = "BookKeep.db";
    private static String[] TABLE_NAME={"BOOKKEEP"};

    public MyBookKeepHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyBookKeepHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(TAG,TAG+"_object create!");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE IF NOT EXISTS "+TABLE_NAME[0]
                +"( _id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , value INTEGER , type TEXT );";
        db.execSQL(createTable);
        Log.d(TAG,"資料庫第一次建立");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>=oldVersion){
            switch (oldVersion){
                case 1:
                    String createTable="CREATE TABLE IF NOT EXISTS "+TABLE_NAME[0]
                            +"( _id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , value INTEGER , type TEXT );";
                    db.execSQL(createTable);
                    Log.d(TAG,"資料庫第一次更新");
                default:
                    break;
            }
        }
    }
}
