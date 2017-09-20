package com.example.valterfranco.myapplication;

import android.app.Activity;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    Button b;
    TextView tv;
    String serverUrl = "http://localhost:8080/PI4/webapi/patient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.txt);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
               JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, serverUrl,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    tv.setText(response.getString("patientName"));
                                    requestQueue.stop();
                                } catch(JSONException e){
                                    Log.getStackTraceString();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv.setText("Something went wrong");
                        error.printStackTrace();
                        requestQueue.stop();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
    }
}
