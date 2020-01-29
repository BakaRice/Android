package com.example.helloworld.learn;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Database{
    private  static List<PartCourse> courses2s=new ArrayList<>();
  static {
      PartCourse courses2 =new PartCourse(1, R.drawable.back2,"1",0,1);
      PartCourse courses21=new PartCourse(2,R.drawable.back2,"2",0,1);
      PartCourse courses22=new PartCourse(3,R.drawable.back2,"3",0,1);
      PartCourse courses23=new PartCourse(4,R.drawable.back2,"4",0,1);
      PartCourse courses24=new PartCourse(5,R.drawable.back2,"5",1,1);
      PartCourse courses25=new PartCourse(6,R.drawable.back2,"6",1,1);
      courses2s.add(courses2);
      courses2s.add(courses21);
      courses2s.add(courses22);
      courses2s.add(courses23);
      courses2s.add(courses24);
      courses2s.add(courses25);
  }

    public static void setCourses2s(List<PartCourse> courses2s) {
        Database.courses2s = courses2s;
    }

    public static List<PartCourse> getCourses2s() {
        return courses2s;
    }
    public static List<PartCourse> pageidCourses(int pageid){
      return courses2s.stream()
              .filter(c->c.getPageid()==pageid)
              .collect(Collectors.toList());
    }
}
