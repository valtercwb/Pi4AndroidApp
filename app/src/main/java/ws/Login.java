package ws;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by valter.franco on 9/7/2017.
 */

public class Login extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://42d29b2a.ngrok.io/PI4WebService/webapi/login";
    private Map<String, String> params;


    public Login(String email, String pass, Response.Listener<String> listener) {
        super(Method.GET, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("userLogin", email);
        params.put("userPass", pass);

    }

    @Override
    public Map<String, String> getHeaders() {
        return params;
    }
}
