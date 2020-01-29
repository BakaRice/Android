package com.example.helloworld.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.test.MainActivity_first;

public class Main2Activity extends AppCompatActivity {
//    private String[] parttitle = {
//            "1、微信是什么？" ,
//            "2、使用微信的费用" ,
//            "3、微信的下载安装",
//            "4、微信的打开",
//            "5、微信的注册与登录",
//            "6、微信账号与昵称",
//            "7、如何找到我的好友？",
//            "8、开始聊天",
//            "9、聊天的其它功能",
//            "10、群聊",
//            "11、朋友圈",
//            "12、功能设置",
//            "13、微信电脑版的使用"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DatabaseService db = new DatabaseService();
//        db.addpartcou2();
         db.deletparttitle();
        if(db.getparttitle().size()==0) {
            db.addparttitle();
        }
//        List<Cousre> part=new ArrayList<>();
//        for (String s : parttitle) {
//            part.add(new Cousre(s));
//       }
        TextView bt_share = (TextView)findViewById(R.id.study_cancel2);
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(com.example.helloworld.learn.Main2Activity.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        ListView lissie2= findViewById(R.id.list_items2);
        MyListAdapter2 myListAdapter=new MyListAdapter2(this,db.getparttitle());
        lissie2.setAdapter(myListAdapter);
        //parent发生点击动作的AdapterView。
//view 在AdapterView中被点击的视图(它是由adapter提供的一个视图)。
//position视图在adapter中的位置。
//id 被点击元素的行id。
        lissie2.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Intent item2 = new Intent(Main2Activity.this,Main3Activity.class);
//                       "com.activity4"
                    item2.putExtra("position",position);
//                        b.putInt("position", (Integer)position);
//                        item2.putExtras(b);
                    startActivity(item2);
                }
        );
    }
}
