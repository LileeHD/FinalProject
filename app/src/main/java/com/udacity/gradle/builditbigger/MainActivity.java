package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import lilee.hd.jokedisplay.DisplayActivity;


public class MainActivity extends AppCompatActivity implements AsyncResponseHandler {
    private static final String TAG = "I'm on fire !";
    String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = findViewById(R.id.joke_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
                Log.d(TAG, "Zenyatta is everywhere.");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically responseHandle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke() {
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.responseHandler = this;
        myAsyncTask.execute();
    }

    @Override
    public void responseHandle(String output) {
        mJoke = output;
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra(DisplayActivity.JOKE_EXTRA, output);
        this.startActivity(intent);
    }

}
