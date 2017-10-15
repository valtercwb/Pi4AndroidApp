package adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import me.org.pi4projectprototype.R;
import model.Patient;
import model.PatientList;

/**
 * Created by valter.franco on 10/12/2017.
 */

public class PatientAdapter extends ArrayAdapter<Patient> {

        ProgressDialog mRefreshProgressDialog;
        PatientList patientList;
        ListView mPatientListView;

        public PatientAdapter(Context context, List<Patient> patients) {
            super(context,R.layout.list_item_patient,patients);
        }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_patient, null);
        }

            // Get components
            TextView patient = (TextView)convertView.findViewById(R.id.patient_view);
            TextView weeksPregnant = (TextView)convertView.findViewById(R.id.weeksPregnant_view);
            NetworkImageView flagView = (NetworkImageView)convertView.findViewById(R.id.patient_image_view);

            // Configure the view for this Country
            Patient p = getItem(position);
            patient.setText(p.getPatientName());
            weeksPregnant.setText("Semana: " + p.getPregnancyWeek());
            patientList.loadFlagIntoView(p, flagView);

        return v;
        }

    //
    // CONSTANTS

    public static final String COUNTRY_KEY = "Country";

    //
    // INTERFACE: CountryListHandler

    public void onRefreshCompleted(Boolean success) {
        mRefreshProgressDialog.hide();
        if (success) {
            PatientAdapter adapter = new PatientAdapter(getContext(),patientList.getPatients());
            mPatientListView.setAdapter(adapter);
        } else {
            Toast.makeText(getContext(), "Refresh failed.", Toast.LENGTH_LONG).show();
        }
    }
}