package com.example.fhict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener {
    private TextView tv;
    private List<String> subjectList=null;
    private String subName="sss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
    }

    @Override
    public void onFragmentInteraction(String token) {
        //tv.setText(token);
        new JSONTask().execute(token);


    }

    private class JSONTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url=null;
            String s=null;
            try {
                url= new URL("https://api.fhict.nl/people/me");
                HttpURLConnection connection = null;
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestProperty("Accept","application/json");
                connection.setRequestProperty("Authorization","Bearer " +strings[0]);
                connection.connect();

                InputStream is=connection.getInputStream();
                Scanner scn = new Scanner(is);
                s = scn.useDelimiter("\\Z").next();

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                subjectList = new ArrayList<>();
                for(int i=0;i<jsonArray.length();i++) {
                    // each array element is an object
                    JSONObject scheduleObject = jsonArray.getJSONObject(i);
                    // from each object, get field "subject", which is a string
                    subName = scheduleObject.getString("subject");
                    // add each "subject" string to list
                    subjectList.add(subName);
                }




            }catch(IOException | JSONException e)
            {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            tv.setText(subName);
        }
    }
    public void openActivity2() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);

    }

}
