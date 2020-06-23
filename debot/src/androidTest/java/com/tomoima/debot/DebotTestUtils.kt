package com.tomoima.debot


import android.app.Activity
import android.app.Dialog
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.mockito.Mockito.*
import java.util.concurrent.CountDownLatch

internal object DebotTestUtils {

    fun getTestDebot(activity: Activity): TestDebot {
        val debot = TestDebot.instance
        getInstrumentation().waitForIdleSync()
        try {
            waitForFragmentTransaction(activity)
            return debot
        } catch (e: InterruptedException) {
            e.printStackTrace()
            return TestDebot()
        }

    }

    @Throws(InterruptedException::class)
    private fun waitForFragmentTransaction(activity: Activity) {
        val latch = CountDownLatch(1)
        activity.runOnUiThread {
            activity.fragmentManager.executePendingTransactions()
            latch.countDown()
        }
        latch.await()
    }

    class TestDebot : DebotDialog() {

        companion object {
            val instance: TestDebot
                get() = TestDebot()
        }

        override fun getDialog(): Dialog {
            return mock(Dialog::class.java);
        }

    }

}
