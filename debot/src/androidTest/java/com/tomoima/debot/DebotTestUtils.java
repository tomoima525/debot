package com.tomoima.debot;


import android.app.Activity;
import android.support.v4.app.FragmentManager;

import java.util.concurrent.CountDownLatch;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

class DebotTestUtils {

    static TestDebot getTestDebot(Activity activity){
        TestDebot debot = TestDebot.getInstance();
        getInstrumentation().waitForIdleSync();
        try {
            waitForFragmentTransaction(activity);
            return debot;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new TestDebot();
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

    static class TestDebot extends DebotDialog {

        public static TestDebot getInstance() {
            return new TestDebot();
        }

        @Override
        public void show(FragmentManager manager, String tag) {

        }

    }

}
