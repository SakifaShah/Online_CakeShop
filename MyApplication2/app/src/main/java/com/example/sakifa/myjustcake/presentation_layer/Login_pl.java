package com.example.sakifa.myjustcake.presentation_layer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.sakifa.myjustcake.R;

/**
 * Created by sakifa on 3/28/18.
 */

public class Login_pl extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        EditText editTextEmail= (EditText) findViewById(R.id.editTextEmail);

    }
}
