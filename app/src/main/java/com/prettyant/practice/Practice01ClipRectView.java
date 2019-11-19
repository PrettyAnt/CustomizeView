package com.prettyant.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.prettyant.R;


/**
 * 裁剪
 *
 *
 * 为了直观，先让view居中显示，则可以确定左上角的坐标位置
 */
public class Practice01ClipRectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private int left;
    private int top;
    private int width;
    private int height;

    public Practice01ClipRectView(Context context) {
        super(context);
    }

    public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        Log.i("wangbei:", "getWidth() :"+getWidth()+"width:" + width + "   height:" + height + "   left:" + left + "  top:" + top);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        left = (getWidth() - width) / 2;
        top = (getHeight() - height) / 2;

        canvas.save();

        /**
         * question: 裁剪图片中心位置，使得边长变为原来的1/2 ?
         */
        canvas.clipRect(left + width / 4, top + height / 4, left + 3 * width / 4, top + 3 * height / 4);
        canvas.drawBitmap(bitmap, left, top, paint);
        canvas.restore();

    }
}
