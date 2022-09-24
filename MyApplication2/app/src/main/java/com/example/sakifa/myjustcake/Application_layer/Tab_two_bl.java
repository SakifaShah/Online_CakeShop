package com.example.sakifa.myjustcake.Application_layer;

/**
 * Created by sakifa on 3/19/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;
import com.example.sakifa.myjustcake.presentation_layer.My_orders_pl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tab_two_bl extends Fragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{
  String KEY_SHOPNO="shopno";
  String shopno= Tab_one_bl.shopno;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CakeList_bl> listItems;
    private ImageView imageView;
    Button button,button1;
    public SwipeRefreshLayout swipeRefreshLayout;

   // CakeAdaptor_bl adapter;

    String URL= Constant.shop_cakes;
    String URL2= Constant.shop_cakes2;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_two, container, false);


        //setContentView(R.layout.activity_main2);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleview);
        button=(Button)view.findViewById(R.id.button);
        button1=(Button)view.findViewById(R.id.button1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       listItems = new ArrayList<>();
       swipeRefreshLayout=view.findViewById(R.id.swipe);
button.setOnClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        getCakes();

        return view;
    }

    private void getCakes() {
        //Toast.makeText(getActivity(),"shopno+"+shopno,Toast.LENGTH_LONG).show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {
                //Toast.makeText(getActivity(),"1",Toast.LENGTH_LONG).show();
                listItems.clear();

                try {
                    JSONObject jsonObject=new JSONObject(response);
                   //Toast.makeText(getActivity(),"2",Toast.LENGTH_LONG).show();
                    JSONArray jsonArray=jsonObject.getJSONArray("cakes");
                    if(jsonArray!=null && jsonArray.length()>0){
                    //Toast.makeText(getActivity(),"3",Toast.LENGTH_LONG).show();
                    for (int i = 0; i <jsonArray.length(); i++) {
                          JSONObject o=jsonArray.getJSONObject(i);
                          CakeList_bl cakeList=new CakeList_bl(o.getString("cakeno"),o.getString("cakedescription"),
                                  o.getString("cakeprice"),o.getString("stock"));
                        //Toast.makeText(getActivity(),o.getString("cakeno"),Toast.LENGTH_LONG).show();
                          listItems.add(cakeList);
                    }
                    //Toast.makeText(getActivity(),shopno,Toast.LENGTH_LONG).show();
                    adapter=new CakeAdaptor_bl(listItems,getActivity());
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                    }
                    else{
                        Toast.makeText(getActivity(),"You havenot added any cakes yet",Toast.LENGTH_LONG).show();
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    swipeRefreshLayout.setRefreshing(false);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error+"hh",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put(KEY_SHOPNO,shopno);
                return params;
            }
        };
        Mysingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
   }

    @Override
    public void onClick(View view) {
      switch (view.getId()){
        case R.id.button:
        {
            Intent intent=new Intent(getActivity(),My_orders_pl.class);
            getActivity().startActivity(intent);
            break;
        }


        default:
            break;

    }}

    @Override
    public void onRefresh() {
        getCakes();
    }
}
