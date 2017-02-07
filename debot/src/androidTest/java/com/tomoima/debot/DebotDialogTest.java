package com.tomoima.debot;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.tomoima.debot.strategy.DebotStrategy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class DebotDialogTest {

    @Rule
    public final ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);
    private Activity activity;
    private DebotTestUtils.TestDebot debot; // TestDebot is a Debot that has minimum function

    FragmentManager fragmentManager;

    @Before
    public void setUp(){
        fragmentManager = mock(FragmentManager.class);

        ArrayList<DebotStrategy> dummyStrategy = new ArrayList<>();
        dummyStrategy.add(new DebotStrategy() {
            @Override
            public void startAction(@NonNull Activity activity) {

            }
        });

        new DebotStrategies().initialize(dummyStrategy);
        activity = activityTestRule.getActivity();
        debot = DebotTestUtils.getTestDebot(activity);
    }

    @Test
    public void showDebugMenu_show_when_no_menu_is_shown() {
        AppCompatActivity activity = mock(AppCompatActivity.class);
        when(activity.getSupportFragmentManager()).thenReturn(fragmentManager);
        when(fragmentManager.findFragmentByTag("com.tomoima.debot.Debot")).thenReturn(null);
        when(fragmentManager.executePendingTransactions()).thenReturn(true);
        debot.showDebugMenu(activity);

        verify(activity, times(1)).getSupportFragmentManager();
        verify(fragmentManager, times(1)).executePendingTransactions();
    }

    @Test
    public void showDebugMenu_does_not_show_menu_when_exists() {
        AppCompatActivity activity = mock(AppCompatActivity.class);
        Fragment fragment = mock(Fragment.class);
        when(activity.getSupportFragmentManager()).thenReturn(fragmentManager);
        when(fragmentManager.findFragmentByTag("com.tomoima.debot.Debot")).thenReturn(fragment);

        debot.showDebugMenu(activity);

        verify(activity, times(1)).getSupportFragmentManager();
        verify(fragmentManager, times(0)).executePendingTransactions();
    }

    @Test
    public void dismissDebugMenu() {
        DebotDialog mockDebot = mock(DebotDialog.class);
        when(fragmentManager.findFragmentByTag("com.tomoima.debot.Debot")).thenReturn(mockDebot);
        debot.dismissDebugMenu(fragmentManager);
        verify(fragmentManager, times(1)).findFragmentByTag("com.tomoima.debot.Debot");
        verify(mockDebot, times(1)).onDismiss(any(DialogInterface.class));
    }

}
