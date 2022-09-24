package com.example.sakifa.myjustcake.presentation_layer;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;
import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Application_layer.MyAdapter_bl;
import com.example.sakifa.myjustcake.Application_layer.ShopAdapter_bl;
import com.example.sakifa.myjustcake.Application_layer.Shop_cake_list_bl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop_cakes_pl extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    String shopno= MyAdapter_bl.shopno;
    public static final String KEY_SHOPNO="shopno";
    String URL= Constant.shop_cakes;
    String URL2= Constant.shop_cakes2;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Shop_cake_list_bl> listitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        listitems = new ArrayList<>();
        getcakes();
    }
    public void getcakes()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listitems.clear();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                  //  Toast.makeText(getApplicationContext(),"error1",Toast.LENGTH_LONG).show();
                    JSONArray jsonArray=jsonObject.getJSONArray("cakes");
                   // Toast.makeText(getApplicationContext(),shopno,Toast.LENGTH_LONG).show();
                    for(int i=0;i<jsonArray.length();i++)
                    {
                       // Toast.makeText(getApplicationContext(),"error2",Toast.LENGTH_LONG).show();
                        JSONObject o=jsonArray.getJSONObject(i);
                        Shop_cake_list_bl shopCakeList=new Shop_cake_list_bl(
                                o.getString("cakeno"),o.getString("cakedescription")
                                ,o.getString("cakeprice"),o.getString("stock")
                        );
                       // Toast.makeText(getApplicationContext(),"error2",Toast.LENGTH_LONG).show();
                        listitems.add(shopCakeList);

                        //Toast.makeText(getApplicationContext(),"error3",Toast.LENGTH_LONG).show();
                    }
                    adapter=new ShopAdapter_bl(listitems,Shop_cakes_pl.this);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error+"error",Toast.LENGTH_LONG).show();
            }
        }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put(KEY_SHOPNO,shopno);
                return params;
            }
        };
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onRefresh() {
        getcakes();
    }
}
