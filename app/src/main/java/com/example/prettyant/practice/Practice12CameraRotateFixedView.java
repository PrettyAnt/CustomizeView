package com.example.prettyant.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.prettyant.R;

/**
 * camera的坐标系为三维坐标系
 * //                       X轴右+左-
 * //                       Y轴上+下-
 * //                       Z轴内+外-
 * //       camera的轴心永远是原点
 * //       使用旋转后，要使图形对称，则需要移动Canvas将绘制内容的中心移动到原点再旋转，旋转完毕后再移动Canvas将绘制内容移动回来
 * //       注意得反着写，Canvas的变换顺序是反着的!
 * //
 * //       旋转方向:   X轴左手定则
 * //                  Y轴右手定则
 * //                  Z轴左手定制
 * //                                         Y+
 * //                                       ^           Z+
 * //                                       |         /
 * //                                       |        /
 * //                                       |       /
 * //                                       |      /
 * //                                       |     /
 * //                                       |    /
 * //                                       |   /
 * //                                       |  /
 * //                                       | /
 * //                                       |/
 * //         X-  --------------------------X----------------------->  X+
 * //                                      /|0
 * //                                     / |
 * //                                    /  |
 * //                                   /   |
 * //                                  /    |
 * //                                 /     |
 * //                                /      |
 * //                               /       |
 * //                              /        |
 * //                             / Z-      |Y-
 * //
 * //   Camera的移动会带着整个三维坐标系一起移动，而且在做变换结束之后才会做投影，而不是一旋转就立即投影到Canvas上去
 * //
 * //
 * //
 * //====================================== 华丽的分割线 ======================================
 * canvas的坐标系与view一样，是一个二维坐标系
 * //                                      X轴右+左-
 * //                                      Y轴下正上-
 * //                                         ----------------------->  X+
 * //                                        |0
 * //                                        |
 * //                                        |
 * //                                        |
 * //                                        |
 * //                                        |
 * //                                        |
 * //                                        |
 * //                                        |
 * //                                        | Y+
 * //
 */
public class Practice12CameraRotateFixedView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private Point point1 = new Point(200, 200);
    private Point point2 = new Point(600, 200);
    private Camera camera = new Camera();
    private Matrix matrix = new Matrix();
    private int width;
    private int height;

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centX1 = getWidth() / 4;
        int centY1 = getHeight() / 2;
        int centX2 = getWidth() * 3 / 4;
        int centY2 = centY1;


        canvas.save();//保存Canvas的状态
        matrix.reset();
        camera.save();//保存Camera的状态
        camera.rotateX(45);//旋转Camera的三维空间
        camera.getMatrix(matrix);
        camera.restore();//恢复Camera的状态
        matrix.preTranslate(-centX1, -centY1);//旋转之前把绘制内容移到原点
        matrix.postTranslate(centX1, centY1);//旋转之后把投影移动回来
        canvas.concat(matrix);//关联起来
        canvas.drawBitmap(bitmap, centX1 - width / 2, centY1 - height / 2, paint);//drawBitmap方法绘制的位置，left--&--top
        canvas.restore();

        canvas.save();//保存Canvas的状态
        matrix.reset();
        camera.save();//保存Camera的状态
        camera.rotateY(45);//旋转Camera的三维空间
        camera.getMatrix(matrix);
        camera.restore();//恢复Camera的状态
        matrix.preTranslate(-centX2, -centY2);//旋转之前把绘制内容移到原点
        matrix.postTranslate(centX2, centY2);//旋转之后把投影移动回来
        canvas.concat(matrix);//关联起来
        canvas.drawBitmap(bitmap, centX2 - width / 2, centY2 - height / 2, paint);//drawBitmap方法绘制的位置，left--&--top
        canvas.restore();


    }
}
