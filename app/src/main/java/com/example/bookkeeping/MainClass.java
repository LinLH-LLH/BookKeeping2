package com.example.bookkeeping;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainClass extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Money>ListMoney;
    private MyBookKeepDBManage manageDB;
    private NavigationView navigationView;
    private ImageView img;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        findViews();
        openDBManage();
//        setRecyclerViewAdapter();
        DBTool.updateRecyclerView(MainClass.this,manageDB,recyclerView,"BOOKKEEP",getSupportFragmentManager());

    }

    private void openDBManage() {
        manageDB = new MyBookKeepDBManage(MainClass.this);
    }


    private void setRecyclerViewAdapter() {

        recyclerView.setLayoutManager(new LinearLayoutManager(MainClass.this, LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(new MyRecyclerViewAdapter(MainClass.this,ListMoney,manageDB,getSupportFragmentManager(),recyclerView));
    }

    private void findViews() {
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        navigationView=(NavigationView)findViewById(R.id.nv);
        img = (ImageView)navigationView.getHeaderView(0).findViewById(R.id.pic);
        setSelfPicture();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.personal:
                        setHint("個人");
                        break;
                    case R.id.financial:
                        setHint("分析");
                        Intent intent=new Intent(MainClass.this,analysis.class);
                        startActivity(intent);
                        break;
                    case R.id.income:
                        setHint("收入");
                        break;
                    case R.id.expense:
                        setHint("支出");
                        break;
                    case R.id.date:
                        setHint("日期");
                        break;
                    case R.id.month:
                        setHint("月份");
                        break;
                    case R.id.year:
                        setHint("年份");
                        break;
                    case R.id.about:
                        setHint("關於");
                        DialogFragment dialogFragment=new CopyRightDialogFragment();
                        dialogFragment.show(getSupportFragmentManager(),"copyrightLayout");
                        break;
                    default:
                        break;
                }
                return false;
            }

            private void setHint(String text) {
                Toast.makeText(MainClass.this,text,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*圖片前處理*/
    private void setSelfPicture() {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.person_c);
        img.setImageBitmap(BitmapTool.toRoundBitmap(bitmap));
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
        DialogFragment dialogFragment;
        switch (item.getItemId()){
            case R.id.about:
                Toast.makeText(MainClass.this
                        ,getResources().getString(R.string.menu_about)
                        ,Toast.LENGTH_SHORT).show();
                dialogFragment=new CopyRightDialogFragment();
                dialogFragment.show(getSupportFragmentManager(),"copyrightLayout");
                break;
            case R.id.create:
                Toast.makeText(MainClass.this
                        ,getResources().getString(R.string.menu_create)
                        ,Toast.LENGTH_SHORT).show();
                dialogFragment=new CreateItemDialogFragment(manageDB,recyclerView);
                dialogFragment.show(getSupportFragmentManager(),"createLayout");
                break;
            default:
                break;
        }
        return true;
    }
}
