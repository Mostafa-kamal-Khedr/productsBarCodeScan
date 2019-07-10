package com.ddg.project.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ddg.project.R;
import com.ddg.project.helper.HideKeyBoard;
import com.ddg.project.helper.Validation;

public class ContactUsActivity extends AppCompatActivity {

    private EditText contactUsSubject, contactUsEmail, contactUsMessage;
    private Validation validation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact_us);

        inflateAppBar();
        init();
    }
    public void onBackPress(View view) {
        super.onBackPressed();
    }

    // components initial value
    private void init() {
        validation = new Validation(ContactUsActivity.this);

        contactUsEmail = findViewById(R.id.edt_contactUs_email);
        contactUsSubject = findViewById(R.id.edt_contactUs_subject);
        contactUsMessage = findViewById(R.id.edt_contactUs_message);
    }

    // delete android app bar and create custom one
    private void inflateAppBar() {


        //      start of  inflate appBar layout
        Toolbar customToolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(customToolbar);
        TextView aboutUserAppBarTitle = findViewById(R.id.appbar_title);
        aboutUserAppBarTitle.setText(R.string.contactUs);
    }

    public void contactUsSend(View view) {

        validation();
    }

    // input validation
    public void validation() {


        if ((validation.setEmptyValidation(contactUsEmail)) &&
                (validation.setEmptyValidation(contactUsSubject)) &&
                (validation.setEmptyValidation(contactUsMessage))) {
            HideKeyBoard.hideSoftKeyboard(ContactUsActivity.this);
            sendEmail();
        }


    }

    // set email setting and open g-mail
    private void sendEmail() {

        Intent send = new Intent(Intent.ACTION_SENDTO);

        String uriText = "mailto:" + Uri.encode("ddg.project.kw2019@gmail.com") +
                "?subject=" + Uri.encode(contactUsSubject.getText().toString()) +
                "&body=" + Uri.encode(contactUsMessage.getText().toString());
        Uri uri = Uri.parse(uriText);

        send.setData(uri);
        startActivity(Intent.createChooser(send, "Send mail..."));
    }
}
