package com.tomoima.debot.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.tomoima.debot.Debot;
import com.tomoima.debot.annotation.DebotAnnotation;
import com.tomoima.debot.sample.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.go_next_activity_btn).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Debot.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Debot.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Debot.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Debot.onPause(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.go_next_activity_btn){
            startActivity(NextActivity.createIntent(this));
        }
    }

    @DebotAnnotation("debugInput")
    public void debugInput() {
        EditText editText = (EditText) findViewById(R.id.input_1);
        editText.setText("This is sample!");
    }
}
