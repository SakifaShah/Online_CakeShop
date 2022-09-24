package com.example.sakifa.myjustcake.presentation_layer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sakifa.myjustcake.Application_layer.Login_bl;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.Application_layer.MyShopAdaptor_bl;
import com.example.sakifa.myjustcake.Application_layer.MyShopItem_bl;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;
import com.example.sakifa.myjustcake.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyShop_pl extends AppCompatActivity {


    final String userno= Login_bl.userno;

    private static final String URL=Constant.get_my_shops;
    private static final String URL2=Constant.get_my_shops2;
    private RecyclerView recyclerView;


    private RecyclerView.Adapter adapter;
    public static final String KEY_USERNO="userno";
    private List<MyShopItem_bl> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        loadmyshops();
    }
    private void loadmyshops()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        //Toast.makeText(getApplicationContext(),userno,Toast.LENGTH_LONG).show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject= new JSONObject(s);
                    JSONArray array=jsonObject.getJSONArray("myshopsinfo");
                    if(array!=null && array.length()>0){
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject o=array.getJSONObject(i);

                        MyShopItem_bl item=new MyShopItem_bl(
                                o.getString("shopname"),o.getString("shopno")
                        );
                        listItems.add(item);

                    }
                    adapter=new MyShopAdaptor_bl(listItems,MyShop_pl.this);
                    recyclerView.setAdapter(adapter);}
                    else
                    {
                        Toast.makeText(getApplicationContext(),"You don't have any shop",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MyShop_pl.this, Shops_pl.class);
                        startActivity(intent);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
        protected Map<String,String> getParams(){
        Map<String,String> params = new HashMap<String, String>();
        params.put(KEY_USERNO,userno);
        return params;
    }
        }
        ;
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        //RequestQueue requestQueue= Volley.newRequestQueue(this);
        //requestQueue.add(stringRequest);
    }
}
