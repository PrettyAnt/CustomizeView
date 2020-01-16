package com.example.viewlearnningprojectplus.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.viewlearnningprojectplus.R;

/**
 * @author ChenYu
 * Author's github https://github.com/PrettyAnt
 * <p>
 * Created on 16:28  2019-10-30
 * PackageName : com.example.viewlearnningprojectplus.widget
 * describle :  范围裁剪&几何变换  注意几何变换是倒叙
 */
public class MyClipVaraView extends View {
    private float left = 600f;
    private float top = 600f;
    private float right = 600f;
    private float bottom = 600f;

    public MyClipVaraView(Context context) {
        super(context);
    }

    public MyClipVaraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyClipVaraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        myClipRect(canvas);  //裁剪
//        myClipPath(canvas);  //裁剪

//        myTranslate(canvas);//平移
//        myRotate(canvas);//旋转
//        myScale(canvas);//放缩
//        mySkew(canvas);//错切


//        myMatrix(canvas);//fixme   使用 Matrix 来做自定义变换
        //使用 Camera 来做三维变换
        myCameraRotate(canvas);//三维旋转

    }

    /**
     * 三维旋转
     */
    private void myCameraRotate(Canvas canvas) {
        Camera camera = new Camera();
        Point point = new Point();
        canvas.save();

        camera.save();//报错Camera的状态
        camera.rotateX(30);//旋转Camera的三维空间
        camera.applyToCanvas(canvas);//把旋转投影到Canvas上
        camera.restore();//恢复Camera的状态

        canvas.drawBitmap(getBitMap(), point.x, point.y, getPaint());
        camera.restore();
    }

    /**
     * @param canvas
     */
    private void myMatrix(Canvas canvas) {
        Matrix matrix = new Matrix();
        float[] pointSrc = {left, top, right, top, left, bottom, right, bottom};
        float[] pointDst = {left - 10, top + 50, right + 120, top - 90, left + 20, bottom + 30, right + 20, bottom + 60};
        matrix.reset();
        matrix.setPolyToPoly(pointSrc, 0, pointDst, 0, 4);
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(getBitMap(), 100, 100, getPaint());
        canvas.restore();
    }

    /**
     * 错切
     *
     * @param canvas
     */
    private void mySkew(Canvas canvas) {
        canvas.save();
        canvas.skew(0f, 0.5f);//x方向 y方向的错切系数
        canvas.drawBitmap(getBitMap(), 20, 20, getPaint());
        canvas.restore();
    }

    /**
     * 放缩
     *
     * @param canvas
     */
    private void myScale(Canvas canvas) {
        canvas.save();
        canvas.scale(0.5f, 1f, 200f, -600f);//X轴放大/缩小的倍数 Y轴放大/缩小的倍数  轴心坐标
        canvas.drawBitmap(getBitMap(), 200, 200, getPaint());
        canvas.restore();
    }

    /**
     * 几何变换--旋转
     *
     * @param canvas
     */
    private void myRotate(Canvas canvas) {
        canvas.save();
        canvas.rotate(45, 600, 600);
        canvas.drawBitmap(getBitMap(), 0, 0, getPaint());
        canvas.restore();
    }

    /**
     * 几何变换--平移
     */
    private void myTranslate(Canvas canvas) {
        Bitmap bitMap = getBitMap();
        canvas.save();
        canvas.translate(-200, 0);
        canvas.drawBitmap(bitMap, 20, 20, getPaint());
        canvas.restore();
    }

    /**
     * 其他形状的裁裁剪
     *
     * @param canvas
     */
    private void myClipPath(Canvas canvas) {
        Path criclePath = getCriclePath();//获取路径
        Point point = new Point();
        Paint paint = getPaint();
        Bitmap bitMap = getBitMap();

        canvas.save();
        canvas.clipPath(criclePath);
        canvas.drawBitmap(bitMap, point.x, point.y, paint);
        canvas.restore();
        //todo
    }


    /**
     * 范围裁剪--一般矩形裁剪
     *
     * @param canvas
     */
    private void myClipRect(Canvas canvas) {
        Paint paint = getPaint();
        Bitmap bitMap = getBitMap();
        canvas.save();
        canvas.clipRect(0, 0, 800, 800);//将当前剪辑与指定的矩形相交，矩形以局部坐标表示。
        canvas.drawBitmap(bitMap, 60, 60, paint);
        canvas.restore();

    }


    /**
     * 回执path的路径
     *
     * @return
     */
    private Path getCriclePath() {
        Path path = new Path();
        path.addCircle(600, 600, 300, Path.Direction.CCW);
        return path;
    }


    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(12);
        return paint;
    }


    private Bitmap getBitMap() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }


}
