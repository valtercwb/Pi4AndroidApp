/*package ws;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by valter.franco on 9/2/2017.
 */
/*
public class Singleton {

    private static Singleton vs;
    private RequestQueue rq;
    private static Context ctx;

    public Singleton(Context context){
     ctx = context;
     rq = GetRequestQueue();
    }

    public RequestQueue GetRequestQueue(){
        if(rq==null){
            rq = Volley.newRequestQueue(ctx.getApplicationContext());
        }
       return rq;
    }

    public static synchronized  Singleton (Context context){
        if(vs==null){GetInstance vs = new Singleton(context);
        }
        return vs;
    }

    public<T> void AddToRequestQueue(Request<T> request){
        rq.add(request);
    }
}
*/