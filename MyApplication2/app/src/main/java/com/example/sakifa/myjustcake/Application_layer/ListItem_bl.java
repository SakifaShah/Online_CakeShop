package com.example.sakifa.myjustcake.Application_layer;

/**
 * Created by sakifa on 3/18/18.
 */

public class ListItem_bl {
    private String text1;
    private String text2;
    private String imageurl;

    public ListItem_bl(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        //   this.imageurl = imageurl;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getImageurl() {
        return imageurl;
    }
}
