package org.cwb.pi4androidapp.adapter;

import org.cwb.pi4androidapp.model.Appointment;

/**
 * When an individual Appointment gets clicked
 */
public interface AppointmentAdapterClickListener {
    public void onAppointmentClicked(Appointment appointment);
}
