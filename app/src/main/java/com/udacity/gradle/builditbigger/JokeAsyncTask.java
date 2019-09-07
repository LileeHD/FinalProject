package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import lilee.hd.jokedisplay.DisplayActivity;

public class JokeAsyncTask extends AsyncTask<Context, Void, String> {

    private static final String TAG= "HAMMER DOWN";

    private static MyApi myApiService = null;
    private Context mContext;

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null){
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
        mContext = params[0];
        try{
            return myApiService.printJoke().execute().getData();
        }catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(mContext, "Overwatch dad's joke", Toast.LENGTH_LONG).show();
//        Log.v(TAG, "NANOBOOST IS READY");
        Intent intent = new Intent(mContext, DisplayActivity.class);
        intent.putExtra(DisplayActivity.JOKE_EXTRA, result);
        mContext.startActivity(intent);



    }

}
