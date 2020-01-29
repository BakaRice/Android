package com.example.helloworld.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;
import com.example.helloworld.Utils.RequestCourseUtils;
import com.example.helloworld.circle.CircleActivity;
import com.example.helloworld.circle.ShareActivity;
import com.example.helloworld.learn.MainActivity;
import com.example.helloworld.location.Main5MapActivity;
import com.example.helloworld.setrelative.SetRelative;

public class MainActivity_first extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取数据
//          RequestCourseUtils.getInstance().gethttpresult("http://114.115.169.146:8080/queryCourseList");
//        System.out.println("gethttpresult的结果"+RequestCourseUtils.getInstance().gethttpresult("http://114.115.169.146:8080/queryCourseList"));
        RequestCourseUtils.getInstance().okhttpDate(getString(R.string.CourseUrl));//测试成功

        Button button2=(Button) findViewById(R.id.bt_1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_first.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button button1=(Button) findViewById(R.id.bt_4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_first.this, CircleActivity.class);
                startActivity(intent);
            }
        });
        Button button3=(Button) findViewById(R.id.bt_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_first.this, Main5MapActivity.class);
                startActivity(intent);
            }
        });
        Button button5=(Button) findViewById(R.id.bt_5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_first.this, SetRelative.class);
                startActivity(intent);
            }
        });


        }
}
