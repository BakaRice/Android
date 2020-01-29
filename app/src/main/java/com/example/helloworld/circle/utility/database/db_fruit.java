package com.example.helloworld.circle.utility.database;


import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class db_fruit extends LitePalSupport {

    private String FruitId;//每条朋友圈对应的数据记录id
    private int userId;//用户;
    private String name;//用户昵称
    private int imageId;
    private String content;
    private int content_imgId;//正文内图片id
    private int liked;
    private String comment;
    private Date sendTime;//发送时间

    public String getFruitId() {
        return FruitId;
    }

    public void setFruitId(String fruitId) {
        FruitId = fruitId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContent_imgId() {
        return content_imgId;
    }

    public void setContent_imgId(int content_imgId) {
        this.content_imgId = content_imgId;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }


}
