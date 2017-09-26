package com.otitan.coordinatorlayouttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.otitan.coordinatorlayouttest.activity.Activity1;
import com.otitan.coordinatorlayouttest.activity.Activity2;
import com.otitan.coordinatorlayouttest.activity.Activity3;
import com.otitan.coordinatorlayouttest.activity.Activity4;
import com.otitan.coordinatorlayouttest.activity.Activity5;
import com.otitan.coordinatorlayouttest.activity.Activity6;
import com.otitan.coordinatorlayouttest.activity.Activity7;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, Activity1.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, Activity2.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, Activity3.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(MainActivity.this, Activity4.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(MainActivity.this, Activity5.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(MainActivity.this, Activity6.class));
                break;
            case R.id.btn7:
                startActivity(new Intent(MainActivity.this, Activity7.class));
                break;

        }
    }
}
