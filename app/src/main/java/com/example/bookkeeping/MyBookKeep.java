package com.example.bookkeeping;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MyBookKeep extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 啟動Stetho
        Stetho.initializeWithDefaults(this);
    }
}
