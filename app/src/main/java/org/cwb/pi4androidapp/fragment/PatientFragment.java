package org.cwb.pi4androidapp.fragment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.model.Patient;
import java.io.Serializable;
import java.util.Optional;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientFragment extends Fragment {

    private Bundle bundle;
    Patient p = new Patient();
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etPhone) EditText etPhone;
    @BindView(R.id.etAge) EditText etAge;
    @BindView(R.id.etBloodType) EditText etBloodType;
    @BindView(R.id.etPartnerBloodType) EditText etPartnerBloodType;
    @BindView(R.id.etStatus) EditText etStatus;

    @BindView(R.id.etPatCode) EditText etPatCode;
    @BindView(R.id.etPlanned) EditText etPlanned;
    @BindView(R.id.etRisk) EditText etRisk;
    @BindView(R.id.etPregnancyWeek) EditText etPregnancyWeek;

    @BindView(R.id.etNeoplasm) EditText etNeo;
    @BindView(R.id.etHeartDisease) EditText etHeart;
    @BindView(R.id.etMentalIllness) EditText etMental;
    @BindView(R.id.etAnomaly) EditText etAnomaly;
    @BindView(R.id.etDiabetes) EditText etDiabetes;
    @BindView(R.id.etTrombo) EditText etTrombo;
    @BindView(R.id.etChlamydia) EditText etChlamydia;
    @BindView(R.id.etSyphilis) EditText etSyphilis;
    @BindView(R.id.etGonorrhea) EditText etGonorrhea;
    @BindView(R.id.etHiv) EditText etHiv;

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

        toolbar.setTitle(p.getPatientName());

        etName.setText(p.getPatientName());
        etEmail.setText(p.getPatientEmail());
        etPhone.setText(p.getPatientPhone());
        etAge.setText(String.valueOf(p.getPatientAge()));
        etBloodType.setText(p.getPatientBloodType());
        etPartnerBloodType.setText(p.getPartnerBloodType());

        etStatus.setText((p.isActive()) ? "Ativa":"Inativa");

        etPatCode.setText(String.valueOf(p.getPatientCod()));
        etPlanned.setText((p.isPlanned())?"Sim":"Não");
        etRisk.setText((p.isRisk())?"Sim":"Não");
        etPregnancyWeek.setText(p.getPregnancyWeek().toString());

        etNeo.setText((p.isNeoplasia())?"Sim":"Não");
        etHeart.setText((p.isCardiopatia())?"Sim":"Não");
        etMental.setText((p.isDoencaMental())?"Sim":"Não");
        etAnomaly.setText((p.isAnomalia())?"Sim":"Não");
        etDiabetes.setText((p.isDiabetes())?"Sim":"Não");
        etTrombo.setText((p.isTromboembo())?"Sim":"Não");
        etChlamydia.setText((p.isClamidia())?"Sim":"Não");
        etSyphilis.setText((p.isSifilis())?"Sim":"Não");
        etGonorrhea.setText((p.isGonorreia())?"Sim":"Não");
        etHiv.setText((p.isHiv())?"Sim":"Não");

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
