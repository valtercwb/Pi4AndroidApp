package org.cwb.pi4androidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    String email;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.login);
        mPasswordView = (EditText) findViewById(R.id.password);


        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                email = mEmailView.getText().toString();
                pass = mPasswordView.getText().toString();

                final Response.Listener<String> response = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);


                            if (jsonObject.getString("userName") != null) {

                                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                                intent.putExtra("name", jsonObject.getString("userName"));

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
        });
    }
}

