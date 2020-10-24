package com.example.bookkeeping;

import android.content.Context;
import android.database.Cursor;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*資料庫內容型態轉換工具*/
public class DBTool {
    //    資料更新步驟:新增資料至資料庫X-查詢所有資料-轉換資料-顯示資料
    private MyBookKeepDBManage manage;
    private List<Money> moneyList;

    /*更新畫面-資料庫1.0-資料內容(ID 標題 金額 型態 圖片)*/
    public static void updateRecyclerView(Context context, MyBookKeepDBManage manage,
                                          RecyclerView recyclerView, String tablename, FragmentManager fragmentManager) {
        Cursor cursor;
        List<Money> moneyList = new ArrayList<>();
        int img[] = new int[]{R.drawable.in, R.drawable.out};
//        查詢資料
        cursor = manage.selectAllData(tablename);
//        轉換資料(money)
        if (cursor.moveToFirst()) {
            do {
                int DB_id = cursor.getInt(cursor.getColumnIndex("_id"));
                String DBtitle = cursor.getString(cursor.getColumnIndex("title"));
                int DBvalue = cursor.getInt(cursor.getColumnIndex("value"));
                String DBtype = cursor.getString(cursor.getColumnIndex("type"));
                int image = DBtype.equals("收入") ? img[0] : img[1];
                moneyList.add(new Money(DB_id, DBtitle, String.valueOf(DBvalue), DBtype, image));
            } while (cursor.moveToNext());
        }
//        顯示資料
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MyRecyclerViewAdapter(context, moneyList, manage, fragmentManager, recyclerView));
    }

    /*更新畫面-資料庫2.0-資料內容(ID 標題 金額 型態 圖片 日期)*/
    public static void updateRecyclerView2(Context context, MyBookKeepDBManage manage,
                                           RecyclerView recyclerView, String tablename, FragmentManager fragmentManager) {
        Cursor cursor;
        List<Money> moneyList = new ArrayList<>();
        int img[] = new int[]{R.drawable.in, R.drawable.out};
//        查詢資料
        cursor = manage.selectAllData(tablename);
//        轉換資料(money)
        if (cursor.moveToFirst()) {
            do {
                int DB_id = cursor.getInt(cursor.getColumnIndex("_id"));
                String DBtitle = cursor.getString(cursor.getColumnIndex("title"));
                int DBvalue = cursor.getInt(cursor.getColumnIndex("value"));
                String DBtype = cursor.getString(cursor.getColumnIndex("type"));
                int image = DBtype.equals("收入") ? img[0] : img[1];
                int year = cursor.getInt(cursor.getColumnIndex("year"));
                int month = cursor.getInt(cursor.getColumnIndex("month"));
                int date = cursor.getInt(cursor.getColumnIndex("date"));
                moneyList.add(new Money(DB_id, DBtitle, String.valueOf(DBvalue),
                        DBtype, image, year, month, date));
            } while (cursor.moveToNext());
        }
//        顯示資料
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MyRecyclerViewAdapter(context, moneyList, manage, fragmentManager, recyclerView));
    }

    /*按照日期(Date)更新畫面-資料庫2.0-資料內容(ID 標題 金額 型態 圖片 日期)*/
    public static void updateRecyclerView(Context context, MyBookKeepDBManage manage,
                                          RecyclerView recyclerView, int selected_attr, String tablename, FragmentManager fragmentManager) {
        Cursor cursor;
        List<Money> moneyList = new ArrayList<>();
        int img[] = new int[]{R.drawable.in, R.drawable.out};
//        查詢資料
        cursor = manage.selectDate(tablename, selected_attr);
//        轉換資料(money)
        if (cursor.moveToFirst()) {
            do {
                int DB_id = cursor.getInt(cursor.getColumnIndex("_id"));
                String DBtitle = cursor.getString(cursor.getColumnIndex("title"));
                int DBvalue = cursor.getInt(cursor.getColumnIndex("value"));
                String DBtype = cursor.getString(cursor.getColumnIndex("type"));
                int image = DBtype.equals("收入") ? img[0] : img[1];
                moneyList.add(new Money(DB_id, DBtitle, String.valueOf(DBvalue), DBtype, image));
            } while (cursor.moveToNext());
        }
//        顯示資料
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MyRecyclerViewAdapter(context, moneyList, manage, fragmentManager, recyclerView));
    }
}
