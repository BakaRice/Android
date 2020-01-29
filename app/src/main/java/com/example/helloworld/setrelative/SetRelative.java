package com.example.helloworld.setrelative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.Utils.ApiFunctionUtils;
import com.example.helloworld.test.MainActivity_first;

public class SetRelative extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_relative);
        //填充list
        ListView listview=(ListView)findViewById(R.id.list_items4);
        SetRelativeListAdapter myListAdapter=new SetRelativeListAdapter(this, ApiFunctionUtils.getInstance().getContects(this));
        listview.setAdapter(myListAdapter);
         //返回按钮
        TextView bt_share = findViewById(R.id.study_cancel4);
        bt_share.setOnClickListener(v -> {
//            Intent intent = new Intent(SetRelative.this, MainActivity_first.class);
//            startActivity(intent);
            finish();
        });
        //添加联系人按钮
        Button addcontects= findViewById(R.id.button);
        addcontects.setOnClickListener(v -> {
            Intent intent = new Intent(SetRelative.this, ViewContects.class);
            startActivity(intent);
        });

    }
}
