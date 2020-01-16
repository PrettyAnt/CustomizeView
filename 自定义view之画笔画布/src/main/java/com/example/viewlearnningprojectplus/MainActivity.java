package com.example.viewlearnningprojectplus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.viewlearnningprojectplus.activity.CanvasActivity;
import com.example.viewlearnningprojectplus.activity.ClipVaraActivity;
import com.example.viewlearnningprojectplus.activity.LinePathActivity;
import com.example.viewlearnningprojectplus.activity.PaintPathActivity;
import com.example.viewlearnningprojectplus.activity.QuadPathActivity;
import com.example.viewlearnningprojectplus.widget.MyLineView;
import com.example.viewlearnningprojectplus.widget.MyPaintPath;
import com.example.viewlearnningprojectplus.widget.MyQuadView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TextView tv_1 = (TextView) findViewById(R.id.tv_1);
        TextView tv_2 = (TextView) findViewById(R.id.tv_2);
        TextView tv_3 = (TextView) findViewById(R.id.tv_3);
        TextView tv_4 = (TextView) findViewById(R.id.tv_4);
        TextView tv_5 = (TextView) findViewById(R.id.tv_5);
        TextView tv_6 = (TextView) findViewById(R.id.tv_6);
        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        tv_5.setOnClickListener(this);
        tv_6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
                startActivity(PaintPathActivity.class);//通过设置路径(path)/画笔(paint)相关的方法
                break;
            case R.id.tv_2:
                startActivity(LinePathActivity.class);//绘制手滑的路径
                break;
            case R.id.tv_3:
                startActivity(QuadPathActivity.class);//绘制手滑的路径(圆滑版，本质二阶贝塞尔曲线)
                break;
            case R.id.tv_4:
                startActivity(CanvasActivity.class);//
                break;
            case R.id.tv_5:
                startActivity(ClipVaraActivity.class);
                break;
            case R.id.tv_6:

                break;

        }
    }

    private void startActivity(Class<? extends AppCompatActivity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
