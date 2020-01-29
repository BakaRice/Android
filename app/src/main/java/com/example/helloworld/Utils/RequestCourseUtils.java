package com.example.helloworld.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.helloworld.R;
import com.example.helloworld.learn.entity.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestCourseUtils {

    private static RequestCourseUtils requestCourseUtils;
    private static List<Course> courseList;
    private RequestCourseUtils() {
    }

    public static RequestCourseUtils getInstance() {
        if (requestCourseUtils == null) {
            requestCourseUtils = new RequestCourseUtils();
        }
        return requestCourseUtils;
    }

//    private String urlPath ="http://114.115.169.146:8080/queryCourseList";

    /**
     * 请求连接数据库
     * @param urlStr
     */
    public List<Course> okhttpDate(String urlStr) {

        final List<Course> courseList = new ArrayList<>();
//        final String[] json = {""};
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        //do something,refresh UI;
                        if (msg.obj instanceof ArrayList<?>) {
                            for (Object o : (List<Course>) msg.obj) {
                                courseList.add(Course.class.cast(o));
                            }
                        }
//                        courseList = get(msg.obj);
                        break;
                    default:
                        break;
                }
            }

        };
        Log.i("TAG", "--ok-");
        new Thread(() -> {
            HttpURLConnection conn = null;
            InputStream is = null;
            StringBuilder resultData = new StringBuilder();
            try {
                URL url = new URL(urlStr); //URL对象
                conn = (HttpURLConnection) url.openConnection(); //使用URL打开一个链接,下面设置这个连接
                conn.setRequestMethod("GET"); //使用get请求
                if (conn.getResponseCode() == 200) {//返回200表示相应成功
                    is = conn.getInputStream();   //获取输入流
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bufferReader = new BufferedReader(isr);
                    String inputLine = "";
                    while ((inputLine = bufferReader.readLine()) != null) {
                        resultData.append(inputLine).append("\n");
                    }
                    System.out.println("GET方法取回内容：" + resultData);


                    Message msg=new Message();
//                    msg.what=1;
//                    msg.obj=resultData.toString();
                    handler.obtainMessage(1,parseJSONtoCourseList(resultData.toString())).sendToTarget();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        return courseList;
    }
//测试失败
    public  String gethttpresult(String urlStr){
        try {
            URL url=new URL(urlStr);
            HttpURLConnection connect=(HttpURLConnection)url.openConnection();
            InputStream input=connect.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line = null;
            System.out.println(connect.getResponseCode());
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public List<Course> parseJSONtoCourseList(String jsonData) {
        List<Course> courseList=new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String courseIconId = jsonObject.getString("courseIconId");
                String courseName = jsonObject.getString("courseName");
                String courseIntroduction = jsonObject.getString("courseIntroduction");
                System.out.println("image" + courseIconId + ";courseName" + courseName + ";courseIntroduction" + courseIntroduction);
                Course course=new Course(courseIconId,courseName,courseIntroduction);
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return courseList;
    }
    public void parseJSONtoChapterList(String jsonData){

    }
}
