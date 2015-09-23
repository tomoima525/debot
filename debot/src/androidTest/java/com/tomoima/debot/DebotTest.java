package com.tomoima.debot;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.view.MenuItem;

import com.tomoima.debot.strategy.DebotStrategy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DebotTest {

    private Activity activity;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Before
    public void setUp(){
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activity = new Activity();
            }
        });

        ArrayList<DebotStrategy> dummyStrategy = new ArrayList<>();
        dummyStrategy.add(new DebotStrategy() {
            @Override
            public void startAction(@NonNull Activity activity) {

            }
        });

        Debot.initialize(dummyStrategy);
    }

    @Test
    public void test1OnOptionsItemSelected_ThrowsExceptionIfActivityIsNull() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Activity is not set");

        MenuItem menuItem;
        menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(0);
        when(menuItem.getGroupId()).thenReturn(Integer.MAX_VALUE);
        Debot.onOptionsItemSelected(menuItem);

    }

    @Test
    public void test2OnPause_ThrowsExceptionIfActivityIsNull(){
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Can't find activity. Check if Debot.onResume() is called at onResume()");

        Debot.onPause(activity);
    }

    @Test
    public void test3OnResume(){
        Debot.onResume(activity);
    }

    @Test
    public void test4OnOptionsItemSelected_returnFalseIfGroupIdDoesntMatch(){
        MenuItem menuItem;
        menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(0);
        when(menuItem.getGroupId()).thenReturn(1);
        assertFalse(Debot.onOptionsItemSelected(menuItem));
    }

    @Test
    public void test5OnOptionsItemSelected_returnTrueIfGroupIdDoesntMatch(){
        MenuItem menuItem;
        menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(0);
        when(menuItem.getGroupId()).thenReturn(Integer.MAX_VALUE);
        assertTrue(Debot.onOptionsItemSelected(menuItem));
    }

}
