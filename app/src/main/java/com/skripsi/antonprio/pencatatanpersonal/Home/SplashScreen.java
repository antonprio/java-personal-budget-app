package com.skripsi.antonprio.pencatatanpersonal.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.skripsi.antonprio.pencatatanpersonal.R;
import android.os.Handler;

/**
 * Created by Anton Prio on 8/22/2016.
 */
public class SplashScreen extends Activity {
    private static int TIMEOUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Home.class);
                startActivity(i);
                finish();
            }
        }, TIMEOUT);
    }
}
