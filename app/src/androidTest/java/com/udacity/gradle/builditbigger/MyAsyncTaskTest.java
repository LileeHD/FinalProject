package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MyAsyncTaskTest {

    @Test
    public void testAsyncLoadsJoke()throws Exception{
        AsyncTask<Void, Void, String>asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return "Jokin";
            }

            @Override
            protected void onPostExecute(String string) {
                assertNotNull(string);
                assertTrue(string.contains("Jokin"));
            }
        };
        asyncTask.execute();
    }
}
