package org.cwb.pi4androidapp.fragment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.model.Patient;
import java.io.Serializable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientFragment extends Fragment {

    private Bundle bundle;
    Patient p = new Patient();
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;

    public PatientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient, container, false);

        ButterKnife.bind(this, v);
        addPatientDataToView();

        bundle = getArguments();

        if(bundle!=null)
           p = bundle.getParcelable("pa");
        collapsingToolbarLayout.setTitle(p.getPatientName());

        return v;
    }

    private void addPatientDataToView() {
    }

    public static PatientFragment newInstance(Patient patient) {
        PatientFragment fragment = new PatientFragment();
        Bundle args = new Bundle();
        args.putSerializable("sdsd", (Serializable) patient);
        fragment.setArguments(args);
        return fragment;
    }
}
