package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.udacity.gradle.builditbigger.AsyncResponseHandler;
import com.udacity.gradle.builditbigger.MyAsyncTask;
import com.udacity.gradle.builditbigger.R;

import java.util.Objects;

import lilee.hd.jokedisplay.DisplayActivity;

public class MainActivityFragment extends Fragment implements AsyncResponseHandler {
    private ProgressBar mProBar;

    private static final String TAG = "Ultimate is ready";

    private InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(Objects.requireNonNull(getActivity()));
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        mProBar = root.findViewById(R.id.progress_bar);
        Button mButton = root.findViewById(R.id.joke_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    tellJoke();
                    Log.d(TAG, "Current outlook: Ad is displayed");
                } else {
                    Toast.makeText(getContext(), getString(R.string.no_interstitial), Toast.LENGTH_LONG).show();
                }
                Log.d(TAG, "Orisa Online.");
            }
        });
        return root;
    }

    private void tellJoke() {
        mProBar.setVisibility(View.VISIBLE);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.responseHandler = this;
        myAsyncTask.execute();
    }

    @Override
    public void onSuccess(String output) {
        if(output!= null){
            Intent intent = new Intent(getActivity(), DisplayActivity.class);
            intent.putExtra(DisplayActivity.JOKE_EXTRA, output);
            mProBar.setVisibility(View.GONE);
            this.startActivity(intent);
            Toast.makeText(getContext(), "SUCCESS: Here's a joke", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailed(Exception exception) {
        Toast.makeText(getContext(), "ERROR: "+exception, Toast.LENGTH_LONG).show();
    }

}
