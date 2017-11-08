package org.cwb.pi4androidapp.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.cwb.pi4androidapp.contract.PatientContract;
import org.cwb.pi4androidapp.model.Patient;
import org.cwb.pi4androidapp.ws.Paths;
import org.cwb.pi4androidapp.ws.Utility;

import java.util.List;

/**
 * Created by valter.franco on 11/2/2017.
 */

public class PatientPresenter implements PatientContract.Presenter{

    public static final String TAG = "AppointmentsPresenter";
    private RequestQueue mRequestQueue;
    private PatientContract.View mView;

    public PatientPresenter(PatientContract.View view, Context context){
        mView = view;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void refreshUI() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                StringRequest patientsRequest = new StringRequest(Request.Method.GET, Paths.PATIENTSLIST_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                List<Patient> patients =   Utility.convertJsonStringToList(response, Patient[].class);

                                mView.showPatients(patients);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Request Failed: " + error.getLocalizedMessage());
                    }
                });

                mRequestQueue.add(patientsRequest);
            }
        }).start();
    }

    @Override
    public void onViewReady() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void patientListClicked(Patient patient) {

    }
}
