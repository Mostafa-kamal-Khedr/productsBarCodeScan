package com.ddg.project.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ddg.project.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class ReportProductsActivity extends AppCompatActivity {
    private static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_products);

        inflateAppBar();
    }

    private void inflateAppBar() {
        //      start of  inflate appBar layout
        Toolbar customToolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(customToolbar);
        TextView aboutUserAppBarTitle = findViewById(R.id.appbar_title);
        aboutUserAppBarTitle.setText(R.string.reportProducts);
    }

    public void goToMap(View view) {
        startActivity(new Intent(ReportProductsActivity.this, MapsActivity.class));
    }

    public void send(View view) {
        onBackPressed();
    }


    public void openCamer(View view) {

        if (isPermissionGranted()) {

            onImageClicked();
        }


    }


    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                return true;
            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation

            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Toast.makeText(ReportProductsActivity.this, R.string.PermissionGranted, Toast.LENGTH_SHORT).show();
                    onImageClicked();

                } else {
                    // Permission Denied
                    Toast.makeText(ReportProductsActivity.this, R.string.PermissionDenied, Toast.LENGTH_SHORT).show();
                }


                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }

    public void onImageClicked() {


        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {




            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Error in upload Image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
