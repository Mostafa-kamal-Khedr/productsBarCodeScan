package com.ddg.project.helper;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by sas on 10/04/2018.
 */

public class LanguageUtil {
    Context context;

    public LanguageUtil(Context context) {
        this.context = context;
    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }


}
