package activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import adapter.PatientAdapter;
import me.org.pi4projectprototype.R;
import model.Patient;
import model.PatientList;

import static adapter.PatientAdapter.COUNTRY_KEY;

public class UserAreaActivity extends AppCompatActivity implements PatientList.RefreshHandler {

    PatientList mPatientsList;
    ListView mPatientListView;
    ProgressDialog mRefreshProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        mPatientListView = (ListView) findViewById(R.id.patient_list);
        mPatientsList = new PatientList(UserAreaActivity.this);

        final EditText etUserName = (EditText) findViewById(R.id.user_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String test = intent.getStringExtra("test");

        etUserName.setText(name);

        if (mRefreshProgressDialog == null) {
            // Cria e configura o objeto ProgressDialog
            mRefreshProgressDialog = new ProgressDialog(UserAreaActivity.this);
            mRefreshProgressDialog.setTitle("Pacientes");
            mRefreshProgressDialog.setMessage("Carregando lista...");
            mRefreshProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        }

        // Exibe o diálogo de progresso antes de iniciar a operação de recarregar os países.
        mRefreshProgressDialog.show();
        mPatientsList.refreshListFromGeonamesService(UserAreaActivity.this);


        PatientAdapter adapter = new PatientAdapter(UserAreaActivity.this, mPatientsList.getPatients());
        mPatientListView.setAdapter(adapter);
        mPatientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Patient p = ((PatientAdapter) mPatientListView.getAdapter()).getItem(i);

                Intent intent = new Intent(UserAreaActivity.this, PatientDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(COUNTRY_KEY, p);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefreshCompleted(Boolean success) {
        mRefreshProgressDialog.hide();
        if (success) {
            PatientAdapter adapter = new PatientAdapter(UserAreaActivity.this, mPatientsList.getPatients());
            mPatientListView.setAdapter(adapter);
        } else {
            Toast.makeText(UserAreaActivity.this, "Refresh failed.", Toast.LENGTH_LONG).show();
        }
    }
}
