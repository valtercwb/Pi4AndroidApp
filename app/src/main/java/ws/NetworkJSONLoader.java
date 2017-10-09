package ws;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by valter.franco on 9/2/2017.
 */

public class NetworkJSONLoader {

    Context context;
    TextView display;

    public NetworkJSONLoader(Context con, TextView tv) {
        context = con;
        display = tv;
    }

    public void requestJSON(String url) {
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";
        final ProgressDialog pDialog = new ProgressDialog(context);

        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                display.setText(response.toString());
                pDialog.hide();
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                display.setText(error.toString());
                pDialog.hide();
            }

        });

        // Adding request to request queue
        VolleySingleton.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}

