package com.example.fhict;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Scanner;

public class HomePage  extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener{
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        tv = findViewById(R.id.textName);


    }

    @Override
    public void onFragmentInteraction(String token) {



        tv.setText(token);
    }
}
