package com.ahpro.listandheadfooter;

public class AnonymusThread {
    public AnonymusThread(final String testitem) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Callback(testitem);
            }
        }).start();
    }

    public void Callback(String test) {

    }
}