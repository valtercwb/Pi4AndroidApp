package org.cwb.pi4androidapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.cwb.pi4androidapp.R;
import org.json.JSONException;
import org.json.JSONObject;
import org.cwb.pi4androidapp.ws.Login;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    SharedPreferences sharedPref;

    String email;
    String pass;
    String loginShared = "";
    String passShared = "";
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = getSharedPreferences("org.cwb.pi4androidapp.activity", Context.MODE_PRIVATE);

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.login);
        mPasswordView = (EditText) findViewById(R.id.password);


        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);

        if (!mWifi.isConnected()) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
            alertDialog.setMessage("Você não está conectado! Deseja conectar-se a internet?")
                    .setNegativeButton("Não", null)
                    .setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                }})

                    .create()
                    .show();
            }

            mSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    email = mEmailView.getText().toString();
                    pass = mPasswordView.getText().toString();


                    if(sharedPref.getString("login", "")!= ""){
                        loginShared = sharedPref.getString("login", "");
                        passShared = sharedPref.getString("senha", "");
                        userName = sharedPref.getString("user", "");

                        if(loginShared.equalsIgnoreCase(email) && passShared.equalsIgnoreCase(pass)) {
                            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                            intent.putExtra("name", userName);
                            LoginActivity.this.startActivity(intent);
                        }

                    } else {
                        final Response.Listener<String> response = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);


                                    if (jsonObject.getString("userName") != null) {

                                        Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                                        intent.putExtra("name", jsonObject.getString("userName"));

                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putString("login", email);
                                        editor.putString("senha", pass);
                                        editor.putString("user", jsonObject.getString("userName"));
                                        editor.apply();

                                        LoginActivity.this.startActivity(intent);
                                    } else {
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                                        alertDialog.setMessage("Usuário ou senha inválidos")
                                                .setNegativeButton("Tentar Novamente?", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                                    alertDialog.setMessage("Usuário ou senha inválido!")
                                            .setNegativeButton("Verifique se digitou seus dados corretamente", null)
                                            .create()
                                            .show();
                                    e.printStackTrace();
                                }
                            }
                        };

                        Login lg = new Login(email, pass, response);
                        //mLoginFormView = findViewById(R.id.login_form);
                        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

                        queue.add(lg);

                    }
                }
            });
    }
}

