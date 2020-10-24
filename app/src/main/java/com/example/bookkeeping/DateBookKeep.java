package com.example.bookkeeping;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookkeeping.DataBase.MyBookKeepDBManage;
import com.example.bookkeeping.Tool.DBTool;

import java.util.Calendar;
import java.util.List;

/*查看今日收支*/
public class DateBookKeep extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Money> ListMoney;
    private MyBookKeepDBManage manageDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        findViews();
        openDBManage();
        DBTool.updateRecyclerView(DateBookKeep.this,manageDB,recyclerView,getToday(),"BOOKKEEP",getSupportFragmentManager());
        setTitle(getMonth()+"月"+getToday()+"日收支表");
    }

    private int getToday() {
        Calendar calendar=Calendar.getInstance();
        int date=calendar.get(Calendar.DAY_OF_MONTH);
        return date;
    }
    private int getMonth() {
        Calendar calendar=Calendar.getInstance();
        int date=calendar.get(Calendar.MONTH);
        return ++date;
    }

    private void openDBManage() {
        manageDB = new MyBookKeepDBManage(DateBookKeep.this);
    }

    private void findViews() {
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
    }
}
