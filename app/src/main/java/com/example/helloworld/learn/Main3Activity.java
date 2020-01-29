package com.example.helloworld.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.test.MainActivity_first;

import java.util.List;

public class Main3Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent i = getIntent();
//        Bundle b = getIntent().getExtras();
        int position= i.getIntExtra("position",-1);
        DatabaseService db = new DatabaseService();
//        addview(Database.pageidCourses(position));
        db.deletpartcourse();
        if(db.getpartcouse(position).size()==0){
            db.addpartcou2();
        }
        TextView bt_share = (TextView)findViewById(R.id.study_cancel3);
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(com.example.helloworld.learn.Main3Activity.this, Main2Activity.class);
//                startActivity(intent);
                finish();
            }
        });
        addview( db.getpartcouse(position));
    }
    private void addview(List<PartCourse> courses2s){
        LinearLayout layout=(LinearLayout)findViewById(R.id.linear);
        layout.removeAllViews();
        for (PartCourse i :courses2s) {
          imageView(i.getImageid());
          creatText(i.getText());
        }
    }
//    /**
//     * 创建一个textView，参数为文本框内容
//     */
    private void creatText(String str) {
        LinearLayout layout=(LinearLayout)findViewById(R.id.linear);
        TextView textView = new TextView(this);
        textView.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(str);
        textView.setTextSize(24);
        layout.addView(textView);
    }
//    /**
//     * 创建一个iamgeview，参数为图片名  imageid =R.drawable.image
//     */
    private void imageView(int imageid) {
        LinearLayout layout=(LinearLayout)findViewById(R.id.linear);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(imageid);
        layout.addView(imageView);
    }
}
