package org.cwb.pi4androidapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.cwb.pi4androidapp.R;
import org.cwb.pi4androidapp.model.Attendance;
import org.cwb.pi4androidapp.model.AttendanceList;
import org.cwb.pi4androidapp.model.Patient;

import java.util.List;

public class AttendanceActivity extends AppCompatActivity implements AttendanceList.RefreshHandler{

    Patient p;
    AttendanceList mAttendanceList;
    ListView mAttendanceListView;
    ProgressDialog mRefreshProgressDialog;
    AppointAdapter adapter;
    Bundle bundle;
    int patientId = 0;
    String patienName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setTitle("Escolha uma data");

            patientId = getIntent().getIntExtra("patientId",0);
            patienName = getIntent().getStringExtra("patientName");

        // Configurar os Componentes
        mAttendanceListView = (ListView) findViewById(R.id.appoint_list);
        mAttendanceList = new AttendanceList(this);

        adapter = new AppointAdapter(mAttendanceList.GetAttendanceList());
        mAttendanceListView.setAdapter(adapter);

        mAttendanceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Attendance att = ((AppointAdapter) mAttendanceListView.getAdapter()).getItem(i);

                Intent intent = new Intent(AttendanceActivity.this, ScheduleAppointmentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("patientId", patientId);
                bundle.putInt("consultaId",att.getAttendanceId());
                bundle.putString("data", att.getAttendanceDate().toString());
                bundle.putString("hora", att.getAttendanceHour().toString());
                bundle.putString("patientName", patienName);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        if (mRefreshProgressDialog == null) {
            // Cria e configura o objeto ProgressDialog
            mRefreshProgressDialog = new ProgressDialog(AttendanceActivity.this);
            mRefreshProgressDialog.setTitle("Aguarde");
            mRefreshProgressDialog.setMessage("Carregando lista de horários...");
            mRefreshProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        // Exibe o diálogo de progresso antes de iniciar a operação de recarregar os países.
        mRefreshProgressDialog.show();
        mAttendanceList.GetAppoint(AttendanceActivity.this);
    }

    @Override
    public void onRefreshCompleted(Boolean success) {
        mRefreshProgressDialog.hide();
        if (success) {
            AppointAdapter adapter = new AppointAdapter(mAttendanceList.GetAttendanceList());
            mAttendanceListView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Aconteceu um erro ao acessar o servidor.", Toast.LENGTH_LONG).show();
        }
    }

    public class AppointAdapter extends ArrayAdapter<Attendance> {

        public AppointAdapter(List<Attendance> appoint) {
            super(AttendanceActivity.this, 0, appoint);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren' given a view, inflate one
            if (convertView == null) {
                convertView = AttendanceActivity.this.getLayoutInflater().inflate(R.layout.list_item_appoint, null);
            }

            // Get components
            TextView tvAppointDate = convertView.findViewById(R.id.tvAppointDate);
            TextView tvSpeacialty = convertView.findViewById(R.id.tvSpeacialty);

            // Configure the view for this Country
            Attendance c = getItem(position);
            tvAppointDate.setText(c.getAttendanceDate().toString());
            tvSpeacialty.setText(c.getAttendanceHour());

            return convertView;
        }
    }
}
