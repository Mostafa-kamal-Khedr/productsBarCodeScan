package com.ddg.project.ui.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ddg.project.R;
import com.ddg.project.helper.LanguageUtil;
import com.ddg.project.helper.SharedPreferenceManager;
import com.ddg.project.ui.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment implements View.OnClickListener {


    private SharedPreferenceManager sharedPreferenceManager;
    private LanguageUtil languageUtil;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_more, container, false);
        sharedPreferenceManager = new SharedPreferenceManager(getActivity());
        languageUtil = new LanguageUtil(getActivity());
        init(inflate);


        return inflate;
    }

    private void init(View inflate) {
        TextView tvAboutUs = inflate.findViewById(R.id.tv_aboutUs);
        TextView tvChangeLanguage = inflate.findViewById(R.id.tv_changeLanguage);

        TextView tvContactUs = inflate.findViewById(R.id.tv_contactUs);
        TextView tvRateApp = inflate.findViewById(R.id.tv_rateApp);
        TextView tvShareApp = inflate.findViewById(R.id.tv_shareApp);

        tvAboutUs.setOnClickListener(this);
        tvChangeLanguage.setOnClickListener(this);
        tvContactUs.setOnClickListener(this);
        tvRateApp.setOnClickListener(this);
        tvShareApp.setOnClickListener(this);

    }

    void showLanguageDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_language);
        dialog.setCancelable(true);
        //  initialize dialog components
        LinearLayout dialogParent = dialog.findViewById(R.id.layout_dialog_parent);
        Button english = dialog.findViewById(R.id.btn_english);
        Button arabic = dialog.findViewById(R.id.btn_arabic);
        dialog.show();
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceManager.saveLanguage("en");
                languageUtil.setLocale("en");
                startActivity(new Intent(getActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });
        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceManager.saveLanguage("ar");
                languageUtil.setLocale("ar");
                startActivity(new Intent(getActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });


    }

    @Override
    public void onClick(View view) {

        int id = view.getId();


        switch (id) {
            case R.id.tv_changeLanguage:
                showLanguageDialog();
                break;
            case R.id.tv_aboutUs:
                setAboutUsDialog();
                break;
            case R.id.tv_contactUs:
                break;
            case R.id.tv_rateApp:
                break;
            case R.id.tv_shareApp:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;


        }
    }

    private void setAboutUsDialog() {

        final Dialog dialog2 = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        dialog2.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        dialog2.setContentView(R.layout.about_us);
//        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView ivAboutUsBack = dialog2.findViewById(R.id.iv_aboutUsBack);
        ivAboutUsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();

    }


}
