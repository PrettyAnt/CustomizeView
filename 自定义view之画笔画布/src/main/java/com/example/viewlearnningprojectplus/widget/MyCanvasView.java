package com.example.viewlearnningprojectplus.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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
public class MyCanvasView extends View {


    public MyCanvasView(Context context) {
        super(context);
    }

    public MyCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCanvasView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasMethods(canvas);
    }

    private void canvasMethods(Canvas canvas) {
        canvas.drawPath(getPath(), getPaint());
        Paint paint = getPaint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        canvas.drawPath(getPath2(), paint);
    }

    /**
     * 绘制路径
     *
     * @return
     */
    private Path getPath() {
        Path path = new Path();
        path.moveTo(100, 300);
        path.rLineTo(100, 300);
        path.rLineTo(100, -400);
        path.rLineTo(100, 200);
        path.rLineTo(100, -300);
        path.rLineTo(200, 400);
        path.rLineTo(50, -400);
        path.rLineTo(150, 500);

//        path.lineTo(200, 600);
//        path.lineTo(300, 200);
//        path.lineTo(400, 400);
//        path.lineTo(500, 100);
//        path.lineTo(700, 500);
//        path.lineTo(750, 100);
//        path.lineTo(900, 600);
        return path;
    }

    /**
     * 绘制路径
     *
     * @return
     */
    private Path getPath2() {
        Path path = new Path();
        path.moveTo(100, 300);
        path.lineTo(200, 600);
        path.lineTo(300, 200);
        path.lineTo(400, 400);
        path.lineTo(500, 100);
        path.lineTo(700, 500);
        path.lineTo(750, 100);
        path.lineTo(900, 600);
        return path;
    }


    /**
     * 获取画笔
     *
     * @return
     */
    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        return paint;
    }


}
