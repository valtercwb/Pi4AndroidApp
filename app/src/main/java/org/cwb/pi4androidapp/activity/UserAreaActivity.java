package org.cwb.pi4androidapp.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.database.DatabaseTable;
import org.cwb.pi4androidapp.model.Patient;
import org.cwb.pi4androidapp.model.PatientList;

public class UserAreaActivity extends AppCompatActivity implements PatientList.RefreshHandler {

    PatientList mPatientsList;
    ListView mPatientListView;
    ProgressDialog mRefreshProgressDialog;
    PatientsAdapter adapter;

    //
    DatabaseTable db = new DatabaseTable(this);
    private ArrayList<Patient> filteredList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
        int x = intent.getIntExtra("tipo",1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        if(x==1){
           getSupportActionBar().setTitle("Pacientes ");
        }else{
            getSupportActionBar().setTitle("Marcar consulta");
        }

        // Configurar os Componentes
        mPatientListView = (ListView) findViewById(R.id.patient_list);
        mPatientsList = new PatientList(this);


        adapter = new PatientsAdapter(mPatientsList.getPatients());
        mPatientListView.setAdapter(adapter);
        if(x == 1){
        mPatientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Patient pa = ((PatientsAdapter) mPatientListView.getAdapter()).getItem(i);

                Intent intent = new Intent(UserAreaActivity.this, PatientDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("patientId", pa.getPatientId());
                bundle.putParcelable("Paciente", pa);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });}
        else{
            mPatientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Patient pa = ((PatientsAdapter) mPatientListView.getAdapter()).getItem(i);

                    Intent intent = new Intent(UserAreaActivity.this, AttendanceActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("patientId", pa.getPatientId());
                    bundle.putString("patientName", pa.getPatientName());

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        if (mRefreshProgressDialog == null) {
            // Cria e configura o objeto ProgressDialog
            mRefreshProgressDialog = new ProgressDialog(UserAreaActivity.this);
            mRefreshProgressDialog.setTitle("Aguarde");
            mRefreshProgressDialog.setMessage("Baixando lista de pacientes...");
            mRefreshProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        // Exibe o diálogo de progresso antes de iniciar a operação de recarregar os países.
        mRefreshProgressDialog.show();
        mPatientsList.GetPatientsList(UserAreaActivity.this);
        // }

        //handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        // SearchView
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        //searchView.setOnQueryTextListener(this);
        //SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                toast("Buscar o texto: " + query);
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });*/

        return true;

    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    // CLASSES

    public class PatientsAdapter extends ArrayAdapter<Patient> {

        public PatientsAdapter(List<Patient> countries) {
            super(UserAreaActivity.this, 0, countries);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren' given a view, inflate one
            if (convertView == null) {
                convertView = UserAreaActivity.this.getLayoutInflater().inflate(R.layout.list_item_patient, null);
            }

            // Get components
            TextView countryTextView = convertView.findViewById(R.id.patient_view);
            TextView capitalTextView = convertView.findViewById(R.id.weeksPregnant_view);
            NetworkImageView flagView = (NetworkImageView) convertView.findViewById(R.id.patient_image_view);

            // Configure the view for this Country
            Patient c = getItem(position);
            countryTextView.setText(c.getPatientName());
            capitalTextView.setText(c.getPregnancyWeek().toString() + " semanas ");
            mPatientsList.loadFlagIntoView(c, flagView);

            return convertView;
        }
    }

    // INTERFACE: CountryListHandler

    @Override
    public void onRefreshCompleted(Boolean success) {
        mRefreshProgressDialog.hide();
        if (success) {
            PatientsAdapter adapter = new PatientsAdapter(mPatientsList.getPatients());
            mPatientListView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Aconteceu um erro ao acessar o servidor.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            Cursor c = db.getWordMatches(query, null);
            //process Cursor and display results}
        }
    }
}
