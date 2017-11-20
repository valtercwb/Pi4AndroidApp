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
import org.cwb.pi4androidapp.adapter.AppointmentAdapter;
import org.cwb.pi4androidapp.adapter.AppointmentAdapterClickListener;
import org.cwb.pi4androidapp.contract.AppointmentContract;
import org.cwb.pi4androidapp.model.Appointment;
import org.cwb.pi4androidapp.model.Attendance;
import org.cwb.pi4androidapp.presenter.AppointmentsPresenter;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduledAppointmentsFragment extends Fragment implements AppointmentContract.View,AppointmentAdapterClickListener {

    private AppointmentContract.Presenter mPresenter;
    @BindView(R.id.schedule_recycler_view)
    public RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Attendance> mScheduledAppointments;

    public ScheduledAppointmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mPresenter == null){
            mPresenter = new AppointmentsPresenter(this, getActivity().getApplicationContext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scheduled_appointments, container, false);
        ButterKnife.bind(this, v);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshUI();
            }
        });
        initRecyclerView();
        getActivity().setTitle(getString(R.string.scheduled_appointments));
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
    public void setPresenter(AppointmentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAppointments(List<Attendance> scheduledAppointments) {
        mScheduledAppointments = scheduledAppointments;
        mSwipeRefreshLayout.setRefreshing(false);

        mAdapter = new AppointmentAdapter(this, scheduledAppointments);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAppointmentClicked(Attendance appointment) {
        /*getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_navigation,
                AppointmentFragment.newInstance(appointment)).commit();*/
    }

}
