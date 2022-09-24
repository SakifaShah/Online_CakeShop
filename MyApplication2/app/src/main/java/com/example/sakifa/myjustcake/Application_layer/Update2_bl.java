package com.example.sakifa.myjustcake.Application_layer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;

import java.util.HashMap;
import java.util.Map;

public class Update2_bl extends AppCompatActivity {

    String URL= Constant.update;
    String URL2=Constant.update2;
    EditText e1,e2,e3;
    String cakeno= CakeAdaptor_bl.cakeno;
    public static final String KEY_CAKENO="cakeno";
    public static final String KEY_CAKEDESCRIPTION="cakedescription";
    public static final String KEY_PRICE="cakeprice";
    public static final String KEY_STOCK="stock";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e1.setText(getIntent().getStringExtra("cakename"));
        e2.setText(getIntent().getStringExtra("cakeprice"));
        e3.setText(getIntent().getStringExtra("stock"));

    }
    public void update(View v)
    {

        update1();

    }

        public void update1()
    {


        final String cakedescription=e1.getText().toString().trim();
        final String cakeprice=e2.getText().toString().trim();
        final String stock=e3.getText().toString().trim();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Update2_bl.this,"Succesfully updated",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Update2_bl.this,error+"error",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put(KEY_CAKENO,cakeno);
                params.put(KEY_CAKEDESCRIPTION,cakedescription);
                params.put(KEY_PRICE,cakeprice);
                params.put(KEY_STOCK,stock);
                return params;
            }
        };
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
