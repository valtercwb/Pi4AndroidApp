package org.cwb.pi4androidapp.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.model.Patient;

import java.util.List;

/**
 * Created by valter.franco on 11/2/2017.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
        private List<Patient> mAppointments;
        private PatientAdapterClickListener mClickListener;

        //Holds our views for each card
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private Patient currentPatient;
            public TextView mDateTextView;
            public TextView mPatientsName;

            private PatientAdapterClickListener mClickListener;

            public ViewHolder(View v, PatientAdapterClickListener listener) {
                super(v);
                mClickListener = listener;
                mDateTextView = (TextView) v.findViewById(R.id.date_textview);
                mPatientsName = (TextView) v.findViewById(R.id.patientname_textview);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onPatientClicked(currentPatient);
                    }
                });
            }
        }

        public PatientAdapter(PatientAdapterClickListener listener, @Nullable List<Patient> patients){
            super();
            mClickListener = listener;
            mAppointments = patients;
        }


        @Override
        public PatientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.patient_card, parent, false);
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
            Patient current = mAppointments.get(position);
            holder.currentPatient = current;
            String username = "";
            /*if(current.getUser() != null) {
                username = current.getUser().getName();
            }*/

            //holder.mDateTextView.setText(current.getFormattedLocalTime());
            holder.mPatientsName.setText(username);
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

