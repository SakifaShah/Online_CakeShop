package com.example.sakifa.myjustcake.Application_layer;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Order2_bl extends AppCompatActivity {
    String URL2 = Constant.order2 ;
    EditText e1, e2;
    Calendar mCurrentDate;
    int  day,month,year;
    String customerno = Login_bl.userno;
    String cakeno = ShopAdapter_bl.cakeno;
    Calendar calendar=Calendar.getInstance();
    String orderdate= DateFormat.getDateInstance().format(calendar.getTime());


    public static final String KEY_CAKEDESCRIPTION = "orderdescription";
    public static final String KEY_CAKENO = "cakeno";
    public static final String KEY_ORDERDATE = "orderdate";
    public static final String KEY_DELIVARYDATE = "delivarydate";
    public static final String KEY_CUSTOMERNO = "customerno";

    String isoFormat;

    String orderdescription, delivarydate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        mCurrentDate=Calendar.getInstance();
        day=mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month=mCurrentDate.get(Calendar.MONTH);
        year=mCurrentDate.get(Calendar.YEAR);
        month=month+1;
        e2.setText(day+"/"+month+"/"+year);
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(Order2_bl.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        e2.setText(day+"/"+month+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }

    public void order(View v) {
        order3();
    }

    public void order3() {


        orderdescription = e1.getText().toString().trim();
        delivarydate = e2.getText().toString().trim();
       // text = e3.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // if (response.equals("Successfully ordered")) {
                    Toast.makeText(Order2_bl.this, "Successfully ordered", Toast.LENGTH_LONG).show();
               /* } response else {
                    Toast.makeText(Order2_bl.this, "Order_pl is failed", Toast.LENGTH_LONG).show();

                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Order2_bl.this, error + "error", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {



                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_CAKEDESCRIPTION, orderdescription);
                params.put(KEY_CAKENO, cakeno);
                params.put(KEY_CUSTOMERNO, customerno);
                params.put(KEY_DELIVARYDATE, delivarydate);
                params.put(KEY_ORDERDATE, orderdate);


               // params.put(KEY_TEXT, text);
                return params;
            }
        };
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    /*public void deliverydate(View view) {
        final DatePickerFragmentDialog dialog =
                DatePickerFragmentDialog.newInstance(new DatePickerFragmentDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerFragmentDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Log.e("delivery date", year + " " + monthOfYear + " " + dayOfMonth);
                        String date = year + " " + monthOfYear + " " + dayOfMonth;

                        e2.setText(date);
                    }
                });


        dialog.show(getSupportFragmentManager(), "tag");}*/

}
