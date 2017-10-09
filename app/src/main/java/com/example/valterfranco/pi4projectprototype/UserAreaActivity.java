package com.example.valterfranco.pi4projectprototype;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import model.PatientsList;

public class UserAreaActivity extends AppCompatActivity {

    PatientsList mPatientsList;
    ListView mPatientListView;
    ProgressDialog mRefreshProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        mPatientListView = (ListView)findViewById(R.id.patient_list);
        mPatientsList = new PatientsList(this);

        final EditText etUserName = (EditText) findViewById(R.id.user_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String test = intent.getStringExtra("test");

        etUserName.setText(name);

    }
}
