package com.example.helloworld.circle;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;
import com.example.helloworld.circle.utility.database.db_fruit;

import java.util.Date;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        final EditText textView = (EditText)findViewById(R.id.share_content_text);

        TextView bt_cancel = (TextView)findViewById(R.id.share_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ShareActivity.this, CircleActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        TextView bt_share = (TextView)findViewById(R.id.share_out);
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("testview",textView.getText().toString());
                /**
                 * 使用LitePal进行数据插入
                 * */
                final db_fruit fruit_db = new db_fruit();
                Date date = new Date();

                fruit_db.setUserId(10005);
                fruit_db.setName("dbname测试2");
                fruit_db.setContent(textView.getText().toString());
                fruit_db.setComment("db静态评论测试");
                fruit_db.setFruitId("dbid测试1");
                //fruit_db.setContent_imgId(R.drawable.img_4);
                fruit_db.setImageId(R.drawable.img_1);
                fruit_db.setLiked(1);
                fruit_db.setSendTime(date);
                fruit_db.save();
                //Log.e("share界面",fruit_db.getContent());
                Intent intent = new Intent(ShareActivity.this, CircleActivity.class);
                startActivity(intent);
            }
        });

    }
}
