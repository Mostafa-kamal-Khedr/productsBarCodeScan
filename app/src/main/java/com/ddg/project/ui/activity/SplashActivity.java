package com.ddg.project.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ddg.project.R;
import com.ddg.project.helper.LanguageUtil;
import com.ddg.project.helper.SharedPreferenceManager;
import com.jaeger.library.StatusBarUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTransparent(SplashActivity.this);

        SharedPreferenceManager sharedPreferenceManager=new SharedPreferenceManager(SplashActivity.this);
        LanguageUtil languageUtil=new LanguageUtil(SplashActivity.this);
        languageUtil.setLocale(sharedPreferenceManager.loadLanguage());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();

            }
        }, 3000);

    }
}
