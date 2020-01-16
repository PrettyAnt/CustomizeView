package com.example.viewlearnningprojectplus.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author ChenYu
 * Author's github https://github.com/PrettyAnt
 * <p>
 * Created on 16:13  2019-08-15
 * PackageName : com.example.androidapilearnning.widget
 * describle :
 */
public class MyLineView extends View {


    public MyLineView(Context context) {
        super(context);
    }

    public MyLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    Paint paint = new Paint();
    Path path = new Path();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                postInvalidate();
                break;

        }

        return super.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        myTouchDrawLine(canvas);
    }

    /**
     * 绘制自定义手势&结合ontouchevent()
     * 直线型
     *
     * @param canvas
     */
    private void myTouchDrawLine(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(6);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, paint);
    }


}
