package com.prettyant.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.prettyant.R;


/**
 * 使用矩阵Matrix进行放缩变换
 */
public class Practice08MatrixScaleView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Matrix matrix = new Matrix();
    private int width;
    private int height;

    public Practice08MatrixScaleView(Context context) {
        super(context);
    }

    public Practice08MatrixScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice08MatrixScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        int x1 = getWidth() / 4;//坐标中心位置
        int y1 = getHeight() / 2;//坐标中心位置
        int x2 = getWidth() * 3 / 4;
        int y2 = y1;

        canvas.save();
        matrix.reset();
        matrix.postScale(0.8f, 1.4f,x1,y1);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, x1-width/2, y1-height/2, paint);//先确定bitmap的坐标位置
        canvas.restore();

        canvas.save();
        matrix.reset();
        matrix.postScale(-0.8f, 1.4f,x2,y2);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, x2-width/2,y2-height/2, paint);
        canvas.restore();
    }
}
