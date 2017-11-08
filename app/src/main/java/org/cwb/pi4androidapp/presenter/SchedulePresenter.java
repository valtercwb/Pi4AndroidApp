package org.cwb.pi4androidapp.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.cwb.pi4androidapp.contract.AppointmentContract;

import java.util.Collections;
import java.util.List;

import org.cwb.pi4androidapp.model.Appointment;
import org.cwb.pi4androidapp.ws.Paths;
import org.cwb.pi4androidapp.ws.Utility;

/**
 * Get Presenter for the Scheduling data
 */
public class SchedulePresenter implements AppointmentContract.Presenter {
    private RequestQueue mRequestQueue;
    public static final String TAG = "SchedulePresenter";
    private AppointmentContract.View mView;

    public SchedulePresenter(AppointmentContract.View view, Context context){
        mView = view;
        mRequestQueue = Volley.newRequestQueue(context);
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
    public void refreshUI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringRequest scheduleRequest = new StringRequest(Request.Method.GET, Paths.SCHEDULED_APPOINTMENTS_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                List<Appointment> appointments = Utility.convertJsonStringToList(response, Appointment[].class);

                                Collections.sort(appointments);
                                mView.showAppointments(appointments);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Request Failed: " + error.getLocalizedMessage());

                    }
                });

                scheduleRequest.setRetryPolicy(new DefaultRetryPolicy(
                        Paths.TIMEOUT_TIME,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                mRequestQueue.add(scheduleRequest);
            }
        }).start();
    }

    @Override
    public void AppointmentClicked(Appointment appointment) {

    }
}
