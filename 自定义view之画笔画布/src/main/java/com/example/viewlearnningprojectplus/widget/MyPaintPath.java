package com.example.viewlearnningprojectplus.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.ComposeShader;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.viewlearnningprojectplus.R;

/**
 * @author ChenYu
 * Author's github https://github.com/PrettyAnt
 * <p>
 * Created on 16:13  2019-08-15
 * PackageName : com.example.androidapilearnning.widget
 * describle :
 */
public class MyPaintPath extends View {


    public MyPaintPath(Context context) {
        super(context);
    }

    public MyPaintPath(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPaintPath(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    float[] pts = {
            100, 200, 400, 200,
            250, 200, 250, 600,
            100, 400, 400, 400,
            100, 600, 400, 600,
            600, 200, 900, 200,
            600, 200, 600, 600,
            900, 200, 900, 600,

    };
    /**
     * 设置画笔属性
     */
    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.ROUND);//设置折线拐角处是否圆滑
        return paint;
    }

    /**
     * 获取绘制路径-->>折线的路径
     *
     * @return
     */
    private Path getBrokenLinePath() {
        Path path = new Path();
        path.moveTo(200, 100);
        path.lineTo(600, 500);
        path.rLineTo(200, -400);//以自身为参考
        return path;
    }

    /**
     * 画圆
     * @return
     */
    private Path getCriclePath() {
        Path path = new Path();
        path.addCircle(400,500,300, Path.Direction.CCW);
        return path;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        myDrawLines(canvas);
//        myDrawArc(canvas);
//        myDrawRoundRect(canvas);
//        myDrawPath(canvas);
//        myDrawLine(canvas);
//        myDrawQuad(canvas);

//        myDrawDashLine(canvas);//虚线效果
//        myDiscretePathEffect(canvas);//离散效果
//        myStrokeJoin(canvas);//拐角效果
//        myPathDashPathEffect(canvas);//印章路径效果
        myUniqueEffect(canvas);//组合特效
//        myPaintShader(canvas);//给画笔添加渐变色
//        myBitmapShader(canvas);//bitmap着色
    }

    private void myBitmapShader(Canvas canvas) {
        Paint paint = new Paint();

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.d);
        Shader bitmapShader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        Shader bitmapShader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        ComposeShader composeShader = new ComposeShader(bitmapShader1, bitmapShader2, PorterDuff.Mode.SRC_OVER);

