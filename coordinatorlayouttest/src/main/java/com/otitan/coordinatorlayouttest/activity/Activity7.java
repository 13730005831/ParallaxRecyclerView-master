package com.otitan.coordinatorlayouttest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.otitan.coordinatorlayouttest.MyApplication;
import com.otitan.coordinatorlayouttest.R;

public class Activity7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);
        MyApplication application = new MyApplication();
        int anInt = application.getInt();
    }
}
