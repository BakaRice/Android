package com.example.helloworld.learn;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class Item extends LitePalSupport {
    private   int image;
    private   String title;
    private   String context;
    private List<Cousre> cousreList= new ArrayList<Cousre>();
    public  Item (int image,String title, String context){
        this.image=image;
        this.title=title;
        this.context=context;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Cousre> getCousreList() {
        return cousreList;
    }

    public void setCousreList(List<Cousre> cousreList) {
        this.cousreList = cousreList;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getContext() {
        return context;
    }
}
