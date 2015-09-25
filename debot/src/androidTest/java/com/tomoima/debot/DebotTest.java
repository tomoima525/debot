package com.tomoima.debot;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.view.MenuItem;

import com.tomoima.debot.strategy.DebotStrategy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    public void testOnPause_ThrowsExceptionIfActivityIsNull(){
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Can't find activity. Check if Debot.onResume() is called at onResume()");

        setWeakReferenceActivity(null);

        Debot.onPause(activity);
    }

    @Test
    public void testOnResume(){
        Debot.onResume(activity);
    }

    @Test
    public void testOnOptionsItemSelected_ThrowsExceptionIfActivityIsNull() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Activity is not set");

        setWeakReferenceActivity(null);

        MenuItem menuItem;
        menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(0);
        when(menuItem.getGroupId()).thenReturn(Integer.MAX_VALUE);
        Debot.onOptionsItemSelected(menuItem);

    }

    @Test
    public void testOnOptionsItemSelected_returnFalseIfGroupIdDoesntMatch() {

        setWeakReferenceActivity(activity);
        MenuItem menuItem;
        menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(0);
        when(menuItem.getGroupId()).thenReturn(1);
        assertFalse(Debot.onOptionsItemSelected(menuItem));
    }

    @Test
    public void testOnOptionsItemSelected_returnTrueIfGroupIdDoesMatch(){
        setWeakReferenceActivity(activity);
        MenuItem menuItem;
        menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(0);
        when(menuItem.getGroupId()).thenReturn(Integer.MAX_VALUE);
        assertTrue(Debot.onOptionsItemSelected(menuItem));
    }

    private void setWeakReferenceActivity(@Nullable Activity activity){
        try {
            Constructor<Debot> debotConstructor = Debot.class.getDeclaredConstructor(null);
            debotConstructor.setAccessible(true);
            Debot debot = debotConstructor.newInstance(null);
            Field f = debot.getClass().getDeclaredField("weakRefActivity");
            f.setAccessible(true);
            f.set(null, activity != null ? new WeakReference<>(activity): null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
