package com.tomoima.debot.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tomoima.debot.Debot;
import com.tomoima.debot.sample.R;

public class NextActivity extends AppCompatActivity {

    private final static String TAG = NextActivity.class.getSimpleName();
    private boolean menuVisibility = true;

    public static Intent createIntent(Context context){
        Intent intent = new Intent(context, NextActivity.class);
        intent.putExtra("testDataString", "test");
        intent.putExtra("testDataBoolean", true);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Debot.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_next, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Debot.setVisibility(menu, menuVisibility);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            menuVisibility = !menuVisibility;
            Log.v(TAG, "Menu is visible: " + menuVisibility);
            invalidateOptionsMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
