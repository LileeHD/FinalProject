package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import lilee.hd.jokedisplay.DisplayActivity;

public class JokeAsynkTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static final String TAG= "HAMMER DOWN";

    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if(myApiService == null){
            MyApi.Builder builder = new MyApi.Builder(new NetHttpTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://192.168.1.35/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        context = pairs[0].first;
        String name = pairs[0].second;
        try{
            return myApiService.sayHi(name).execute().getData();
        }catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Intent intent = new Intent(context, DisplayActivity.class);
        intent.putExtra(DisplayActivity.JOKE_EXTRA, result);
        context.startActivity(intent);
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        Log.v(TAG, "CAMARCHE");

    }

}
