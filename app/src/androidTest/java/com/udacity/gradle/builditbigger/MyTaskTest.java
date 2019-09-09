package com.udacity.gradle.builditbigger;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MyTaskTest {
    @Test
    public void testMyAsyncTask() throws Exception {

        MyAsyncTask asyncTask = new MyAsyncTask();

        AsyncResponseHandler handler = new AsyncResponseHandler() {

            @Override
            public void responseHandle(String output) {
                assertEquals("Pouet", "Groot");
//                assertNotNull(output);
                Log.d("Here's an Overwatch dad's joke: ", output);
            }
        };
        asyncTask.setResponseHandler(handler);
        asyncTask.execute();
    }
}
