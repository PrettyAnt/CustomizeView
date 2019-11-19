package com.prettyant.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.prettyant.R;


/**
 * 裁剪path,可以定制多种路径
 *
 * 要求： 将图片置于中心位置，两种方式裁剪；裁剪圆的半径为图形边长的一半
 */
public class Practice02ClipPathView extends View {
    Paint paint = new Paint();
    Bitmap bitmap;
    private Point point1;
    private Point point2;
    private Path path1 = new Path();
    private Path path2 = new Path();

    public Practice02ClipPathView(Context context) {
        super(context);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        point1 = new Point(getWidth()/4, getHeight() / 2);
        point2 = new Point(3 * getWidth() / 4, getHeight() / 2);

        path1.addCircle(point1.x, point1.y, bitmap.getWidth()/2, Path.Direction.CW);
        path2.addCircle(point2.x, point2.y, bitmap.getWidth() / 2, Path.Direction.CW);
        path2.setFillType(Path.FillType.INVERSE_WINDING);


        canvas.save();
        canvas.clipPath(path1);
        canvas.drawBitmap(bitmap, point1.x-bitmap.getWidth()/2, point1.y-bitmap.getHeight()/2, paint);
        canvas.restore();


        canvas.save();
        canvas.clipPath(path2);
        canvas.drawBitmap(bitmap, point2.x - bitmap.getWidth() / 2, point2.y - bitmap.getHeight() / 2, paint);
        canvas.restore();
    }
}
