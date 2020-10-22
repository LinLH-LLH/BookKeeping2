package com.example.bookkeeping;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class analysis extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysislayout);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(1000);
        progressBar.setProgress(100);
    }
}
