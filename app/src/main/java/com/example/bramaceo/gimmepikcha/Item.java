package com.example.bramaceo.gimmepikcha;


public class Item {
    String url;
    String name;

    public Item(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
