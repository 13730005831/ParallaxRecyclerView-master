package com.otitan.coordinatorlayouttest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.ArrayList;

/**
 * 佛祖保佑,永无BUG
 * Created by 小眼神 on 2017/9/22.
 */

public class BallView extends View {

    private Paint paint;
    private float radius2;

    public BallView(Context context) {
        this(context, null);
    }

    public BallView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private float ITEM_DIVIER = 60;
    private float SCALE_RATE = 0.3F;
    private float radius = 30;
    private int ITEM_COUNT = 6;
    private ArrayList<Circle> circles = new ArrayList();
    private float mMaxLength;

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        Circle circle = new Circle();
        circle.radius = radius * 3 / 4;
        circle.center = new float[]{radius, radius * (1f + SCALE_RATE)};
        circles.add(circle);

        for (int i = 1; i < ITEM_COUNT; i++) {
            Circle circle2 = new Circle();
            circle2.radius = radius;
            circle2.center = new float[]{(radius * 2 + ITEM_DIVIER) * i, radius * (1f + SCALE_RATE)};
            circles.add(circle2);
        }

        mMaxLength = (radius *2 + ITEM_DIVIER) * ITEM_COUNT;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画小圆
        Circle circleSmall = circles.get(0);
        circleSmall.center[0] =  mInterpolatedTime * mMaxLength;
        canvas.drawCircle(circleSmall.center[0],circleSmall.center[1],radius * 3 / 4,paint);

        for (int i = 1; i < circles.size(); i++) {
            drawBigCircle(canvas,i);
        }

    }

    private static final String TAG = "BallView";
    private void drawBigCircle(Canvas canvas,int i) {
        Circle circleSmall = circles.get(0);
        Circle circleBig = circles.get(i);

        radius2 = circleBig.radius;
        float distant = Math.abs(circleBig.center[0] - circleSmall.center[0]);

        if (distant <= this.radius + this.radius *3 /4){//接触了,大球放大
            float scale = 1 + SCALE_RATE * ( 1 - distant / (circleBig.radius + circleSmall.radius));//放大比例
            radius2 *= scale;
            canvas.drawCircle(circleBig.center[0],circleBig.center[1], radius2,paint);

        }else{//分离
            canvas.drawCircle(circles.get(i).center[0],circles.get(i).center[1],circles.get(i).radius,paint);
        }

        // 贝塞尔
        if (distant > radius2 && distant < ITEM_DIVIER + radius + radius2){
            Path path = new Path();
            path.moveTo(circleSmall.center[0],circleSmall.center[1] - radius * 3 / 4);
            path.quadTo((circleSmall.center[0] + circleBig.center[0]) / 2,circleSmall.center[1],
                    circleBig.center[0],circleBig.center[1] - radius);

            path.lineTo(circleBig.center[0],circleBig.center[1] + circleBig.radius);

            path.quadTo((circleSmall.center[0] + circleBig.center[0]) / 2,circleSmall.center[1],
                    circleSmall.center[0],circleSmall.center[1] + circleSmall.radius);
            path.close();
            canvas.drawPath(path,paint);
        }

    }

    private float mInterpolatedTime;
    public class MoveAnimation extends Animation{
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mInterpolatedTime = interpolatedTime;
            Log.e(TAG, "applyTransformation: " + mInterpolatedTime );
            invalidate();
        }
    }

    private void startAnimation(){
        MoveAnimation animation = new MoveAnimation();
        animation.setDuration(3000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        startAnimation(animation);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    public class Circle {
        float radius;
        float center[];
    }
}
