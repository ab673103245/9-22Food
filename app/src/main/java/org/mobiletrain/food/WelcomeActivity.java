package org.mobiletrain.food;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import org.mobiletrain.food.util.AppConfig;

public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sp = getSharedPreferences(AppConfig.USERINFO, MODE_PRIVATE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                if (isRunning) {
                    if (sp.getBoolean("isFirstLogin", true)) {
                        startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    }
                    WelcomeActivity.this.finish();
                }
            }
        }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }
}
