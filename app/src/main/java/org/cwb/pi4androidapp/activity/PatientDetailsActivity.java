package org.cwb.pi4androidapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.adapter.TabPagerAdapter;
import org.cwb.pi4androidapp.model.Patient;
import org.cwb.pi4androidapp.model.Patient.RefreshHandlerPatient;
import org.cwb.pi4androidapp.ws.Paths;
import org.json.JSONException;
import org.json.JSONObject;

public class PatientDetailsActivity extends AppCompatActivity implements RefreshHandlerPatient {

    Bundle bundle = new Bundle();
    Patient p;
    ProgressDialog mRefreshProgressDialog;
    ViewPager viewPager;
    PagerAdapter adapter;
    TabLayout tabLayout;
    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        p = new Patient(this);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Paciente"));
        tabLayout.addTab(tabLayout.newTab().setText("Consultas"));
        tabLayout.addTab(tabLayout.newTab().setText("Exames"));

        Bundle bundlePatId = getIntent().getExtras();
        patientId = bundlePatId.getInt("patientId");
        Bundle patbun = getIntent().getExtras();
        Patient patt = patbun.getParcelable("Paciente");
        if (mRefreshProgressDialog == null) {
            // Cria e configura o objeto ProgressDialog
            mRefreshProgressDialog = new ProgressDialog(PatientDetailsActivity.this);
            mRefreshProgressDialog.setTitle("Aguarde");
            mRefreshProgressDialog.setMessage("Baixando paciente...");
            mRefreshProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        mRefreshProgressDialog.show();

        //LoadPatientData(PatientDetailsActivity.this);
       p.LoadPatientData(PatientDetailsActivity.this,patientId);
        /* final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Paths.PATIENT_URL + patientId , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            p = new Patient();
                            p.setPatientId(response.getInt("patientId"));
                            p.setPatientName(response.getString("patientName"));
                            p.setPatientEmail(response.getString("patientEmail"));
                            p.setPatientPhone(response.getString("patientPhone"));
                            p.setPatientAge(response.getInt("patientAge"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(PatientDetailsActivity.this);

        queue.add(jsonObjectRequest);*/

        /*Bundle bundle = getIntent().getExtras();
        int patientId = bundle.getInt("patientId");*/

        //LoadPatientData(patientId);
     /*   viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }



   /* public void LoadPatientData(final PatientDetailsActivity handler) {
        String patPath = Paths.PATIENT_URL + String.valueOf(patientId);
        RequestQueue queue = Volley.newRequestQueue(PatientDetailsActivity.this);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, patPath , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            p.setPatientId(response.getInt("patientId"));
                            p.setPatientName(response.getString("patientName"));
                            p.setPatientEmail(response.getString("patientEmail"));
                            p.setPatientPhone(response.getString("patientPhone"));
                            p.setPatientAge(response.getInt("patientAge"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Erro","Response error");
            }

        });
        queue.add(jsonObjectRequest);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_layout, menu);
        return true;
    }

    @Override
    public void onRefreshCompletedPatient(Boolean success,Patient p) {
        mRefreshProgressDialog.hide();
        if (success) {
            bundle.putParcelable("pa",p);
            viewPager = (ViewPager) findViewById(R.id.pager);
            adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),bundle);
            viewPager.setAdapter(adapter);

            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());


                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        } else {
            Toast.makeText(this, "Aconteceu um erro ao acessar o servidor.", Toast.LENGTH_LONG).show();
        }
    }
}
