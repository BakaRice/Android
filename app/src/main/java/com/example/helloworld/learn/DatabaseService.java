package com.example.helloworld.learn;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.helloworld.R;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseService extends LitePalSupport{
      private   String[] parttitle = {
                "微信是什么？" ,
                "使用微信的费用" ,
                "微信的下载安装",
//                "微信的打开",
                "微信的注册与登录",
                "微信账号与昵称",
                "如何找到我的好友？",
                "开始聊天",
                "聊天的其它功能",
                "群聊",
                "朋友圈",
                "功能设置",
                "微信电脑版的使用"};

    private  int[] images = new int[]{R.drawable.wechat,R.drawable.qq,R.drawable.zhifubao,R.drawable.taobao};
    private String[] data={"微信入门","QQ入门","支付宝入门","淘宝入门"};
    private String[] context={"  超过十亿人使用的手机应用 支持发送语音短信、视频、图片和文字 可以群聊，仅耗少量流量，适合大部分智能手机。",
            "  8亿人在用的即时通讯软件,你不仅可以在各类通讯终端上通过QQ聊天交友,还能进行免费的视频、语音通话,或者随时随地收发重要文件。",
            "  全球领先的独立第三方支付平台,致力于为广大用户提供安全快速的电子支付/网上支付/安全支付/手机支付体验,及转账收款/水电煤缴费/信用卡还款/AA收款等功能",
            "  亚洲较大的网上交易平台,提供各类服饰、美容、家居、数码、话费/点卡充值… 数亿优质商品,同时提供担保交易(先收货后付款)等安全交易保障服务"};

    private  static List<PartCourse> partCourseList =new ArrayList<>();

    public void addpartcou2(){

//        PartCourse courses2 =new PartCourse(1,R.drawable.back,"1",0,1);
//        PartCourse courses21=new PartCourse(2,R.drawable.back,"2",0,1);
//        PartCourse courses22=new PartCourse(3,R.drawable.back,"3",0,1);
//        PartCourse courses23=new PartCourse(4,R.drawable.back,"4",0,1);
//        PartCourse courses24=new PartCourse(5,R.drawable.back,"5",1,1);
//        PartCourse courses25=new PartCourse(6,R.drawable.back,"6",1,1);
//        partCourseList.add(courses2);
//        partCourseList.add(courses21);
//        partCourseList.add(courses22);
//        partCourseList.add(courses23);
//        partCourseList.add(courses24);
//        partCourseList.add(courses25);
        PartCourse courses4 =new PartCourse(1,R.drawable.w4_2,"1、点击微信的图标进入首页",3,1);
        PartCourse courses41=new PartCourse(2,R.drawable.w4_1,"2、点击【注册】，",3,1);
        PartCourse courses42=new PartCourse(3,R.drawable.w4_3,"3、输入手机号，再点击【注册】，进入注册页面，按照上面的提示填写相应的信息，然后点击【已阅读并同意《软件许可及服务协议》】前的小方框，最后点击注册",3,1);
        PartCourse courses43=new PartCourse(4,R.drawable.w4_4,"4、阅读隐私文件，然后点击【同意】，必须点【同意】，不然无法继续进行注册",3,1);
        PartCourse courses44=new PartCourse(5,R.drawable.w4_5,"5、在【安全验证】页面点击【开始】，然后拖动拼图完成验证",3,1);
        PartCourse courses45=new PartCourse(6,R.drawable.w4_6,"6、使用注册账号的手机号发送验证码到指定的号码",3,1);
        PartCourse courses46=new PartCourse(7,R.drawable.w4_7,"7、然后回到注册页面点击【我已发送，下一步】",3,1);
        PartCourse courses47=new PartCourse(8,R.drawable.w4_8,"8、注册完成之后可以点击【好】同步手机通讯录进入主页" +
                "",3,1);
        partCourseList.add(courses4);
        partCourseList.add(courses41);
        partCourseList.add(courses42);
        partCourseList.add(courses43);
        partCourseList.add(courses44);
        partCourseList.add(courses45);
        partCourseList.add(courses46);
        partCourseList.add(courses47);

        SQLiteDatabase db= LitePal.getDatabase();
        LitePal.saveAll(partCourseList);
    }
    public List<PartCourse> getpartcouse(int pageid){
        SQLiteDatabase db= LitePal.getDatabase();
        return LitePal.findAll(PartCourse.class).stream()
                .filter(pi->pi.getPageid()==pageid)
                .collect(Collectors.toList());
    }
    public void deletpartcourse(){
        SQLiteDatabase db= LitePal.getDatabase();//在oncreate里才有用
        LitePal.deleteAll(PartCourse.class);
        Log.d("deletpart","Partcourse deleted");
    }
    /**
     *
     */

        public void  additem(){
            SQLiteDatabase db= LitePal.getDatabase();
            List<Item> items=new ArrayList<>();
            for (int i = 0;i < images.length; i ++){
                items.add(new Item(images[i],data[i],context[i]));
            }
            LitePal.saveAll(items);
        }

    /**
     *
     */
        public void deletitem(){
            SQLiteDatabase db= LitePal.getDatabase();//在oncreate里才有用
            LitePal.deleteAll(Item.class);
            Log.d("deletpart","items deleted");
        }

    /**
     * @return
     */
        public  List<Item> getitem(){
            SQLiteDatabase db= LitePal.getDatabase();
            return LitePal.findAll(Item.class);
        }


        public  void addparttitle( ){
            SQLiteDatabase db= LitePal.getDatabase();//在oncreate里才有用
            for (String i:parttitle) {
                Cou2 cousre= new Cou2();
//            cousre.setItem();
                cousre.setParttitle(i);
                cousre.save();
//                DataSupport.saveAll();
            }
        }
        public  void deletparttitle(){
            SQLiteDatabase db= LitePal.getDatabase();//在oncreate里才有用
            LitePal.deleteAll(Cou2.class);
            Log.d("deletpart","titles deleted");
        }
        public List<Cou2> getparttitle(){
            SQLiteDatabase db= LitePal.getDatabase();
            return LitePal.findAll(Cou2.class);
        }

}
