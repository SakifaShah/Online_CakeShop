package com.example.sakifa.myjustcake.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sakifa.myjustcake.Application_layer.My_orders_adapter_bl;
import com.example.sakifa.myjustcake.Application_layer.My_orders_list_bl;
import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class My_orders_pl extends AppCompatActivity {
    TextView textView;
    String url= Constant.myOrders;
    String KEY_SHOPNO="shopno";
    String shopno = Shop_info_pl.shopno;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    List<My_orders_list_bl> listitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        recyclerView= (RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems = new ArrayList<>();
       /* My_orders_list_bl list=new My_orders_list_bl("fff","ff");
        listitems.add(list);
        adapter=new My_orders_adapter_bl(listitems,My_orders_pl.this);
        recyclerView.setAdapter(adapter);*/
        myorder();
    }
    public void myorder()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    //Toast.makeText(My_orders_pl.this,"1",Toast.LENGTH_LONG).show();
                    JSONArray jsonArray=jsonObject.getJSONArray("cakes");
                   // Toast.makeText(My_orders_pl.this,"11",Toast.LENGTH_LONG).show();
                    if(jsonArray!=null && jsonArray.length()>0){
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject o=jsonArray.getJSONObject(i);
                        My_orders_list_bl list=new My_orders_list_bl(o.getString("cakedescription")
                                ,o.getString("cakeno"));
                        listitems.add(list);
                        //Toast.makeText(My_orders_pl.this,o.getString("cakedescription"),Toast.LENGTH_LONG).show();

                    }
                    adapter=new My_orders_adapter_bl(listitems,My_orders_pl.this);
                    recyclerView.setAdapter(adapter);}
                    else
                    {
                        Toast.makeText(My_orders_pl.this,"You dont have any orders",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(My_orders_pl.this, Shop_info_pl.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

               // Toast.makeText(My_orders_pl.this,"3k",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(My_orders_pl.this,error+"error",Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put(KEY_SHOPNO,shopno);
                return params;
            }
        };
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
