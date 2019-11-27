package com.example.prettyant.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.prettyant.R;

/**
 * 三维旋转
 */
public class Practice11CameraRotateView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();

    public Practice11CameraRotateView(Context context) {
        super(context);
    }

    public Practice11CameraRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11CameraRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int width = bitmap.getWidth();
        Log.i("wangbei", "width: "+width);
        Log.i("wangbei1", "x:" + camera.getLocationX() + "------ y:" + camera.getLocationY() + "------ z:" + camera.getLocationZ());
        canvas.save();
        camera.save();
        camera.rotateX(30);
//        camera.translate(-100,-100,0);//不能这么用，camera的移动会带着坐标系一起移动？？
        canvas.translate(point1.x,point1.y);//fixme
        camera.applyToCanvas(canvas);
        canvas.translate(-point1.x-bitmap.getWidth(),-point1.y);//fixme
        camera.restore();
        canvas.drawBitmap(bitmap, point1.x+bitmap.getWidth(), point1.y, paint);

        canvas.restore();
        Log.i("wangbei2", "x:" + camera.getLocationX() + "------ y:" + camera.getLocationY() + "------ z:" + camera.getLocationZ());

        canvas.save();
        camera.save();
        camera.rotateY(30);
        canvas.translate(point2.x,point2.y);//fixme
        camera.applyToCanvas(canvas);
        canvas.translate(-point2.x,-point2.y);//fixme
        camera.restore();
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
        Log.i("wangbei2", "x:" + camera.getLocationX() + "------ y:" + camera.getLocationY() + "------ z:" + camera.getLocationZ());
    }
}
