package org.cwb.pi4androidapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.fragment.AboutUsFragment;
import org.cwb.pi4androidapp.fragment.AppointByMonthFragment;
import org.cwb.pi4androidapp.fragment.PatientListFragment;
import org.cwb.pi4androidapp.fragment.ScheduledAppointmentsFragment;
import org.cwb.pi4androidapp.presenter.PatientPresenter;
import org.cwb.pi4androidapp.presenter.SchedulePresenter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    public NavigationView mNavigationView;
    ActionBarDrawerToggle toggle;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        sharedPref = getSharedPreferences("org.cwb.pi4androidapp.activity", Context.MODE_PRIVATE);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(0);
        onNavigationItemSelected(mNavigationView.getMenu().getItem(0));

        TextView txtProfileName = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.user_name);
        txtProfileName.setText(getIntent().getStringExtra("name"));

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            //launchDataFragment();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mWifi.isConnected()) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }else{
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_info:
                showLastConnectionDate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showLastConnectionDate() {
            if(sharedPref.getString("ultimoAcesso", "")!=null){
            String data = sharedPref.getString("ultimoAcesso", "");
            Toast.makeText(this,"Os dados do aplicativo podem estar desatualizados. Último acesso: "+data,Toast.LENGTH_LONG).show();
            }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_data) {
            LaunchDataFragment();
        }
        else if (id == R.id.nav_patient) {
            //launchPatientListFragment();
            Intent intent = new Intent(this, UserAreaActivity.class);
            intent.putExtra("tipo", 1);
            NavigationActivity.this.startActivity(intent);
        } else if (id == R.id.nav_appointment) {
            Intent intent = new Intent(this, UserAreaActivity.class);
            intent.putExtra("tipo", 2);
            NavigationActivity.this.startActivity(intent);
        } else if (id == R.id.nav_scheduled) {
            LaunchScheduledFragment();
        } else if (id == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_navigation, new AboutUsFragment()).commit();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void launchPatientListFragment() {
        PatientListFragment patientListFragment = new PatientListFragment();
        patientListFragment.setPresenter(new PatientPresenter(patientListFragment, getApplicationContext()));
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_navigation, patientListFragment).commit();
    }

    //Launches the scheduling fragment
    private void LaunchScheduledFragment() {
        ScheduledAppointmentsFragment scheduledFragment = new ScheduledAppointmentsFragment();
        scheduledFragment.setPresenter(new SchedulePresenter(scheduledFragment, getApplicationContext()));
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_navigation, scheduledFragment).commit();
    }

    private void LaunchDataFragment() {
        AppointByMonthFragment appointByMonth = new AppointByMonthFragment();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_navigation, appointByMonth).commit();
    }

}
