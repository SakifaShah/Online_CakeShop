package com.example.sakifa.myjustcake.presentation_layer.model;

/**
 * Created by sakifa on 3/20/18.
 */

public class Cake {
    private int shopNo;
    private int cakeNo;
    private String cakeName, cakeDescription;


    public Cake(int shopNo, int cakeNo, String cakeName, String cakeDescription) {
        this.shopNo = shopNo;
        this.cakeNo = cakeNo;
        this.cakeName = cakeName;
        this.cakeDescription = cakeDescription;
    }

    public int getShopNo() {
        return shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }

    public int getCakeNo() {
        return cakeNo;
    }

    public void setCakeNo(int cakeNo) {
        this.cakeNo = cakeNo;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getCakeDescription() {
        return cakeDescription;
    }

    public void setCakeDescription(String cakeDescription) {
        this.cakeDescription = cakeDescription;
    }
}
