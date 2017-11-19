package org.cwb.pi4androidapp.model;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.cwb.pi4androidapp.activity.PatientDetailsActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.PendingIntent.getActivity;

/**
 * Created by valter.franco on 11/12/2017.
 */

/*public class AppointmentList {

    Context mContext;
    List<Appointment> appointList;
    //
    // CONSTANTS

    public static String APPOINT_LIST_URL = "http://cwbpi4.herokuapp.com/webapi/patient/patientId/appointment";

    private static String TAG = "Appointment.List";

    public AppointmentList(Context context){
        mContext = context;
        appointList = new ArrayList<>();
        LoadAppointment();
    }

    public void LoadAppointment() {
        Appointment appoint = new Appointment();
        appoint.setAppointId(23);
        appoint.setAppDate("11/04/2017");
        appoint.setAppDescr("nsijbfdisd");
        appoint.setAppPatWeight(87);
        appoint.setAppBabyHeartBeat("24/212");
        appoint.setAppPatBloodPres("90/12");
        appoint.setAppPatImc("23,4");
    }

    public void GetAppoint(final AppointmentList.RefreshHandler handler,int patientId) {

        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonArrayRequest patientsRequest = new JsonArrayRequest(Request.Method.GET,APPOINT_LIST_URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    appointList.clear();

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject o = response.getJSONObject(i);
                        Appointment app = new Appointment();
                        app.setAppointmentId(o.getInt("patientId"));
                        appointList.add(app);
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

    public List<Appointment> GetAppointmentList() {
        return appointList;
    }

    //
    // INTERFACES

    public interface RefreshHandler {
        void onRefreshCompleted(Boolean success);
    }
}*/
public class AppointmentList {

        Context mContext;
        int patientID = 0;
        List<Appointment> appointList;
        //
        // CONSTANTS

        public String APPOINT_LIST_URL = "http://cwbpi4.herokuapp.com/webapi/patient/";

        private String TAG = "Appointment.List";

        public AppointmentList(Context context,int patientId){
            mContext = context;
            patientID = patientId;
            appointList = new ArrayList<>();
        }

        public void GetAppoint(final RefreshHandler handler) {

            RequestQueue queue = Volley.newRequestQueue(mContext);
            JsonArrayRequest patientsRequest = new JsonArrayRequest(Request.Method.GET,APPOINT_LIST_URL + patientID + "/appointment", null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    try {
                        appointList.clear();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject o = response.getJSONObject(i);
                            Appointment app = new Appointment();
                            app.setAppDate(o.getString("appDate"));
                            appointList.add(app);
                        }

                        Log.i(TAG, "Consultas refreshed.");
                        handler.onRefreshCompleted(true);
                    } catch (JSONException e) {
                        Log.e(TAG, "Can' refresh consultas.", e);
                        handler.onRefreshCompleted(false);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Can' refresh the consultas.", error);
                    handler.onRefreshCompleted(false);
                }
            });

            queue.add(patientsRequest);

        }

        public List<Appointment> GetAppointmentList() {
            return appointList;
        }

    // INTERFACES

        public interface RefreshHandler {
            void onRefreshCompleted(Boolean success);
        }
    }
