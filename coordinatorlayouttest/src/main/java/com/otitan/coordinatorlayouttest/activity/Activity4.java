package com.otitan.coordinatorlayouttest.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.otitan.coordinatorlayouttest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity4 extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.btn1)
    Button btn1;
    private PopupWindow window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn, R.id.btn1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:

                showPopup();
                break;
            case R.id.btn1:

                Toast.makeText(getApplicationContext(),"btn1",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showPopup() {
        window = new PopupWindow();
        View view = View.inflate(getApplicationContext(), R.layout.main_popup_view, null);
        Button camera = (Button) view.findViewById(R.id.camera);
        Button photo = (Button) view.findViewById(R.id.photo);
        Button cancel = (Button) view.findViewById(R.id.cancel);

        camera.setOnClickListener(this);
        photo.setOnClickListener(this);
        cancel.setOnClickListener(this);

        window.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setContentView(view);
        window.setOutsideTouchable(true);
//        View rootview = View.inflate(getApplicationContext(),R.layout.activity_4,null);
        window.setAnimationStyle(R.style.popup_anim_style);
        window.setFocusable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(btn, Gravity.BOTTOM, 0, 0);

//        window.showAsDropDown(btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                Toast.makeText(getApplicationContext(),"camert",Toast.LENGTH_SHORT).show();
                window.dismiss();
                break;
            case R.id.photo:
                window.dismiss();
                Toast.makeText(getApplicationContext(),"photo",Toast.LENGTH_SHORT).show();

                break;
            case R.id.cancel:
                window.dismiss();
                Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_SHORT).show();

                break;

        }
    }
}
