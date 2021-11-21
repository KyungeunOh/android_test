package com.opensource.firebasetest;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class EcoPlace {
    public String name;
    public String addr;
    public String number;
    public String img;

    public EcoPlace(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public EcoPlace(String name, String addr, String number, String img){
        this.name = name;
        this.addr = addr;
        this.number = number;
        this.img = img;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("addr", addr);
        result.put("number", number);
        result.put("img", img);
        return result;
    }
}
