package com.example.sakifa.myjustcake.Data_access_layer;

/**
 * Created by sakifa on 2/27/18.
 */

public class Constant {
    public static final String SERVER_URL = "http://middaydreamz.com/cakeshop";
    //public static final String SERVER_URL = "http://192.168.10.2/main_cakeshop1";
    public static final String login_url2 = Login_db.login_url2;
            //SERVER_URL + "/com/login_verification.php";
    public static final String reg_url2 = Registration_db.reg_url2;
    public static final String createshop_url2 = Shop_db.createshop_url2;
    public static final String get_shops2 = Shop_db.get_shops2;
            //SERVER_URL + "/com/get_cakeshops.php";
    public static final String get_shops_info = Shop_db.get_shops_info;
                    //SERVER_URL + "/com/get_a_singleshop_info.php";
    //public static final String get_a_shopinfo =SERVER_URL+"main_cakeshop1/com/get_a_singleshop_info.php";
    public static final String get_my_shops2 = Shop_db.get_my_shops2;
                            //SERVER_URL + "/com/get_myshops.php";
    public static final String shop_cakes2= Shop_db.shop_cakes2;
                                    //SERVER_URL +"/com/get_cake_of_a_shop.php";
    public static final String update2= Update_db.update2;
    public static final String cakeUpload2= SERVER_URL + "/com/insert_cake.php";
    public static final String order2= Order_db.order2;
    public static final String imgurl=SERVER_URL+"/com/uploads/";
    public static final String shopinfo_update= SERVER_URL+"/com/update_shop_info.php";
    public static final String myOrders= SERVER_URL+"/com/mycakes.php";





    ////////////////////Demo//////////////////////////
    public static final String shop_cakes= SERVER_URL +"get_cake_no.php";
    public static final String update=SERVER_URL+"Updatecake.php";
    public static final String cakeUpload=SERVER_URL+"upload.php";
    public static final String upload ="http://192.168.10.2/upload.php";
    public static final String imageurl= SERVER_URL + "get_my_shops.php";
    public static final String login_url = SERVER_URL + "login1.php";
    public static final String cakeshop = SERVER_URL + "cakeshops.php";
    public static final String createshop_url = SERVER_URL + "createshop.php";
    public static final String get_my_shops_info =SERVER_URL + "get_my_shop_info.php";
    public static final String reg_url = SERVER_URL + "cakeshop/com/Registration_bl.php";
    public static final String get_my_shops = SERVER_URL + "get_my_shops.php";
    public static final String get_shops = SERVER_URL + "cakeshops.php";
}
