package com.ahpro.listandheadfooter;

import android.os.AsyncTask;

public class LoginProcess extends AsyncTask<Void, Void, Void> {

    String mType;
    String mConnection;
    String mUrl;
    String mModule;
    String mJson;

    LoginProcess(String type, String connection, String url, String module, String json) {
        mType = type;
        mConnection = connection;
        mUrl = url;
        mModule = module;
        mJson = json;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
