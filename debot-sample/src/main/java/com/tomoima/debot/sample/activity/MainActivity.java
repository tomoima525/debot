package com.tomoima.debot.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tomoima.debot.annotation.DebotAnnotation;
import com.tomoima.debot.sample.R;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.go_next_activity_btn).setOnClickListener(this);
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

    @DebotAnnotation("debugInput2")
    public void debugInput2() {
        EditText editText = (EditText) findViewById(R.id.input_2);
        editText.setText("This is sample 2!");
    }
}
