package org.cwb.pi4androidapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.adapter.TabPagerAdapter;
import org.cwb.pi4androidapp.fragment.AppointmentFragment;
import org.cwb.pi4androidapp.model.Appointment;
import org.cwb.pi4androidapp.model.AppointmentList;
import org.cwb.pi4androidapp.model.Patient;
import org.cwb.pi4androidapp.model.Patient.RefreshHandlerPatient;
import org.cwb.pi4androidapp.model.PatientList;

import java.util.List;


public class PatientDetailsActivity extends AppCompatActivity implements RefreshHandlerPatient,
AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = false;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    Bundle bundle = new Bundle();
    Patient p;
    PatientList pList;

    AppointmentList appointList;
    ListView mAppointListView;
    AppointAdapter appointAdapter;
    //_____________________________________
    ProgressDialog mRefreshProgressDialog;
    ViewPager viewPager;
    PagerAdapter adapter;
    TabLayout tabLayout;
    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        p = new Patient(this);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Paciente"));
        tabLayout.addTab(tabLayout.newTab().setText("Consultas"));
        tabLayout.addTab(tabLayout.newTab().setText("Exames"));

        Bundle bundlePatId = getIntent().getExtras();
        patientId = bundlePatId.getInt("patientId");
        Bundle patbun = getIntent().getExtras();
        Patient patt = patbun.getParcelable("Paciente");
        if (mRefreshProgressDialog == null) {
            // Cria e configura o objeto ProgressDialog
            mRefreshProgressDialog = new ProgressDialog(PatientDetailsActivity.this);
            mRefreshProgressDialog.setTitle("Aguarde");
            mRefreshProgressDialog.setMessage("Baixando paciente...");
            mRefreshProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        mRefreshProgressDialog.show();

        //LoadPatientData(PatientDetailsActivity.this);
       p.LoadPatientData(PatientDetailsActivity.this,patientId);

        //appointAdapter = new AppointAdapter(appointList.GetAppointmentList());
        //mAppointListView.setAdapter(appointAdapter);

        //appointList.GetAppoint((AppointmentList.RefreshHandler) PatientDetailsActivity.this,patientId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_layout, menu);
        return true;
    }

    @Override
    public void onRefreshCompletedPatient(Boolean success,Patient p) {
        mRefreshProgressDialog.hide();
        if (success) {

            //AppointAdapter appointAdapter = new AppointAdapter(appointList.GetAppointmentList());
           // mAppointListView.setAdapter(appointAdapter);

            bundle.putParcelable("pa",p);
            viewPager = (ViewPager) findViewById(R.id.pager);
            adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),bundle,this);
            viewPager.setAdapter(adapter);

            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        } else {
            Toast.makeText(this, "Aconteceu um erro ao acessar o servidor.", Toast.LENGTH_LONG).show();
        }
    }

    private void bindActivity() {
        //mToolbar        = (Toolbar) findViewById(R.id.main_toolbar);
        //mTitle          = (TextView) findViewById(R.id.main_textview_title);
        //mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        //mAppBarLayout   = (AppBarLayout) findViewById(R.id.main_appbar);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);

    }

    private void handleToolbarTitleVisibility(float percentage) {

        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }
    private void handleAlphaOnTitle(float percentage) {

        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {

        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f): new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(duration);

        alphaAnimation.setFillAfter(true);

        v.startAnimation(alphaAnimation);
    }

////////////////////////////////////////
public class AppointAdapter extends ArrayAdapter<Appointment> {

    public AppointAdapter(List<Appointment> appoint) {
        super(PatientDetailsActivity.this, 0, appoint);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // If we weren' given a view, inflate one
        if (convertView == null) {
            convertView = PatientDetailsActivity.this.getLayoutInflater().inflate(R.layout.historic_appoint_list, null);
        }

        // Get components
        TextView tvAppointDate = convertView.findViewById(R.id.textView10);
        TextView tvAppointReason = convertView.findViewById(R.id.textView11);

        // Configure the view for this Country
        Appointment c = getItem(position);
        tvAppointDate.setText(c.getAppointmentId());
        tvAppointReason.setText(c.getAppDate()+ " semanas ");

        return convertView;
    }
    }
}

