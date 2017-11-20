package org.cwb.pi4androidapp.contract;

import android.app.AlertDialog;

import org.cwb.pi4androidapp.base.BasePresenter;
import org.cwb.pi4androidapp.base.BaseView;
import org.cwb.pi4androidapp.model.Attendance;

/**
 * Contract for View and Presenter of Scheduling an Appointment
 */
public interface SchedulingContract {
    interface View extends BaseView<Presenter> {
        /**
         * Show the user that the view is working
         * @param show - should show
         */
        void showWorking(boolean show);

        /**
         * Show successful request
         * @param alertDialog - alert dialog to be shown
         */
        void showSuccess(AlertDialog alertDialog);

        /**
         * Show that the request failed
         * @param alertDialog - error dialog
         */
        void showFailure(AlertDialog alertDialog);
    }

    interface Presenter extends BasePresenter {
        /**
         * Submit an appointment
         * @param firstName - user first name
         * @param lastName - user last name
         */
        void submitAppointment(Attendance attendance);
    }
}
