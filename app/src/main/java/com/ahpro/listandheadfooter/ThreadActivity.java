package com.ahpro.listandheadfooter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ThreadActivity extends AppCompatActivity {

    TextView tv;
    TextView tv2;
    TextView tv3;
    ProgressBar pbar;
    ImageView iv;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        window = getWindow();
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        iv = findViewById(R.id.imageView2);
        pbar = findViewById(R.id.progressBar3);

        findViewById(R.id.button).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE); // 터치 막기
                tv.setVisibility(View.VISIBLE);
                iv.bringToFront();
                iv.setVisibility(View.VISIBLE);
                pbar.setVisibility(View.VISIBLE);
                pbar.bringToFront();
                String testItem = "asdkgjasldrgjqelrgj";
                new AnonymousThreadStart(new AnonymusThread(testItem) {
                    @Override
                    public void Callback(String test) {
                        for (int i = 1; i <= 3; i++) {
                            try {
                                Thread.sleep(1000);
                                final int finaltemp = i;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv2.setText(String.valueOf(finaltemp));
                                    }

                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE); // 터치 막기 해제
                                tv.setVisibility(View.INVISIBLE);
                                tv2.setText("!!!쓰레드 끝!!!");
                                iv.setVisibility(View.INVISIBLE);
                                pbar.setVisibility(View.INVISIBLE);
                            }

                        });
                    }
                });
            }
        });

        findViewById(R.id.button2).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                pbar.setVisibility(View.VISIBLE);
                String testItem = "asdkgjasldrgjqelrgj";
                new AnonymousThreadStart(new AnonymusThread(testItem) {
                    @Override
                    public void Callback(String test) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 1; i <= 7; i++) {
                                    try {
                                        Thread.sleep(1000);
                                        tv3.setText(String.valueOf(i));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                pbar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                });
            }
        });
    }
}
