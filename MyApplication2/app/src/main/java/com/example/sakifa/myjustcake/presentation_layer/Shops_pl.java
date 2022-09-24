package com.example.sakifa.myjustcake.presentation_layer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sakifa.myjustcake.Application_layer.Login_bl;
import com.example.sakifa.myjustcake.Application_layer.MyAdapter_bl;
import com.example.sakifa.myjustcake.Application_layer.ListItem_bl;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;
import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Application_layer.CreateShop_bl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Shops_pl extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    String URL= Constant.get_shops;
    String URL2= Constant.get_shops2;
    Button createbtn,visitbtn;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private List<ListItem_bl> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        createbtn = (Button) findViewById(R.id.button);
        visitbtn = (Button) findViewById(R.id.button1);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        listItems = new ArrayList<>();
        loadRecyclerviewData();
    }
    public void logout(View view)
    {
        Intent intent = new Intent(Shops_pl.this, Login_bl.class);
        startActivity(intent);
        finish();
    }

    public void create(View view) {
        Intent intent = new Intent(Shops_pl.this, CreateShop_bl.class);
        startActivity(intent);


    }

    private void loadRecyclerviewData() {
        listItems.clear();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.........");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("shops");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        ListItem_bl item = new ListItem_bl(
                                o.getString("shopname"),
                                o.getString("shopno")
                        );
                        listItems.add(item);
                    }
                    adapter = new MyAdapter_bl(listItems, Shops_pl.this);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        //RequestQueue requestQueue= Volley.newRequestQueue(this);
        //requestQueue.add(stringRequest);
    }

    public void visit(View view) {
        Intent intent = new Intent(Shops_pl.this, MyShop_pl.class);
        startActivity(intent);

    }

    @Override
    public void onRefresh() {
        loadRecyclerviewData();
    }
}
