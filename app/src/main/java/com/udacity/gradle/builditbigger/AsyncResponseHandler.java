package com.udacity.gradle.builditbigger;

public interface AsyncResponseHandler {
    void onSuccess(String output);
    void onFailed(Exception exception);
}
