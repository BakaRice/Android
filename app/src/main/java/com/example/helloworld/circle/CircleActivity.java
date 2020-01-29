package com.example.helloworld.circle;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.circle.adapter.CoverFlowAdapter;
import com.example.helloworld.circle.adapter.FruitAdapter;
import com.example.helloworld.circle.entity.Fruit;
import com.example.helloworld.circle.entity.User;
import com.example.helloworld.circle.utility.database.db_fruit;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CircleActivity extends AppCompatActivity {

    private List<Fruit> fruitlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle2);
        //模拟数据建立
        initFruits();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<String> mdatas = new ArrayList<>();
        CoverFlowAdapter coverFlowAdapter = new CoverFlowAdapter(CircleActivity.this);
        FruitAdapter adapter = new FruitAdapter(fruitlist, coverFlowAdapter);
        recyclerView.setAdapter(adapter);



        TextView bt_share = (TextView) findViewById(R.id.bt_share);
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CircleActivity.this, ShareActivity.class);
                startActivity(intent);
            }
        });
        TextView bt_share2 = (TextView) findViewById(R.id.circle_back);
        bt_share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(CircleActivity.this, MainActivity_first.class);
//                startActivity(intent);
                finish();
            }
        });

    }

    private void initFruits() {
        User user = new User("10001", "测试用户1");
        User user1 = new User("10002", "测试用户2");
        Date date = new Date();

        SQLiteDatabase db = LitePal.getDatabase();


        /**
         * 使用LitePal进行数据查询
         */
        List<db_fruit> allfruits = LitePal.order("sendTime DESC").find(db_fruit.class);
        for (db_fruit f : allfruits) {
            //Log.e("db",f.getName());
            Fruit dbitem = new Fruit(user, f.getImageId(), f.getContent(), f.getContent_imgId(), f.getLiked(), f.getComment(), f.getSendTime());
            fruitlist.add(dbitem);
        }
        Log.e("MainTime", date.toString());

        //静态数据
        for (int i = 0; i < 2; i++) {

            Fruit nullitem = new Fruit(user, R.drawable.img_0, "1这是一段正文的测试内容，为了测试老友圈在发送时的正文内容显示是否正常，换行测试，左右边距为30dp",
                    R.drawable.img_0, 1, "评论", date);
            fruitlist.add(nullitem);
            Fruit apple = new Fruit(user1, R.drawable.img_1, "2这是一段正文的测试内容，为了测试老友圈在发送时的正文内容显示是否正常，换行测试，左右边距为30dp",
                    R.drawable.img_1, 1, "评论", date);
            fruitlist.add(apple);
        }
    }
}
