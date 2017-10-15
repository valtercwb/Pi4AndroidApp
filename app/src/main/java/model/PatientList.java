package model;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import activity.UserAreaActivity;
import me.org.pi4projectprototype.R;
import ws.VolleySingleton;

/**
 * Created by valter.franco on 10/12/2017.
 */

public class PatientList {

    Context mContext;
    List<Patient> patientList;
    static ImageLoader sImageLoader;

    public PatientList(Context context) {
        mContext = context;
        patientList = new ArrayList<>();

    }

    private ImageLoader getImageLoader() {
        if (sImageLoader == null) {
            sImageLoader = new ImageLoader(VolleySingleton.getInstance().getRequestQueue(), new ImageLoader.ImageCache() {
                private final LruCache<String, Bitmap>
                        cache = new LruCache<>(20);

                @Override
                public Bitmap getBitmap(String url) {
                    return cache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    cache.put(url, bitmap);
                }
            });
        }

        return sImageLoader;
    }

    // PUBLIC API

    /**
     * Carrega a lista de países de maneira assíncrona.
     *
     * @param handler Um handler que será notificado nos eventos de recarga.
     */
    public void refreshListFromGeonamesService(final RefreshHandler handler) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonArrayRequest patientsRequest = new JsonArrayRequest(Request.Method.GET,PATIENTS_LIST_URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    patientList.clear();

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject o = response.getJSONObject(i);
                        Patient p = new Patient();
                        p.setPatientName(o.getString("patientName"));
                        //p.setPregnancyWeek((double)o.getString("PregnancyWeek"));
                        patientList.add(p);
                    }

                    Log.i(TAG, "Pacientes refreshed.");
                    handler.onRefreshCompleted(true);
                } catch (JSONException e) {
                    Log.e(TAG, "Can' refresh the Pacientes.", e);
                    handler.onRefreshCompleted(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Can' refresh the country list.", error);
                handler.onRefreshCompleted(false);
            }
        });

        queue.add(patientsRequest);
    }

    /**
     * Carrega a imagem da bandeira no componente NetworkImageView.
     *
     * @param patient   O objeto Country representando o país do qual deseja obter a bandeira.
     * @param imageView O componente NetworkImageView que irá receber a imagem.
     */
    public void loadFlagIntoView(Patient patient, NetworkImageView imageView) {
        imageView.setDefaultImageResId(R.drawable.patientph);
        imageView.setImageUrl(patient.getPatientImage(), getImageLoader());
    }

    /**
     * Obtém a lista de países.
     *
     * @return A lista com os países carregados nesse objeto..
     */
    public List<Patient> getPatients() {
        return patientList;
    }


    //
    // CONSTANTS

    private final static String PATIENTS_LIST_URL = "http://f368af63.ngrok.io/PI4WebService/webapi/patient";
    private final static String TAG = "Patient.List";


    //
    // INTERFACES

    public interface RefreshHandler {
        void onRefreshCompleted(Boolean success);
    }

}



