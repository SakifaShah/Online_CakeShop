package com.example.sakifa.myjustcake.Application_layer;

import android.content.Context;
import android.content.Intent;
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
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;
import com.example.sakifa.myjustcake.presentation_layer.Shops_pl;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_bl extends AppCompatActivity  {
    public static String  userno="";

    EditText editTextPassword;
     EditText editTextEmail;
     Context ctx;
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    String url= Constant.login_url;
    String url2=Constant.login_url2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);

    }
    public void loginClick(View view)
    {
        login();

    }

    public void regClick(View view)
    {
       Intent intent=new Intent(Login_bl.this,Registration_bl.class);
       this.startActivity(intent);

    }
   public void login()
    {

        final String password = editTextPassword.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        //Toast.makeText(Login_bl.this, "response2", Toast.LENGTH_SHORT).show();
            StringRequest stringRequest=new StringRequest(Request.Method.POST,url2,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   if (response.equals("error"))

                   {
                        Toast.makeText(Login_bl.this, "Login_bl Failed" +
                                " : Incorrect username or password"+response, Toast.LENGTH_SHORT).show();
                   }
                   else{
              Toast.makeText(Login_bl.this, "Login_bl Success", Toast.LENGTH_SHORT).show();
                       try {
                           JSONObject jsonObject=new JSONObject(response);
                          // Toast.makeText(Login_bl.this, "Login_bl Success1", Toast.LENGTH_SHORT).show();
                           JSONArray array=jsonObject.getJSONArray("userinfo");
                       //    Toast.makeText(Login_bl.this, "Login_bl Success2", Toast.LENGTH_SHORT).show();
                           for(int i=0;i<array.length();i++)
                           {
                              // Toast.makeText(Login_bl.this, "Login_bl Success3", Toast.LENGTH_SHORT).show();
                               JSONObject o=array.getJSONObject(i);
                            //   Toast.makeText(Login_bl.this, "Login_bl Success4", Toast.LENGTH_SHORT).show();
                               userno=o.getString("userno");
                               String username=o.getString("username");
                              // Toast.makeText(Login_bl.this,username,Toast.LENGTH_LONG).show();
                           }
                          // Toast.makeText(Login_bl.this, "Login_bl Success2", Toast.LENGTH_SHORT).show();
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                       Intent intent=new Intent(Login_bl.this,Shops_pl.class);
                       startActivity(intent);
                       finish();


                   }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login_bl.this, "error"+error.toString(), Toast.LENGTH_SHORT).show();
                }
            })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params= new HashMap<>();
                    params.put(KEY_EMAIL,email);
                    params.put(KEY_PASSWORD,password);
                    return params;
                };
            };

        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        //RequestQueue requestQueue = Volley.newRequestQueue(Login_bl.this);
        //requestQueue.add(stringRequest);
}

    /**
     * Created by sakifa on 3/16/18.
     */



    /**
     * Created by sakifa on 3/16/18.
     */

}
