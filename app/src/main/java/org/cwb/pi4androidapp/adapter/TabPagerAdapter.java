package org.cwb.pi4androidapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.cwb.pi4androidapp.fragment.AppointmentFragment;
import org.cwb.pi4androidapp.fragment.ExamFragment;
import org.cwb.pi4androidapp.fragment.PatientFragment;
import org.cwb.pi4androidapp.model.Patient;

public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    Bundle bundle;
    Patient p;
    Context mContext;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs,Bundle p, Context mContext) {
        super(fm);
        this.tabCount = numberOfTabs;
        this.bundle = p;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PatientFragment tab1 = new PatientFragment();
                tab1.setArguments(this.bundle);
                return tab1;
            case 1:
                AppointmentFragment tab2 = new AppointmentFragment();
                tab2.setArguments(this.bundle);
                return tab2;

            case 2:
                ExamFragment tab3 = new ExamFragment();
                tab3.setArguments(this.bundle);
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
