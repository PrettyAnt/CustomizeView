package com.example.prettyant.practice;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.prettyant.R;

/**
 * 实现翻页效果
 */
public class Practice14FlipboardView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera = new Camera();
    int degree;
    ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 180, 360);

    public Practice14FlipboardView(Context context) {
        super(context);
    }

    public Practice14FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice14FlipboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

        animator.setDuration(2500);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    @SuppressWarnings("unused")
    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - bitmapWidth / 2;
        int y = centerY - bitmapHeight / 2;

        //绘制左半部分
        canvas.save();
        canvas.clipRect(x, y, x + bitmapWidth / 2, y + bitmapHeight);
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();

        //绘制右半部分
        canvas.save();
        camera.save();
        if (degree > 270) {
            canvas.clipRect(x + bitmapWidth / 2, y, x + bitmapWidth, y + bitmapHeight);
        } else {
            canvas.clipRect(x, y, x + bitmapWidth / 2, y + bitmapHeight);
        }
        Log.w("wangbei", "onDraw: "+camera.getLocationX()+"---"+camera.getLocationY()+"---"+camera.getLocationZ() );
//        camera.translate(0,0,-80);

        camera.rotateY(degree);
        canvas.translate(centerX, centerY);
        camera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        camera.restore();


        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();


//        canvas.save();
//
//        camera.save();
//        camera.rotateX(degree);
//        canvas.translate(centerX, centerY);
//        camera.applyToCanvas(canvas);
//        canvas.translate(-centerX, -centerY);
//        camera.restore();
//
//        canvas.drawBitmap(bitmap, x, y, paint);
//        canvas.restore();
    }
}