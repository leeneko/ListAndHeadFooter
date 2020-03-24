package com.ahpro.listandheadfooter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Thread2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread2);

        findViewById(R.id.button4).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                new LoginProcess("", "", "", "", "");
            }
        });
    }
}
