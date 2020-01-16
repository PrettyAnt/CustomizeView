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
public class MyQuadView extends View {


    private float startX;
    private float startY;

    public MyQuadView(Context context) {
        super(context);
    }

    public MyQuadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyQuadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    Paint paint = new Paint();
    Path path = new Path();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                path.moveTo(startX,startY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (event.getX() + startX) / 2;
                float endY = (event.getY() + startY) / 2;
                path.quadTo(startX, startY, endX, endY);
                startX = event.getX();
                startY = event.getY();
                postInvalidate();
                break;

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        myTouchDrawQuad(canvas);
    }

    /**
     * 绘制自定义手势&结合ontouchevent()
     * 圆滑型
     *
     * @param canvas
     */
    private void myTouchDrawQuad(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(60);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPath(path, paint);
    }


}
