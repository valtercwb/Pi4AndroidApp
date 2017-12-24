package org.cwb.pi4androidapp.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valter.franco on 11/29/2017.
 */

public class AppointList {

        Context mContext;
        List<Appointment> patientList;

        public AppointList(Context context) {
            mContext = context;
            patientList = new ArrayList<>();
        }

        public void GetAppointsList(final RefreshHandler handler,int patientId) {
            RequestQueue queue = Volley.newRequestQueue(mContext);
            JsonArrayRequest patientsRequest = new JsonArrayRequest(Request.Method.GET,PATIENTS_LIST_URL + patientId +"/appointment", null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    try {
                        patientList.clear();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject o = response.getJSONObject(i);
                            Appointment a = new Appointment();
                            a.setAppointmentId(o.getInt("appointId"));
                            a.setAppDate(o.getString("appDate"));
                            patientList.add(a);
                        }

                        Log.i(TAG, "Pacientes refreshed.");
                        handler.onRefreshCompleted(true);
                    } catch (JSONException e) {
                        Log.e(TAG, "Can' refresh the Pacientes.", e);
                        handler.onRefreshCompleted(false);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Can' refresh the country list.", error);
                    handler.onRefreshCompleted(false);
                }
            });

            queue.add(patientsRequest);
        }

        public List<Appointment> getAppoints() {
            return patientList;
        }

        private final static String PATIENTS_LIST_URL = "http://cwbpi4.herokuapp.com/webapi/patient/";
        private final static String TAG = "Patient.List";

        public interface RefreshHandler {
            void onRefreshCompleted(Boolean success);
        }
    }