package com.tomoima.debot


import android.app.Activity
import android.app.Dialog
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import com.nhaarman.mockito_kotlin.whenever
import com.tomoima.debot.strategy.DebotStrategy
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.util.*

@RunWith(AndroidJUnit4::class)
class DebotDialogTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(TestActivity::class.java)
    lateinit private var activity: Activity
    lateinit private var debot: DebotTestUtils.TestDebot // TestDebot is a Debot that has minimum
    // function

    val fragmentManager: FragmentManager = mock(FragmentManager::class.java)

    @Before
    fun setUp() {
        val dummyStrategy = ArrayList<DebotStrategy>()
        dummyStrategy.add(object : DebotStrategy() {
            override fun startAction(activity: Activity) {

            }
        })

        DebotStrategies.initialize(dummyStrategy)
        activity = activityTestRule.activity
        debot = DebotTestUtils.getTestDebot(activity)
    }

    @Test
    fun showDebugMenu_show_when_no_menu_is_shown() {
        val activity = mock(AppCompatActivity::class.java)
        whenever(activity.supportFragmentManager).thenReturn(fragmentManager)
        whenever(fragmentManager.findFragmentByTag("com.tomoima.debot.Debot")).thenReturn(null)
        whenever(fragmentManager.executePendingTransactions()).thenReturn(true)
        debot.showDebugMenu(activity)

        verify(activity).supportFragmentManager
        verify(fragmentManager).executePendingTransactions()
    }

    @Test
    fun showDebugMenu_does_not_show_menu_when_exists() {
        val activity = mock(AppCompatActivity::class.java)
        val fragment = mock(Fragment::class.java)
        whenever(activity.supportFragmentManager).thenReturn(fragmentManager)
        whenever(fragmentManager.findFragmentByTag("com.tomoima.debot.Debot")).thenReturn(fragment)

        debot.showDebugMenu(activity)

        verify(activity).supportFragmentManager
        verify(fragmentManager, never()).executePendingTransactions()
    }

    @Test
    fun dismissDebugMenu() {
        val mockDebot = mock(DebotDialog::class.java)
        whenever(fragmentManager.findFragmentByTag("com.tomoima.debot.Debot")).thenReturn(mockDebot)
        debot.dismissDebugMenu(fragmentManager)
        verify(fragmentManager).findFragmentByTag("com.tomoima.debot.Debot")
        verify(mockDebot).onDismiss(ArgumentMatchers.any(Dialog::class.java))
    }

}
