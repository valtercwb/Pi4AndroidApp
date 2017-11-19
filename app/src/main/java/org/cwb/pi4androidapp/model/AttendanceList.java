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

import static org.cwb.pi4androidapp.ws.Paths.AVAILABLE_APPOINTMENT_URL;

/**
 * Created by valter.franco on 11/18/2017.
 */

public class AttendanceList {

    Context mContext;
    List<Attendance> attendanceList;
    //
    // CONSTANTS

    private String TAG = "Attendance.List";

    public AttendanceList(Context context){
        mContext = context;
        attendanceList = new ArrayList<>();
    }

    public void GetAppoint(final AttendanceList.RefreshHandler handler) {

        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonArrayRequest patientsRequest = new JsonArrayRequest(Request.Method.GET,AVAILABLE_APPOINTMENT_URL , null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    attendanceList.clear();

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject o = response.getJSONObject(i);
                        Attendance attendance = new Attendance();
                        attendance.setAttendanceId(o.getInt("attendanceId"));
                        attendance.setAttendanceDate(o.getString("attendanceDate"));
                        attendance.setAttendanceHour(o.getString("attendanceHour"));

                        attendanceList.add(attendance);
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

    public List<Attendance> GetAttendanceList() {
        return attendanceList;
    }

    // INTERFACES

    public interface RefreshHandler {
        void onRefreshCompleted(Boolean success);
    }
}
