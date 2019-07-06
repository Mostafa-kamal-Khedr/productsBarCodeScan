package com.ddg.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ddg.project.R;
import com.ddg.project.helper.LanguageUtil;
import com.ddg.project.helper.SharedPreferenceManager;
import com.jaeger.library.StatusBarUtil;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTransparent(SplashActivity.this);

        setAppLanguage();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MapsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();

            }
        }, 3000);

    }

    private void setAppLanguage() {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(SplashActivity.this);
        LanguageUtil languageUtil = new LanguageUtil(SplashActivity.this);
        if (sharedPreferenceManager.loadLanguage() != null) {

            languageUtil.setLocale(sharedPreferenceManager.loadLanguage());

        } else {
            String phoneLanguage = Locale.getDefault().getLanguage();

            languageUtil.setLocale(phoneLanguage);

        }

    }
}
