package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.Objects;

// Ressources: https://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a/12575319#12575319https://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a/12575319#12575319

public class MyAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "HAMMER DOWN";
    private static final String LOG_TAG = "Freeze, don't move!";

    private static MyApi myApiService = null;

    public AsyncResponseHandler responseHandler;

    void setResponseHandler(AsyncResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(new NetHttpTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
            return myApiService.printJoke().execute().getData();
        } catch (IOException e) {
            Log.e(LOG_TAG, Objects.requireNonNull(e.getMessage()));
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        responseHandler.responseHandle(result);
    }
}
