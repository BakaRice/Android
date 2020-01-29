package com.example.helloworld.setrelative.entity;

public class Relative{
    private String name;
    private String number;

    public  Relative( String name,String number){
        this.name=name;
        this.number=number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
