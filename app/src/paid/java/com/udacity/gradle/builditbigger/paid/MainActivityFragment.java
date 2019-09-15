package com.udacity.gradle.builditbigger.paid;

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

import com.udacity.gradle.builditbigger.AsyncResponseHandler;
import com.udacity.gradle.builditbigger.MyAsyncTask;
import com.udacity.gradle.builditbigger.R;

import java.util.Objects;

import lilee.hd.jokedisplay.DisplayActivity;

public class MainActivityFragment extends Fragment implements AsyncResponseHandler {
    private ProgressBar mProBar;

    private static final String TAG = "Ultimate is ready";

    private String mJoke;

    public MainActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProBar = root.findViewById(R.id.progress_bar);
        Button mButton = root.findViewById(R.id.joke_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
                Log.d(TAG, "Sygma presents.");
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
        }
    }

    @Override
    public void onFailed(Exception exception) {
        Toast.makeText(getContext(), "ERROR: "+exception, Toast.LENGTH_LONG).show();
    }

}
