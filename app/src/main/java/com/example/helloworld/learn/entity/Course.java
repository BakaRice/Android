package com.example.helloworld.learn.entity;

public class Course {
    private   String image;
    private   String title;
    private   String context;
    public  Course (String image,String title, String context){
        this.image=image;
        this.title=title;
        this.context=context;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
