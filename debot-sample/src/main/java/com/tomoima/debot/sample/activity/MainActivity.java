package com.tomoima.debot.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.tomoima.debot.Debot;
import com.tomoima.debot.annotation.DebotAnnotation;
import com.tomoima.debot.sample.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Debot debot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.go_next_activity_btn).setOnClickListener(this);
        debot = Debot.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.go_next_activity_btn){
            startActivity(NextActivity.createIntent(this));
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            debot.showDebugMenu(this);
        }
        return super.onKeyUp(keyCode, event);
    }

    @DebotAnnotation("debugInput")
    public void debugInput() {
        EditText editText = (EditText) findViewById(R.id.input_1);
        editText.setText("This is sample!");
    }

    @DebotAnnotation("debugInput2")
    public void debugInput2() {
        EditText editText = (EditText) findViewById(R.id.input_2);
        editText.setText("This is sample 2!");
    }
}
