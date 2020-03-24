package com.ahpro.listandheadfooter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class AccessActivity extends AppCompatActivity {

    ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        pBar = findViewById(R.id.progressBar);

        findViewById(R.id.btn_background).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                try {
                    ProgressTestTask task = new ProgressTestTask();
                    task.execute();
                    String result = task.get();
                    Log.d("롴_SL_결과", result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_on).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pBar.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.btn_off).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pBar.setVisibility(View.GONE);
            }
        });
    }

    private class ProgressTestTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            pBar.bringToFront();
            pBar.setVisibility(View.VISIBLE);
            pBar.setIndeterminate(true);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            // super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String resultData = null;

            try {
                String url = "https://hanadev-db.ahpro.co.kr:50000/b1s/v1/";
                String module = "Login";
                HttpsURLConnection conn = (HttpsURLConnection) new URL(url + module).openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                // Service Layer 로그인 요청
                Map<String, Object> params2 = new HashMap<>();
                params2.put("CompanyDB", "DEV_SAPB1H_G");
                params2.put("UserName", "manager");
                params2.put("Password", "$Ahpro8112");
                String json = convertMapToJson(params2);

                if (json != null) {
                    PrintWriter printWriter = new PrintWriter(conn.getOutputStream() );
                    printWriter.print(json);
                    printWriter.close();
                }

                conn.connect();

                if (conn.getResponseCode() < 400) {
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    String line = "";
                    StringBuffer response = new StringBuffer();

                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }

                    br.close();

                    resultData = response.toString();
                } else {
                    InputStream is = conn.getErrorStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    br.close();

                    resultData = response.toString();
                }

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultData;
        }

        @Override
        protected void onPostExecute(String s) {
            pBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            // super.onPostExecute(s);
        }
    }

    private String convertMapToJson(Map<String, Object> map) {
        if(map == null || map.size() == 0) return "";

        String res = "{";

        try {
            for(String key : map.keySet()) {
                res += "\"" + key + "\":\"" +  map.get(key).toString() + "\",";
            }

            res = res.substring(0, res.length() - 1);
            res += "}";
        } catch(Exception ex) {
            ex.printStackTrace();
            res = "";
        }

        return res;
    }
}
