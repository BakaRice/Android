package com.example.helloworld.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;
import com.example.helloworld.circle.CircleActivity;
import com.example.helloworld.circle.ShareActivity;
import com.example.helloworld.test.MainActivity_first;

public class MainActivity extends AppCompatActivity  {
    private  int[] images = new int[]{R.drawable.wechat,R.drawable.qq,R.drawable.zhifubao,R.drawable.taobao};
    private String[] data={"微信入门","QQ入门","支付宝入门","淘宝入门"};
    private String[] context={"  超过十亿人使用的手机应用 支持发送语音短信、视频、图片和文字 可以群聊，仅耗少量流量，适合大部分智能手机。",
            "  8亿人在用的即时通讯软件,你不仅可以在各类通讯终端上通过QQ聊天交友,还能进行免费的视频、语音通话,或者随时随地收发重要文件。",
    "  全球领先的独立第三方支付平台,致力于为广大用户提供安全快速的电子支付/网上支付/安全支付/手机支付体验,及转账收款/水电煤缴费/信用卡还款/AA收款等功能",
    "  亚洲较大的网上交易平台,提供各类服饰、美容、家居、数码、话费/点卡充值… 数亿优质商品,同时提供担保交易(先收货后付款)等安全交易保障服务"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mian);
//        List<Item> items=new ArrayList<>();
//        for (int i = 0;i < images.length; i ++){
//            items.add(new Item(images[i],data[i],context[i]));
//        }
        DatabaseService db = new DatabaseService();
        db.deletitem();
       if(db.getitem().size()==0) {
           db.additem();
       }
        TextView bt_share = (TextView)findViewById(R.id.study_cancel1);
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(com.example.helloworld.learn.MainActivity.this, MainActivity_first.class);
////                startActivity(intent);
                finish();
            }
        });
//        Button button=(Button) findViewById(R.id.circle_button);
//       button.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent intent = new Intent(com.example.helloworld.learn.MainActivity.this, CircleActivity.class);
//               startActivity(intent);
//           }
//       });
        ListView listview=(ListView)findViewById(R.id.list_items);
        MyListAdapter myListAdapter=new MyListAdapter(this,db.getitem());
        listview.setAdapter(myListAdapter);
        //parent发生点击动作的AdapterView。
//view 在AdapterView中被点击的视图(它是由adapter提供的一个视图)。
//position视图在adapter中的位置。
//id 被点击元素的行id。
        listview.setOnItemClickListener(
                (parent, view, position, id) -> {
                   Intent item = new Intent("com.activity2");
                   startActivity(item);
                }
        );
    }

}
