package com.udacity.gradle.builditbigger;

import android.text.TextUtils;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MyAsyncTaskTest {
    private String result = null;

    @Test
    public void LoadTest() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        MyAsyncTask asyncTask = new MyAsyncTask();
        AsyncResponseHandler handler = new AsyncResponseHandler() {
            @Override
            public void responseHandle(String output) {
                result = output;
                countDownLatch.countDown();
//                assertEquals("Baptiste", "Ana");
                assertNotNull(output);
                Log.d("OVERWATCH", output);
            }
        };
        asyncTask.setResponseHandler(handler);
        asyncTask.execute();

        try {
            countDownLatch.await();
            assertNotNull("No joke here", result);
            assertFalse("Empty string", TextUtils.isEmpty(result));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
