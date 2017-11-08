package org.cwb.pi4androidapp.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.cwb.pi4androidapp.contract.SchedulingContract;
import org.cwb.pi4androidapp.model.Appointment;
import org.cwb.pi4androidapp.model.NetworkingError;
import org.cwb.pi4androidapp.model.User;
import org.cwb.pi4androidapp.ws.Paths;
import org.cwb.pi4androidapp.ws.Utility;

import java.io.UnsupportedEncodingException;

/**
 * Presenter for Scheduling an Appointment
 */
public class SchedulingPresenter implements SchedulingContract.Presenter {
    public static final String TAG = "SchedulingPresenter";
    private SchedulingContract.View mView;
    private Appointment mAppointment;
    private RequestQueue mRequestQueue;


    public SchedulingPresenter(SchedulingContract.View view, Appointment appointment, Context context){
        mView = view;
        mAppointment = appointment;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void submitAppointment(String firstName, String lastName) {
        mView.showWorking(true);
        String url = Paths.AVAILABLE_APPOINTMENTS_URL + "/" + mAppointment.getAppointmentId();

        mAppointment.setUser(new User(firstName, lastName));

        final String json = Utility.convertPojoToString(mAppointment);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response);
                mView.showWorking(false);
                mView.showSuccess(DialogFactory.getSuccessDialog(mView.getActivity()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                NetworkingError networkingError;
                String json = new String(error.networkResponse.data);

                networkingError = Utility.convertJsonStringToPojo(json, NetworkingError.class);
                AlertDialog dialog = DialogFactory.getDialogForError(mView.getActivity(), networkingError);
                mView.showFailure(dialog);
                mView.showWorking(false);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return json == null ? null : json.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", json, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        mRequestQueue.add(stringRequest);
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
}
