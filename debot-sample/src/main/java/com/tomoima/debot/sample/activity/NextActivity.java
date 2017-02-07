package com.tomoima.debot.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tomoima.debot.sample.R;

public class NextActivity extends BaseActivity {

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
    }
}
