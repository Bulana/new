package com.bulana.anew;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private static AppController mInstance;
    private RequestQueue mRequesQueue;

    //one thread a time
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public RequestQueue getRequestQueue() {

        if (mRequesQueue == null) {
            mRequesQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequesQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequesQueue != null) {
            mRequesQueue.cancelAll(tag);
        }
    }
}
