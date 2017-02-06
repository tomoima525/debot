package com.tomoima.debot.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.tomoima.debot.Debot;
import com.tomoima.debot.sample.R;

public class NextActivity extends AppCompatActivity {

    private final static String TAG = NextActivity.class.getSimpleName();
    private boolean menuVisibility = true;
    Debot debot;

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
        debot = Debot.getInstance();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            debot.showDebugMenu(this);
        }
        return super.onKeyUp(keyCode, event);
    }
}
