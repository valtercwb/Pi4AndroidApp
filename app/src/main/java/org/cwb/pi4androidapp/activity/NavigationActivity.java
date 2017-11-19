package org.cwb.pi4androidapp.activity;

import android.content.Intent;
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

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.fragment.AboutUsFragment;
import org.cwb.pi4androidapp.fragment.PatientListFragment;
import org.cwb.pi4androidapp.presenter.PatientPresenter;


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

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_navigation);
            ButterKnife.bind(this);
            setSupportActionBar(mToolbar);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            mNavigationView.setNavigationItemSelectedListener(this);
            //mNavigationView.setCheckedItem(R.id.nav_data);

            TextView txtProfileName = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.user_name);
            txtProfileName.setText(getIntent().getStringExtra("name"));

            if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
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
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_patient) {
                //launchPatientListFragment();
                Intent intent = new Intent(this,UserAreaActivity.class);
                intent.putExtra("tipo", 1);
                NavigationActivity.this.startActivity(intent);
            } else if (id == R.id.nav_appointment) {
               // launchScheduleFragment();
               // launchAppointmentsFragment();
                Intent intent = new Intent(this,UserAreaActivity.class);
                intent.putExtra("tipo", 2);
                NavigationActivity.this.startActivity(intent);
            } else if (id == R.id.nav_data) {
                //launchDataFragment();
            }
            else if (id == R.id.nav_contraction) {
                //launchAppointmentsFragment();
            }else if (id == R.id.nav_about) {
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
        /*private void launchScheduleFragment() {
            ScheduleFragment scheduleFragment = new ScheduleFragment();
            scheduleFragment.setPresenter(new SchedulePresenter(scheduleFragment, getApplicationContext()));
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_navigation, scheduleFragment).commit();
        }*/



    }


