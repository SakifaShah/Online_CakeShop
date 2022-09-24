package com.example.sakifa.myjustcake.Application_layer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class Update_shop_bl extends AppCompatActivity {
    EditText editText1,editText2,editText3;
    Button button;
    String shopno= Tab_one_bl.shopno;
    public static final String KEY_SHOPNO="shopno";
    public static final String KEY_SHOPADDRESS="shopaddress";
    public static final String KEY_SHOPCONTACT="shopcontact";
    public static final String KEY_SHOPNAME="shopname";
    String URL= Constant.shopinfo_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_shop);
        button=(Button)findViewById(R.id.button);
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        editText1.setText(getIntent().getStringExtra("shopName"));
        editText2.setText(getIntent().getStringExtra("shopAddress"));
        editText3.setText(getIntent().getStringExtra("shopContact"));
    }
    public void modify(View v)
    {
        editShop();
    }
    public void editShop()
    {
        final String shopname=editText1.getText().toString().trim();
        final String shopcontact=editText2.getText().toString().trim();
        final String shopaddress=editText3.getText().toString().trim();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Update_shop_bl.this,"Sucessfully Updated",LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Update_shop_bl.this,error+"error",LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put(KEY_SHOPNO,shopno);
                params.put(KEY_SHOPNAME,shopname);
                params.put(KEY_SHOPADDRESS,shopaddress);
                params.put(KEY_SHOPCONTACT,shopcontact);
                return params;
            }
        };
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
