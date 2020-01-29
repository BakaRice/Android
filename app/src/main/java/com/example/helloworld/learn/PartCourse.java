package com.example.helloworld.learn;

import org.litepal.crud.LitePalSupport;

public class PartCourse extends LitePalSupport {
 private int id;
 private int imageid;
 private String text;
 private int pageid;
 private int courseid;
    public PartCourse(int id, int imageid, String text, int pageid, int courseid ){
      this.id=id;
      this.courseid=courseid;
      this.imageid=imageid;
      this.text=text;
      this.pageid=pageid;
}
    public int getId() {
        return id;
    }

    public int getImageid() {
        return imageid;
    }

    public String getText() {
        return text;
    }

    public int getPageid() {
        return pageid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getCourseid() {
        return courseid;
    }
}
