package com.example.bookkeeping.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/*資料庫建立和版本管理*/
public class MyBookKeepHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyBookKeepHelper";
    private static int DB_VERSION = 1;
    private static String DB_NAME = "BookKeep.db";
    private static String[] TABLE_NAME = {"BOOKKEEP"};
    private Context context;
//    private static MyBookKeepDBManage dbManage=new MyBookKeepDBManage(context);

    public MyBookKeepHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyBookKeepHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        Log.d(TAG, TAG + "_object create!");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME[0]
                + "( _id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , value INTEGER , type TEXT );";
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME[0]
                + "( _id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , value INTEGER , type TEXT" +
                ",year INTEGER,month INTEGER,date INTEGER);";
        db.execSQL(createTable);


        Log.d(TAG, "資料庫第一次建立");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion >= oldVersion) {
            switch (oldVersion + 1) {
                case 1:
//                    String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME[0]
//                            + "( _id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , value INTEGER , type TEXT );";
//                    db.execSQL(createTable);
                    Log.d(TAG, "資料庫建立");
                case 2:
//                    String createTable2="CREATE TABLE IF NOT EXISTS "+TABLE_NAME[0]
//                            +"( _id INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , value INTEGER , type TEXT" +
//                            ",year INTEGER,month INTEGER,date INTEGER );";
//                    List<Money>moneyList=new ArrayList<>();
//                    Cursor cursor=db.query(TABLE_NAME[0],null,null,null,null,null,null);
//                    if(cursor.moveToFirst()){
//                        while (cursor.moveToNext()){
//                            String title,type;
//                            int value,i=1,img;
//                            title=cursor.getString(cursor.getColumnIndex("title"));
//                            type=cursor.getString(cursor.getColumnIndex("type"));
//                            value=cursor.getInt(cursor.getColumnIndex("value"));
//                            img=type=="收入"?R.drawable.in:R.drawable.out;
//                            moneyList.add(new Money(i,title,String.valueOf(value),type,img,2020,10,21));
//                            i++;
//                        }
//                    }
//                    String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME[0];
//                    db.execSQL(DROP_TABLE);
//                    db.execSQL(createTable2);
//                    for(int i=0;i<moneyList.size();i++){
//
//                        dbManage.insertData(TABLE_NAME[0],moneyList.get(i).getTitle()
//                                ,Integer.parseInt(moneyList.get(i).getSubtitle()),moneyList.get(i).getType()
//                                ,moneyList.get(i).getYear(),moneyList.get(i).getMonth(),moneyList.get(i).getDate());
//                    }
//                    Log.d(TAG,"資料庫第一次更新");
                default:
                    break;
            }
        }
    }
}
