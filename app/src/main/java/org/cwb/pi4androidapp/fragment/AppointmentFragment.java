package org.cwb.pi4androidapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
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
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import org.cwb.pi4androidapp.R;

import org.cwb.pi4androidapp.model.AppointList;
import org.cwb.pi4androidapp.model.Appointment;

import org.cwb.pi4androidapp.model.Patient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AppointmentFragment extends Fragment{

   /* Bundle bundle;
    Patient p;
    int patientId;
    AppointList mAppointsList;
    AppointsAdapter adapter;*/

    @BindView(R.id.appoint_list)
    ListView mAppointListView;

    public AppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_appointment, container, false);
        /*bundle = getArguments();
        if (bundle != null)
            p = bundle.getParcelable("pa");
        patientId = p.getPatientId();

        mAppointntListView = (ListView) v.findViewById(R.id.appoint_list);
        mAppointsList = new AppointList(getActivity());

        adapter = new AppointsAdapter(mAppointsList.getAppoints());
        mAppointntListView.setAdapter(adapter);

        mAppointsList.GetAppointsList((AppointList.RefreshHandler) getActivity(),patientId);*/
        return v;
    }


   /* public void onRefreshCompleted(Boolean success) {

        if (success) {
            AppointsAdapter adapter = new AppointsAdapter(mAppointsList.getAppoints());
            mAppointListView.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity(), "Aconteceu um erro ao acessar o servidor.", Toast.LENGTH_LONG).show();
        }
    }

    public class AppointsAdapter extends ArrayAdapter<Appointment> {

        public AppointsAdapter(List<Appointment> appoints) {
            super(getActivity(), 0, appoints);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren' given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.historic_appoint_list,null);
            }

            // Get components
            TextView textView10 = convertView.findViewById(R.id.textView10);
            TextView textView11 = convertView.findViewById(R.id.textView11);

            // Configure the view for this Country
            Appointment c = getItem(position);
            textView10.setText(c.getAppointmentId());
            textView11.setText(c.getAppDate().toString());
            //mPatientsList.loadFlagIntoView(c, flagView);

            return convertView;
        }
    }*/
}
