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

import java.util.HashMap;
import java.util.Map;

public class Registration_bl extends AppCompatActivity{

    private static final String REGISTER_URL = Constant.reg_url;
    private static final String REGISTER_URL2 =Constant.reg_url2;
    public static String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_CONTACT = "contact";
    public static final String KEY_ADDRESS = "address";
    public static String username=null,email=null,password=null,address=null,contact=null;

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextContact;
    private EditText editTextAddress;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextContact= (EditText) findViewById(R.id.editTextContact);
        editTextAddress= (EditText) findViewById(R.id.editTextAddress);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);


    }

    public void btnFunction(View view){
        registerUser();


    }

    private void registerUser(){
       // String email=null,contact=null,address=null,password=null;
        username=editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        email = editTextEmail.getText().toString().trim();
        contact = editTextContact.getText().toString().trim();
        address = editTextAddress.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL2,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("success")) {
                            Toast.makeText(Registration_bl.this,"Registration_bl Successful",Toast.LENGTH_LONG).show();
                            Intent homeIntent=new Intent(Registration_bl.this,Login_bl.class);
                            startActivity(homeIntent);
                            finish();
                        }
                        else Toast.makeText(Registration_bl.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Registration_bl.this,error+toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,username);
                params.put(KEY_EMAIL, email);
                params.put(KEY_PASSWORD,password);
                params.put(KEY_CONTACT, contact);
                params.put(KEY_ADDRESS, address);
                return params;
            }

        };
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
       /*RequestQueue requestQueue = Volley.newRequestQueue(this);

      // requestQueue.add(stringRequest);*/
    }


}
