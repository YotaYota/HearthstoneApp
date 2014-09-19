package com.yotayota.hearthstoneapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Welcome screen. Waits for 1 seconds, then starts MainActivity.
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getActionBar().hide();

        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 1000) {
                        sleep(100);
                        waited += 100;
                    }
                } catch (InterruptedException e) {
                } finally {
                    finish();
                    Intent intent = new Intent();
                    intent.setClassName("com.yotayota.hearthstoneapp",
                            "com.yotayota.hearthstoneapp.MainActivity");
                    startActivity(intent);
                }
            }
        };
        splashThread.start();
    }
}
