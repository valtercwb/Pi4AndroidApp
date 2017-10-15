package activity;

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

import org.json.JSONException;
import org.json.JSONObject;

import me.org.pi4projectprototype.R;
import ws.Login;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    String email;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                email = mEmailView.getText().toString();
                pass = mPasswordView.getText().toString();

                final Response.Listener<String> response = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject != null) {
                                   if(jsonObject.getString("userName")!=""){

                                    Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                    intent.putExtra("name", jsonObject.getString("userName"));
                                    intent.putExtra("test", "jadhkjfsbdk");
                                    LoginActivity.this.startActivity(intent);
                                }else {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                                alertDialog.setMessage("Usuário ou senha inválidos")
                                        .setNegativeButton("Tentar Novamente?", null)
                                        .create()
                                        .show();
                            }
                            } else {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                                alertDialog.setMessage("Falha ao logar")
                                        .setNegativeButton("Tentar Novamente?", null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Login lg = new Login(email, pass, response);
                mLoginFormView = findViewById(R.id.login_form);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

                queue.add(lg);
            }
        });
    }
}