        paint.setShader(composeShader);
        canvas.drawCircle(300,300,300,paint);

    }


    /**
     * 给画笔添加渐变色
     * 1、LinearGradient 线性渐变
     * 2、RadialGradient 辐射渐变
     * 3、SweepGradient 扫描渐变
     * @param canvas
     */
    private void myPaintShader(Canvas canvas) {
        Path brokenLinePath = getCriclePath();
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(60);
        canvas.drawPath(brokenLinePath, paint);

        canvas.translate(200, 200);
        Shader linearGradient = new LinearGradient(200,200,200,800,Color.RED,Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        canvas.drawPath(brokenLinePath, paint);

        canvas.translate(-300, 300);
        Shader radialGradient = new RadialGradient(400,500,300,Color.RED,Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(radialGradient);
        canvas.drawPath(brokenLinePath, paint);

        canvas.translate(400, 200);
        Shader sweepGradient = new SweepGradient(400,500,Color.RED,Color.BLUE);
        paint.setShader(sweepGradient);
        canvas.drawPath(brokenLinePath, paint);
    }



    /**
     * 组合特效--->>>paint
     *
     * @param canvas
     */
    private void myUniqueEffect(Canvas canvas) {
        Paint paint = getPaint();
        Path brokenLinePath = getBrokenLinePath();
//        canvas.drawCircle(300,300,200,paint);
//        canvas.drawColor(Color.parseColor("#88880000"));//设置画布(canvas)的颜色
        canvas.drawPath(brokenLinePath, paint);//仅应用原始路径

        canvas.translate(0, 200);
        CornerPathEffect cornerPathEffect = new CornerPathEffect(20);
        paint.setPathEffect(cornerPathEffect);
        canvas.drawPath(brokenLinePath, paint);//仅应用圆角特效路径

        canvas.translate(0, 200);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{60, 40,100, 10}, 0);
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(brokenLinePath, paint);//仅应用虚线路径

        //使用ComposePathEffect先应用圆角特效，再应用虚线特效
        canvas.translate(0, 200);
        ComposePathEffect composePathEffect = new ComposePathEffect(dashPathEffect, cornerPathEffect);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(brokenLinePath, paint);

        //
        canvas.translate(0, 200);
        paint.setStyle(Paint.Style.STROKE);
        SumPathEffect sumPathEffect = new SumPathEffect(cornerPathEffect, dashPathEffect);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(brokenLinePath, paint);


    }

    /**
     * 绘制印章效果 PathDashPathEffect--->>>paint
     *
     * @param canvas
     */
    private void myPathDashPathEffect(Canvas canvas) {
        Paint paint = getPaint();
        Path brokenLinePath = getBrokenLinePath();
        canvas.drawPath(brokenLinePath, paint);

        /**
         *   PathDashPathEffect()
         *   shape:表示印章路径，比如我们下面示例中的三角形加右上角一个点
         *   advance:表示两个印章路径间的距离,很容易理解，印章间距离越大，间距就越大
         *   phase:路径绘制偏移距离，与DashPathEffect中的float phase参数意义相同
         *   style:表示在遇到转角时，如何操作印章以使转角平滑过渡，取值有：Style.ROTATE，Style.MORPH，Style.TRANSLATE
         */
        canvas.translate(0, 200);
        paint.setColor(Color.RED);
        paint.setPathEffect(new PathDashPathEffect(getInnerPath(), 80, 0, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(brokenLinePath, paint);


        canvas.translate(0, 200);
        paint.setColor(Color.BLUE);
        paint.setPathEffect(new PathDashPathEffect(getInnerPath(), 80, 0, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(brokenLinePath, paint);

    }

    /**
     * 绘制内部印章路径--->>>path
     *
     * @return
     */
    private Path getInnerPath() {
        Path innerPath = new Path();
        innerPath.moveTo(30, 0);
        innerPath.lineTo(60, 60);
        innerPath.lineTo(0, 60);
        innerPath.close();
        innerPath.addCircle(30, 30, 10, Path.Direction.CCW);
        return innerPath;
    }

    /**
     * 绘制离散效果 DiscretePathEffect--->>>paint
     *
     * @param canvas
     */
    private void myDiscretePathEffect(Canvas canvas) {
        Paint paint = getPaint();
        Path brokenLinePath = getBrokenLinePath();
        canvas.drawPath(brokenLinePath, paint);

        paint.setPathEffect(new DiscretePathEffect(10, 20));
        canvas.translate(0, 200);
        paint.setColor(Color.RED);
        canvas.drawPath(brokenLinePath, paint);

        paint.setPathEffect(new DiscretePathEffect(10, 20));
        canvas.translate(0, 200);
        paint.setColor(Color.BLUE);
        canvas.drawPath(brokenLinePath, paint);
    }


    /**
     * 绘制虚线 DashPathEffect--->>>paint
     *
     * @param canvas
     */
    private void myDrawDashLine(Canvas canvas) {
        Paint paint = getPaint();
        Path brokenLinePath = getBrokenLinePath();
        canvas.drawPath(brokenLinePath, paint);

        paint.setPathEffect(new DashPathEffect(new float[]{20, 8, 200, 80}, 0));
        canvas.translate(0, 200);
        paint.setColor(Color.RED);
        canvas.drawPath(brokenLinePath, paint);

        paint.setPathEffect(new DashPathEffect(new float[]{20, 8, 200, 80}, 80));
        canvas.translate(0, 200);
        paint.setColor(Color.BLUE);
        canvas.drawPath(brokenLinePath, paint);
    }


    /**
     * 绘制拐角效果 StrokeJoin-->>paint
     *
     * @param canvas
     */
    private void myStrokeJoin(Canvas canvas) {
        Paint paint = getPaint();
        Path brokenLinePath = getBrokenLinePath();
        canvas.drawPath(brokenLinePath, paint);

//        paint.setPathEffect(new DiscretePathEffect(10, 20));
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.translate(0, 200);
        paint.setColor(Color.RED);
        canvas.drawPath(brokenLinePath, paint);

        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.translate(0, 200);
        paint.setColor(Color.BLUE);
        canvas.drawPath(brokenLinePath, paint);
    }

    /**
     * 绘制自定义贝塞尔曲线--->>>path
     *
     * @param canvas
     */
    private void myDrawQuad(Canvas canvas) {
        Paint paint = getPaint();
        Path path = new Path();

        path.moveTo(100, 500);
        path.quadTo(300, 800, 500, 500);
        path.quadTo(700, 200, 900, 500);
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制自定义直线
     *
     * @param canvas
     */
    private void myDrawLine(Canvas canvas) {
        Paint paint = getPaint();
        Path brokenLinePath = getBrokenLinePath();
        canvas.drawPath(brokenLinePath, paint);
    }

    /**
     * 绘制自定义图形--->>>path
     *
     * @param canvas
     */
    private void myDrawPath(Canvas canvas) {
        Paint paint = getPaint();
        Path path = new Path();
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
        path.lineTo(224, 366);
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制圆角矩形--->>>canvas
     *
     * @param canvas
     */
    private void myDrawRoundRect(Canvas canvas) {
        Paint paint = getPaint();
        RectF rectF = new RectF();
        canvas.drawRoundRect(rectF, 100f, 100f, paint);
    }

    /**
     * 绘制直线
     *
     * @param canvas--->>>canvas
     */
    private void myDrawLines(Canvas canvas) {
        Paint paint = getPaint();
        canvas.drawLines(pts, paint);
    }

    /**
     * 绘制弧形
     *
     * @param canvas--->>>canvas
     */
    private void myDrawArc(Canvas canvas) {
        Paint paint = getPaint();
        canvas.drawArc(200f, 200f, 400f, 600f, 0, 90, true, paint);
    }

}
