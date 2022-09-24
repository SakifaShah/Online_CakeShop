package com.example.sakifa.myjustcake.Application_layer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.sakifa.myjustcake.presentation_layer.Shop_info_pl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by sakifa on 3/19/18.
 */

public class Tab_three_bl extends Fragment implements View.OnClickListener {
    String shopno = MyShopAdaptor_bl.t;
    Tab_two_bl t=new Tab_two_bl();
    String SHOPNO_KEY = "shopno", usernum = "userno";
    String cakeno = "jj";
    public static final int IMG = 1;
    Button button1, button2;
    Bitmap bitmap;
    ImageView imageView;
    EditText editText1,editText2,editText3;
   // String URL = "http://192.168.1.129/upload.php";
    String URL2 = Constant.cakeUpload2;
    String URL = Constant.cakeUpload;
    public static final String name = "name";
    public static final String image = "image";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_three, container, false);
        imageView = (ImageView) view.findViewById(R.id.image);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        editText1 = (EditText) view.findViewById(R.id.editText1);
        editText2=(EditText)view.findViewById(R.id.editText2);
        editText3=(EditText)view.findViewById(R.id.editText3);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                selectImage();

                break;
            case R.id.button2: {
                //cakeno();
                uploadimage();
Intent intent=new Intent(getActivity(), Shop_info_pl.class);
getActivity().startActivity(intent);
                break;
            }
            default:
                break;
        }
    }


    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMG && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                editText1.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void uploadimage() {

        final String cakedescription= editText1.getText().toString().trim();
        final String cakeprice=editText2.getText().toString().trim();
        final String stock=editText3.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);
                        // JSONObject jsonObject=new JSONObject(response);
                        // String Response=jsonObject.getString("response");
                       /* if (response.equals("success"))
                            //Toast.makeText(getActivity(), "Response", Toast.LENGTH_LONG).show();
                        //Toast.makeText(getActivity(),imagetostring(bitmap),Toast.LENGTH_LONG).show();
                        else if (response.equals("not success"))
                            Toast.makeText(getActivity(), "Arman", Toast.LENGTH_LONG).show();*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error + "jjj", Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String imagee = imagetostring(bitmap);

                params.put(SHOPNO_KEY, shopno);
                params.put("cakedescription", cakedescription);
                params.put("cakeprice", cakeprice);
                params.put("stock", stock);

                params.put(image, imagee);
                // Toast.makeText(getActivity(),imagetostring(bitmap),Toast.LENGTH_LONG).show();
                return params;
            }
        };
        Mysingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }

    private String imagetostring(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        String code = Base64.encodeToString(imgBytes, Base64.DEFAULT);
        return code;


    }


}
