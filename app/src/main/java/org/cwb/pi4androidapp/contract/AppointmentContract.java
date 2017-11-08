package org.cwb.pi4androidapp.contract;

import org.cwb.pi4androidapp.base.BasePresenter;
import org.cwb.pi4androidapp.base.BaseView;
import org.cwb.pi4androidapp.model.Appointment;

import java.util.List;

/**
 * Contract for the View and Presenters of an Appointment
 */
public interface AppointmentContract
{
    interface View extends BaseView<Presenter> {
        /**
         * Show the appointments
         * @param scheduledAppointments - latest list of scheduled appointments
         */
        void showAppointments(List<Appointment> scheduledAppointments);
    }

    interface Presenter extends BasePresenter {
        /**
         * UI needs to be refreshed
         */
        void refreshUI();

        /**
         * An Appointment was clicked
         * @param appointment - appointment that was clicked
         */
        void AppointmentClicked(Appointment appointment);
    }
}
