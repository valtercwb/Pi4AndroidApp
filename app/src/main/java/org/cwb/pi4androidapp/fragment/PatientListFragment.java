package org.cwb.pi4androidapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.adapter.PatientAdapter;
import org.cwb.pi4androidapp.adapter.PatientAdapterClickListener;
import org.cwb.pi4androidapp.contract.PatientContract;
import org.cwb.pi4androidapp.model.Patient;
import org.cwb.pi4androidapp.presenter.PatientPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class PatientListFragment extends Fragment implements PatientContract.View, PatientAdapterClickListener {

    private static final String SCHEDULED_APPOINTMENTS = "SCHEDULED_APPOINTMENTS";
    private PatientContract.Presenter mPresenter;
    @BindView(R.id.patient_recycler_view)
    public RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Patient> mPatientList;

    public PatientListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(mPresenter == null){
            mPresenter = new PatientPresenter(this, getActivity().getApplicationContext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_list, container, false);
        ButterKnife.bind(this, v);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshUI();
            }
        });
        initRecyclerView();
        getActivity().setTitle(getString(R.string.pacientes));
        mSwipeRefreshLayout.setRefreshing(true);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mPresenter.refreshUI();
    }

    private void initRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    public void setPresenter(PatientContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPatients(List<Patient> patients) {
        // specify an adapter (see also next example)
        mPatientList = patients;
        mSwipeRefreshLayout.setRefreshing(false);

        mAdapter = new PatientAdapter(this, patients);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPatientClicked(Patient patient) {
        getFragmentManager().beginTransaction().addToBackStack(null).
        replace(R.id.content_navigation,PatientFragment.newInstance(patient)).commit();
    }
}
