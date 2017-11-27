package org.cwb.pi4androidapp.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.model.Attendance;

import java.util.List;

/**
 * Adapter for Appointment data, basically encapsulates data for various Android Widgets to bind to
 */
public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private List<Attendance> mAppointments;
    private AppointmentAdapterClickListener mClickListener;

    //Holds our views for each card
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Attendance currentAppointment;
        public TextView tvDate;
        public TextView tvPatient;
        public TextView tvDoctor;

        private AppointmentAdapterClickListener mClickListener;

        public ViewHolder(View v, AppointmentAdapterClickListener listener) {
            super(v);
            mClickListener = listener;
            tvDate = (TextView) v.findViewById(R.id.tv_date);
            tvPatient = (TextView) v.findViewById(R.id.tv_patient);
            tvDoctor = (TextView) v.findViewById(R.id.tv_doctor);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onAppointmentClicked(currentAppointment);
                }
            });
        }
    }

    public AppointmentAdapter(AppointmentAdapterClickListener listener, @Nullable List<Attendance> attendances){
        super();
        mClickListener = listener;
        mAppointments = attendances;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_card, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ViewHolder vh = new ViewHolder(v, mClickListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Attendance current = mAppointments.get(position);
        holder.currentAppointment = current;
        String username = "";
        if(current.getAttendanceDate() != null) {
            username = current.getAttendanceDate();
        }

        holder.tvDate.setText(current.getAttendanceDate()+"  "+current.getAttendanceHour());
        holder.tvPatient.setText(current.getAttendancePatientName());
        holder.tvDoctor.setText(current.getAttendanceDoctorName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mAppointments != null) {
            return mAppointments.size();
        } else{
            return 0;
        }
    }

}
