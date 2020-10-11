package com.example.bookkeeping;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainClass extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Money>ListMoney;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        findViews();
        setData();
        setRecyclerViewAdapter();
        Mydatabase db=new Mydatabase(MainClass.this,Mydatabase._DBName,null,Mydatabase._DBVersion);

    }

    private void setData() {
        ListMoney=new ArrayList<>();
        ListMoney.add(new Money("收入","薪資20K",R.drawable.in));
        ListMoney.add(new Money("支出","冰沙30",R.drawable.out));
        ListMoney.add(new Money("收入","薪資20K",R.drawable.in));
        ListMoney.add(new Money("支出","冰沙30",R.drawable.out));
        ListMoney.add(new Money("收入","薪資20K",R.drawable.in));
        ListMoney.add(new Money("支出","冰沙30",R.drawable.out));
        ListMoney.add(new Money("收入","薪資20K",R.drawable.in));
        ListMoney.add(new Money("支出","冰沙30",R.drawable.out));
    }

    private void setRecyclerViewAdapter() {

        recyclerView.setLayoutManager(new LinearLayoutManager(MainClass.this, LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(new MyRecyclerViewAdapter(MainClass.this,ListMoney));
    }

    private void findViews() {
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }
}
