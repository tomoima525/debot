package com.tomoima.debot;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Menu;
import android.view.MenuItem;

import com.tomoima.debot.strategy.DebotStrategy;
import com.tomoima.debot.testUtils.DebotTestUtils;
import com.tomoima.debot.testUtils.TestActivity;
import com.tomoima.debot.testUtils.TestMenuItem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class DebotTest {

    @Rule
    public final ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);
    private Activity activity;
    private Debot debot;

    @Before
    public void setUp(){

        ArrayList<DebotStrategy> dummyStrategy = new ArrayList<>();
        dummyStrategy.add(new DebotStrategy() {
            @Override
            public void startAction(@NonNull Activity activity) {

            }
        });

        new DebotStrategies().initialize(dummyStrategy);
        activity = activityTestRule.getActivity();
        debot = DebotTestUtils.getDebotMock(activity);
    }

    @Test
    public void testSetVisibility() {
        MenuItem menuItem = new TestMenuItem();
        Menu menu = mock(Menu.class);
        when(menu.size()).thenReturn(1);
        when(menu.getItem(0)).thenReturn(menuItem);
        Debot.setVisibility(menu,true);
        assertThat(menuItem.isVisible(),is(true));
    }

    @Test
    public void testOnOptionsItemSelected_returnFalseIfGroupIdDoesNotMatch() {

        MenuItem menuItem;
        menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(0);
        when(menuItem.getGroupId()).thenReturn(1);
        assertThat(debot.onOptionsItemSelected(menuItem),is(false));
    }

    @Test
    public void testOnOptionsItemSelected_returnTrueIfGroupIdDoesMatch(){
        MenuItem menuItem;
        menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(0);
        when(menuItem.getGroupId()).thenReturn(Integer.MAX_VALUE);
        assertThat(debot.onOptionsItemSelected(menuItem),is(true));
    }

}
