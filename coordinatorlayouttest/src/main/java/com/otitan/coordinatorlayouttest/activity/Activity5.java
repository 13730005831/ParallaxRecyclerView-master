package com.otitan.coordinatorlayouttest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.otitan.coordinatorlayouttest.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Activity5 extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.tv)
    TextView tv;
    private static final String TAG = "Activity5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        ButterKnife.bind(this);

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_MOVE:
                        int width = v.getWidth();
                        int height = v.getHeight();
//                        Log.e(TAG, "onTouch: width:" + width +",height:" + height );
                        //改变Button的位置

                        float rawX = event.getRawX();
                        float x = event.getX();
                        float v1 = rawX + x;
                        float rawY = event.getRawY();
                        float y = event.getY();
                        float v2 = rawY + y;
                        float x1 = v.getX();
                        float y1 = v.getY();
                        Log.e(TAG, "onTouch: rawX:" + rawX +",rawY:" + rawY );
                        Log.e(TAG, "onTouch: x:" + x +",y:" + y );
//                        Log.e(TAG, "onTouch: v1:" + v1 + "v2:" + v2);
//                        Log.e(TAG, "onTouch: x1:" + x1 + "y1:" + y1);
                        Log.e(TAG, "onTouch: ------------------------------------------------" );
//                        v.setX(event.getRawX() - v.getWidth());
//                        v.setY(event.getRawY() - v.getHeight());
                        //会使view 向下便宜view的高度
                        v.setX(event.getRawX());
                        v.setY(event.getRawY() );

                        break;
                }
                return true;
            }
        });

    }
}
