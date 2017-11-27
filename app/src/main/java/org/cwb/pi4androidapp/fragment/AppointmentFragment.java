package org.cwb.pi4androidapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.model.Appointment;
import org.cwb.pi4androidapp.model.AppointmentList;
import org.cwb.pi4androidapp.model.Patient;
import org.cwb.pi4androidapp.model.PatientList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AppointmentFragment extends Fragment {

    Bundle bundle;
    Patient p;
    int patientId;

    @BindView(R.id.appoint_list)
    ListView mAppointListView;

    //DatabaseTable db = new DatabaseTable(this);
    private ArrayList<Appointment> filteredList;

    public AppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bundle = getArguments();
        if (bundle != null)
            p = bundle.getParcelable("pa");
        patientId = p.getPatientId();

        // Configurar os Componentes

        return inflater.inflate(R.layout.fragment_appointment, container, false);
    }


}
