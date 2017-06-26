package com.project.mahima.happyshopping;

import android.content.ClipData;

/**
 * Created by mahima on 23/4/17.
 */
public class Item {
    private String name;
    private String desc;
    private String cost;
    private String image;
    private String type;

    public Item(String name, String desc, String cost, String image,String type){
        this.name = name;
        this.desc = desc;
        this.cost = cost;
        this.image = image;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getCost() {
        return cost;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
