package com.otitan.coordinatorlayouttest.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 佛祖保佑,永无BUG
 * Created by 小眼神 on 2017/9/21.
 */

public class MyBehavior extends CoordinatorLayout.Behavior<TextView> {

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {

        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        //设置TextView的位置
        child.setX(dependency.getX());
        child.setY(dependency.getY()+200);
        return true;
    }
}
