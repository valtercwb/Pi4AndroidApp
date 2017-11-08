package org.cwb.pi4androidapp.contract;

import org.cwb.pi4androidapp.base.BasePresenter;
import org.cwb.pi4androidapp.base.BaseView;
import org.cwb.pi4androidapp.model.Patient;

import java.util.List;

/**
 * Created by valter.franco on 11/2/2017.
 */

public interface PatientContract {

    interface View extends BaseView<Presenter> {
        /**
         * Show the appointments
         * @param patientList - latest list of scheduled appointments
         */
        void showPatients(List<Patient> patientList);
    }

    interface Presenter extends BasePresenter {
        /**
         * UI needs to be refreshed
         */
        void refreshUI();

        /**
         * An Appointment was clicked
         * @param patient - appointment that was clicked
         */
        void patientListClicked(Patient patient);
    }
}

