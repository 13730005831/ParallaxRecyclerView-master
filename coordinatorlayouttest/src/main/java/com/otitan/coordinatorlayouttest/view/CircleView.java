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
 * Created by 小眼神 on 2017/9/25.
 */

public class CircleView extends View {
    private static final String TAG = "CircleView";
    private Paint mPaint;
    private Paint sPaint;
    private float[] bigCircleAngle = new float[]{0, 45, 90, 135, 180, 225, 270, 315, 360};
    private ArrayList<Circle> circles = new ArrayList<>();
    private float radius = 200;
    private float bigCircleRadius = 30;
    private float smallCircleRadius = 20;
    private float SCALE_RATE = 0.3F;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        sPaint = new Paint();
        sPaint.setStyle(Paint.Style.FILL);
        sPaint.setColor(Color.GREEN);
        sPaint.setStrokeWidth(10);

        Circle smallCircle = new Circle();
        smallCircle.center = new float[]{radius, 0};
        smallCircle.radius = smallCircleRadius;
        circles.add(smallCircle);

        for (int i = 0; i < bigCircleAngle.length; i++) {
            Circle bigCircle = new Circle();
            bigCircle.radius = bigCircleRadius;
            float x = (float) (Math.cos(bigCircleAngle[i] * Math.PI / 180) * radius);
            float y = (float) (Math.sin(bigCircleAngle[i] * Math.PI / 180) * radius);
            bigCircle.center = new float[]{x, y};
            circles.add(bigCircle);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAxis(canvas);//画坐标轴
        //画大圆
        drawCircles(canvas);
    }

    //画坐标轴
    private void drawAxis(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);

//        canvas.drawCircle(0, 0, radius, mPaint);//轨迹圆
//        canvas.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0, mPaint);//X轴
//        canvas.drawLine(0, -getHeight() / 2, 0, getHeight() / 2, mPaint);//Y轴
    }

    private void drawCircles(Canvas canvas) {
        float angle = mInterpolatedTime * 360;//角度
        Circle smallCircle = circles.get(0);
        smallCircle.center[0] = (float) (Math.cos(angle * Math.PI / 180) * radius);//计算的时候需要转换成弧度
        smallCircle.center[1] = (float) (Math.sin(angle * Math.PI / 180) * radius);
        //画小圆
        canvas.drawCircle(smallCircle.center[0], smallCircle.center[1], smallCircleRadius, sPaint);

        Circle circle1 = circles.get(1);
        Circle circle2 = circles.get(2);
        float s = (circle1.center[0] - circle2.center[0]) * (circle1.center[0] - circle2.center[0]) +
                (circle1.center[1] - circle2.center[1]) * (circle1.center[1] - circle2.center[1]);

        double sqrt = Math.sqrt(s);
        //画大圆
        for (int i = 0; i < bigCircleAngle.length; i++) {
            Log.e(TAG, "angle: " + angle + ",   this:" + bigCircleAngle[i]);
            Circle circleSmall = circles.get(0);
            Circle circleBig = circles.get(i);

            float radius = circleBig.radius;
            float x = circleBig.center[0] - circleSmall.center[0];
            float y = circleBig.center[1] - circleSmall.center[1];

            double distents = Math.sqrt(x * x + y * y);

            //大球放大
            if (distents <= circleBig.radius + circleSmall.radius) {//两球接触,大球放大
                double scale = 1 + SCALE_RATE * (1 - distents / (circleBig.radius + circleSmall.radius));//放大比例
                radius *= scale;
                canvas.drawCircle(circleBig.center[0], circleBig.center[1], radius, sPaint);

            } else {
                canvas.drawCircle(circleBig.center[0], circleBig.center[1], bigCircleRadius, sPaint);
            }


            //控制的坐标
           /* float x1 = circleBig.center[0] + (circleSmall.center[0] - circleBig.center[0]);
            float y1 = circleSmall.center[1] + (circleBig.center[1] - circleSmall.center[1]);*/

            float x1 = (circleBig.center[0] - circleSmall.center[0])/2 + circleSmall.center[0];
            float y1 = (circleSmall.center[1] - circleBig.center[1]) /2 + circleBig.center[1];

            /*if ( distents > bigCircleRadius && distents < sqrt) {//算法不正确
                //有瑕疵
                Path path = new Path();

                path.moveTo((float) (Math.cos(angle + (bigCircleAngle[i] - angle)/2))*smallCircleRadius,
                        (float) (Math.sin(angle + (bigCircleAngle[i] - angle)/2))*smallCircleRadius);

                path.quadTo(x1,y1,(float) (circleBig.center[0] - bigCircleRadius * Math.cos((bigCircleAngle[i] - angle)/2)),
                        (float) (circleBig.center[1] - bigCircleRadius * Math.sin((bigCircleAngle[i] - angle)/2)));

                path.lineTo((float) (circleBig.center[0] + bigCircleRadius * Math.cos((bigCircleAngle[i] - angle)/2)),
                        (float) (circleBig.center[1] + bigCircleRadius * Math.sin((bigCircleAngle[i] - angle)/2)));

                path.quadTo(x1,y1,(float) (Math.cos(angle - (bigCircleAngle[i] + angle)/2))*smallCircleRadius,
                        (float) (Math.sin(angle - (bigCircleAngle[i] + angle)/2))*smallCircleRadius);
                path.close();
                canvas.drawPath(path, sPaint);
            }*/


            //画贝塞尔
            if ( distents > bigCircleRadius && distents < sqrt) {
                //有瑕疵
                Path path = new Path();

                path.moveTo(circleSmall.center[0] + circleSmall.radius, circleSmall.center[1]);
                path.quadTo(x1, y1, circleBig.center[0] + circleBig.radius, circleBig.center[1]);
                path.lineTo(circleBig.center[0] - circleBig.radius, circleBig.center[1]);
                path.quadTo(x1, y1, circleSmall.center[0] - circleSmall.radius, circleSmall.center[1]);
                path.close();

                canvas.drawPath(path, sPaint);
            }

        }

    }


    private class Circle {
        float radius;
        float center[];
    }

    private float mInterpolatedTime;

    public class MoveAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mInterpolatedTime = interpolatedTime;
            Log.e(TAG, "applyTransformation: " + mInterpolatedTime);
            invalidate();
        }
    }

    private void startAnimation() {
        MoveAnimation animation = new MoveAnimation();
        animation.setDuration(3000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        startAnimation(animation);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();


    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
