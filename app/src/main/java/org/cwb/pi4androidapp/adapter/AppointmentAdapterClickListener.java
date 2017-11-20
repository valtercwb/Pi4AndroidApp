package org.cwb.pi4androidapp.adapter;

import org.cwb.pi4androidapp.model.Appointment;
import org.cwb.pi4androidapp.model.Attendance;

/**
 * When an individual Appointment gets clicked
 */
public interface AppointmentAdapterClickListener {
    public void onAppointmentClicked(Attendance appointment);
}
