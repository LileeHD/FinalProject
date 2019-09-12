package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.udacity.gradle.builditbigger.AsyncResponseHandler;
import com.udacity.gradle.builditbigger.MyAsyncTask;
import com.udacity.gradle.builditbigger.R;

import lilee.hd.jokedisplay.DisplayActivity;

public class MainActivity extends AppCompatActivity implements AsyncResponseHandler {
    private static final String TAG = "Ultimate is ready";
    String mJoke;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        Button mButton = findViewById(R.id.joke_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    Log.d(TAG, "Current outlook: Ad is displayed");
                } else {
                    Log.d(TAG, "Current outlook: not working well");
                }
                tellJoke();
                Log.d(TAG, "Orisa Online.");
            }
        });
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
