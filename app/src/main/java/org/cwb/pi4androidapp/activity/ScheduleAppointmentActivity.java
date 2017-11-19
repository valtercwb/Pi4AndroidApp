package org.cwb.pi4androidapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.model.Attendance;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.cwb.pi4androidapp.ws.Paths.SCHEDULE_APPOINTMENT_URL;

public class ScheduleAppointmentActivity extends AppCompatActivity {

    int patientId = 0;
    int consultaId = 0;
    String patientName = "";
    String data = "";
    String hora = "";
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.tvpaciente) TextView tvNome;
    @BindView(R.id.textClock) TextView tvClock;
    @BindView(R.id.calendarView) CalendarView calendarView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.button_agenda) Button button;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setTitle("Agendar Consulta ");

        patientId = getIntent().getIntExtra("patientId",0);
        patientName = getIntent().getStringExtra("patientName");
        consultaId = getIntent().getIntExtra("consultaId",0);
        data = getIntent().getStringExtra("data");
        hora = getIntent().getStringExtra("hora");

        tvNome.setText(patientName);
        calendarView.setDate(formatCalendarDate(data),true,true);
        tvClock.setText(hora);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(ScheduleAppointmentActivity.this,
                R.array.doctors, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setSelection(0);
        spinner.setAdapter(spinnerAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attendance attendance = new Attendance();
                attendance.setAttendanceId(consultaId);
                attendance.setAttendancePatientId(patientId);
                attendance.setAttendanceDocId(0);
                attendance.setAttendanceHour(hora);
                attendance.setAttendanceHour(data);
                postNewAppointment(ScheduleAppointmentActivity.this,attendance);

            }
        });
    }

    public long formatCalendarDate(String date){
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

    public static void postNewAppointment(Context context, final Attendance attendance){

       // mPostCommentResponse.requestStarted();
        Gson gson = new Gson();
        String Str = gson.toJson(attendance);

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest sr = new StringRequest(Request.Method.POST,SCHEDULE_APPOINTMENT_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //mPostCommentResponse.requestCompleted();
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //mPostCommentResponse.requestEndedWithError(error);
            }

        });
        queue.add(sr);
    }
}
