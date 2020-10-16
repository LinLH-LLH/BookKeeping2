package com.example.bookkeeping;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
        setIncome_Expenses();

        setRecyclerViewAdapter();
        Mydatabase db=new Mydatabase(MainClass.this,Mydatabase._DBName,null,Mydatabase._DBVersion);

    }

    private void setIncome_Expenses() {
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


    // create menu on toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    // call menu triger function
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                Toast.makeText(MainClass.this
                        ,getResources().getString(R.string.menu_about)
                        ,Toast.LENGTH_SHORT).show();
            case R.id.create:
                Toast.makeText(MainClass.this
                        ,getResources().getString(R.string.menu_create)
                        ,Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
