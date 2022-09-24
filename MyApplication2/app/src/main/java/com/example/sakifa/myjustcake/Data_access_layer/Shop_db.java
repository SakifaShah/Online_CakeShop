package com.example.sakifa.myjustcake.Data_access_layer;

/**
 * Created by sakifa on 3/30/18.
 */

public class Shop_db {
    public static final String SERVER_URL = "http://middaydreamz.com/cakeshop";
    public static final String createshop_url2 = SERVER_URL + "/com/insert_cakeshop.php";
    public static final String get_shops2 = SERVER_URL + "/com/get_cakeshops.php";
    public static final String get_shops_info = SERVER_URL + "/com/get_a_singleshop_info.php";
    //public static final String get_a_shopinfo =SERVER_URL+"main_cakeshop1/com/get_a_singleshop_info.php";
    public static final String get_my_shops2 = SERVER_URL + "/com/get_myshops.php";
    public static final String shop_cakes2= SERVER_URL +"/com/get_cake_of_a_shop.php";
    public static final String imgurl=SERVER_URL+"/com/uploads/";
    public static final String shopinfo_update= SERVER_URL+"/com/update_shop_info.php";
}
