package com.example.bookkeeping;

import android.content.Context;
import android.database.Cursor;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*資料庫內容型態轉換工具*/
public class DBTool {
//    資料更新步驟:新增資料至資料庫X-查詢所有資料-轉換資料-顯示資料
    private MyBookKeepDBManage manage;
    private List<Money>moneyList;

    public static void updateRecyclerView(Context context,MyBookKeepDBManage manage, RecyclerView recyclerView, String tablename){
        Cursor cursor;
        List<Money>moneyList=new ArrayList<>();
//        查詢資料
        cursor=manage.selectAllData(tablename);
//        轉換資料(money)
        if(cursor.moveToFirst()){
            do {
                String DBtitle=cursor.getString(cursor.getColumnIndex("title"));
                int DBvalue=cursor.getInt(cursor.getColumnIndex("value"));
                String DBtype=cursor.getString(cursor.getColumnIndex("type"));
                String title=DBtype;
                String subtitle=DBtitle+DBvalue;
                int image=R.drawable.in;
                moneyList.add(new Money(title,subtitle,image));
            }while (cursor.moveToNext());
        }
//        顯示資料
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new MyRecyclerViewAdapter(context,moneyList));
    }
}
