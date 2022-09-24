package com.example.sakifa.myjustcake.Application_layer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sakifa.myjustcake.Data_access_layer.Constant;
import com.example.sakifa.myjustcake.R;
import com.example.sakifa.myjustcake.Data_access_layer.Mysingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import java.util.Map;


/**
 * Created by sakifa on 3/19/18.
 */

@SuppressLint("ValidFragment")
public class Tab_one_bl extends Fragment implements View.OnClickListener{

    static String shopno = MyShopAdaptor_bl.t;
    String URL = Constant.get_my_shops_info;
    String URL2 = Constant.get_shops_info;
    //Context ctx;
    String a = null, b = null, c = null;
    TextView t1, t2, t3;
    Button button1;
    static final String KEY_SHOPNO = "shopno";

    @SuppressLint("ValidFragment")
    public Tab_one_bl(String t) {
        this.shopno=t;
    }

    public static String getShopno() {
        return shopno;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_one, container, false);
        t1 = (TextView) view.findViewById(R.id.t1);
        t2 = (TextView) view.findViewById(R.id.t2);
        t3 = (TextView) view.findViewById(R.id.t3);
        button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(this);

        volley();


        return view;


    }


    public void volley() {
        //Toast.makeText(getActivity(), getShopno(),Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getActivity(), shopno,Toast.LENGTH_LONG).show();
                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //Toast.makeText(getActivity(), "1",Toast.LENGTH_LONG).show();
                    JSONArray jsonArray = jsonObject.getJSONArray("shopinfo");
                  // Toast.makeText(getActivity(), "2",Toast.LENGTH_LONG).show();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject o = jsonArray.getJSONObject(i);
                        a = o.getString("shopname");
                        b = o.getString("shopcontact");
                        c = o.getString("shopaddress");
                        //  Toast.makeText(getActivity(), "3",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                t1.setText(a);
                t2.setText(b);
                t3.setText(c);
              //  Toast.makeText(getActivity(), "4", Toast.LENGTH_LONG).show();

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error + "userno", Toast.LENGTH_LONG).show();
            }
        }) {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_SHOPNO, shopno);
                return params;
            }
        };
        Mysingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(),Update_shop_bl.class);
        intent.putExtra("shopName",a);
        intent.putExtra("shopAddress",b);
        intent.putExtra("shopContact",c);
        this.startActivity(intent);

    }
}
