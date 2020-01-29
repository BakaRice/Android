package com.example.helloworld.learn;

import org.litepal.crud.LitePalSupport;

public class Cousre extends LitePalSupport {
    private String parttitle;
    private  Item item;
    public Cousre(String parttitle){
        this.parttitle=parttitle;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setParttitle(String parttitle) {
        this.parttitle = parttitle;
    }

    public String getParttitle() {
        return parttitle;
    }
}
