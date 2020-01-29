package com.example.helloworld.setrelative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.Utils.ApiFunctionUtils;

public class ViewContects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contects);
//返回按钮的设置
        TextView bt_share = findViewById(R.id.study_cancel5);
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ViewContects.this, SetRelative.class);
//                startActivity(intent);
                  finish();
            }
        });
//填充list
        ListView listview= findViewById(R.id.list_items5);
        ViewContectsListAdapter myListAdapter=new ViewContectsListAdapter(this, ApiFunctionUtils.getInstance().getContects(this));
        listview.setAdapter(myListAdapter);
    }
}
