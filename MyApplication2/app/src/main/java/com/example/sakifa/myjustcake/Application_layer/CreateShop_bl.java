package com.example.sakifa.myjustcake.Application_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;
import com.example.sakifa.myjustcake.presentation_layer.MyShop_pl;

import java.util.HashMap;
import java.util.Map;

public class CreateShop_bl extends AppCompatActivity {
    private static final String CREATE_URL = Constant.createshop_url2;
    final String userno= Login_bl.userno;
    //String userno=t;
    private EditText e1,e2,e3;
    private Button b1;
    public static final String KEY_NAME = "shopname";
    public static final String KEY_CONTACT = "shopcontact";
    public static final String KEY_ADDRESS = "shopaddress";
    public static final String KEY_USERNO = "userno";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_shop);
        e1=(EditText)findViewById(R.id.sname);
        e2=(EditText)findViewById(R.id.sContact);
        e3=(EditText)findViewById(R.id.sAddress);
        b1=(Button)findViewById(R.id.crtbtn);
    }
    public void createshop(View view)
    {
        //Toast.makeText(CreateShop_bl.this,userno,Toast.LENGTH_LONG).show();

        createShop();
    }
    private void createShop(){
        final String shopname = e1.getText().toString().trim();
        final String shopcontact = e2.getText().toString().trim();
        final String shopaddress = e3.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CREATE_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("success")){
                        Toast.makeText(CreateShop_bl.this,"New shop created successfully",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(CreateShop_bl.this,MyShop_pl.class);
                        startActivity(intent);

                        }
                        else Toast.makeText(CreateShop_bl.this,response,Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(CreateShop_bl.this,error+toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put(KEY_USERNO,userno);
                params.put(KEY_NAME,shopname);
                params.put(KEY_CONTACT, shopcontact);
                params.put(KEY_ADDRESS, shopaddress);
                return params;
            }

        };
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        // RequestQueue requestQueue = Volley.newRequestQueue(this);
        // requestQueue.add(stringRequest);
    }

    /**
     * Created by sakifa on 3/9/18.
     */


}
