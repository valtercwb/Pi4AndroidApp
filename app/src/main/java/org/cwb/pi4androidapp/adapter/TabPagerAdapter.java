package org.cwb.pi4androidapp.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import org.cwb.pi4androidapp.fragment.AppointmentFragment;
import org.cwb.pi4androidapp.fragment.ExamFragment;
import org.cwb.pi4androidapp.fragment.PatientFragment;
import org.cwb.pi4androidapp.model.Patient;

/**
 * Created by Neil on 12/3/2015.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    Bundle bundle;
    Patient p;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs,Bundle p) {
        super(fm);
        this.tabCount = numberOfTabs;
        this.bundle = p;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PatientFragment tab1 = new PatientFragment();
                // bundle.putParcelable("patient", data);
                //bundle.putParcelable("pa",p);
                 tab1.setArguments(this.bundle);

                return tab1;
            case 1:
                AppointmentFragment tab2 = new AppointmentFragment();
                return tab2;
            case 2:
                ExamFragment tab3 = new ExamFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
