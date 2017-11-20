package org.cwb.pi4androidapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.contract.SchedulingContract;
import org.cwb.pi4androidapp.model.Attendance;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.cwb.pi4androidapp.ws.Paths.SCHEDULE_APPOINTMENT_URL;

public class ScheduleAppointmentActivity extends AppCompatActivity implements SchedulingContract.View {

    private SchedulingContract.Presenter mPresenter;

    int patientId = 0;
    int consultaId = 0;
    String patientName = "";
    String data = "";
    String hora = "";
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.tvpaciente)
    TextView tvNome;
    @BindView(R.id.textClock)
    TextView tvClock;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.button_agenda)
    Button button;
    @BindView(R.id.progress_bar)
    protected ProgressBar mProgressSpinner;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setTitle("Agendar Consulta ");

        patientId = getIntent().getIntExtra("patientId", 0);
        patientName = getIntent().getStringExtra("patientName");
        consultaId = getIntent().getIntExtra("consultaId", 0);
        data = getIntent().getStringExtra("data");
        hora = getIntent().getStringExtra("hora");

        tvNome.setText(patientName);
        calendarView.setDate(formatCalendarDate(data), true, true);
        tvClock.setText(hora);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(ScheduleAppointmentActivity.this,
                R.array.doctors, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setSelection(0);
        spinner.setAdapter(spinnerAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinner.getSelectedItemPosition()!=0){
                    final Attendance attendance = new Attendance();
                    attendance.setAttendanceId(consultaId);
                    attendance.setAttendancePatientId(patientId);
                    attendance.setAttendanceDocId(spinner.getSelectedItemPosition());
                    attendance.setAttendanceHour(hora);
                    attendance.setAttendanceDate(data);
                    postNewAppointment(ScheduleAppointmentActivity.this, attendance);

                   /* new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.submitAppointment(attendance);
                        }
                    }).start();*/
                }else{
                    Toast.makeText(ScheduleAppointmentActivity.this,"É necessário selecionar um médico",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public long formatCalendarDate(String date) {
        String parts[] = date.split("-");

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        month--;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milliTime = calendar.getTimeInMillis();

        return milliTime;
    }

    public static void postNewAppointment(Context context, final Attendance attendance) {

        // mPostCommentResponse.requestStarted();
        Gson gson = new Gson();
        String jsonBody = gson.toJson(attendance);
        try {
            RequestQueue queue = Volley.newRequestQueue(context);

            final String mRequestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, SCHEDULE_APPOINTMENT_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {

                        responseString = String.valueOf(response.statusCode);

                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            queue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPresenter(SchedulingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public void showWorking(final boolean show) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(show) {
                    mProgressSpinner.setVisibility(View.VISIBLE);
                } else {
                    mProgressSpinner.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void showSuccess(AlertDialog alertDialog) {
        alertDialog.show();
    }

    @Override
    public void showFailure(AlertDialog alertDialog) {

    }
}
