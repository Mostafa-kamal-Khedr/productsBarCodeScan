package com.ddg.project.helper;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.ddg.project.R;


/**
 * Created by Mostafa khedr on 02,1,2018
 */


public class Validation {
    public static String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static String userNamePattern = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$";

    Context context;

    public Validation(Context context) {
        this.context = context;
    }












    //  this method to validate any editText for not null
    public boolean setEmptyValidation(EditText EditText) {

        String text = EditText.getText().toString();

        if (TextUtils.isEmpty(text)) {
            EditText.setError(context.getResources().getString(R.string.empity));
            EditText.requestFocus();
            return false;
        } else {

            return true;
        }


    }



    //  this method to validate any editText for not null
    public boolean setEmptyValidation(EditText EditText ,int emptyValue) {

        String text = EditText.getText().toString();

        if (TextUtils.isEmpty(text)) {
            EditText.setError(context.getResources().getString(emptyValue));
            EditText.requestFocus();
            return false;
        } else {

            return true;
        }


    }




}
