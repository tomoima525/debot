package com.tomoima.debot.testUtils;


import android.app.Activity;

import com.tomoima.debot.Debot;

import java.util.concurrent.CountDownLatch;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class DebotTestUtils {

    public static Debot getDebotMock(Activity activity){
        Debot debot = Debot.getInstance(activity);
        getInstrumentation().waitForIdleSync();
        try {
            waitForFragmentTransaction(activity);
            return debot;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new Debot();
        }
    }

    private static void waitForFragmentTransaction(final Activity activity) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.getFragmentManager().executePendingTransactions();
                latch.countDown();
            }
        });
        latch.await();
    }
}
