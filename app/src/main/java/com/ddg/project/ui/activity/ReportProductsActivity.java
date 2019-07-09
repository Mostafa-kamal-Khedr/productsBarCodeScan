package com.ddg.project.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ddg.project.R;
import com.ddg.project.helper.SharedPreferenceManager;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportProductsActivity extends AppCompatActivity {
    private static final int RequestPermissionCode = 1;


    @BindView(R.id.iv_report)
    ImageView ivReport;
    @BindView(R.id.edt_productName)
    EditText edtProductName;
    @BindView(R.id.edt_productBarCode)
    EditText edtProductBarCode;
    @BindView(R.id.edt_productDescription)
    EditText edtProductDescription;
    @BindView(R.id.edt_storeName)
    EditText edtStoreName;
    @BindView(R.id.edt_location)
    EditText edtLocation;
    @BindView(R.id.edt_storeDescription)
    EditText edtStoreDescription;
    private ImageView ivReportImageOfProduct;
    private Uri resultUri;
    private SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_products);
        ButterKnife.bind(this);

        inflateAppBar();

        init();
    }

    private void init() {

        sharedPreferenceManager = new SharedPreferenceManager(ReportProductsActivity.this);
        if (sharedPreferenceManager.loadUserMapAddress() != null) {
            String userMapAddress = sharedPreferenceManager.loadUserMapAddress();
            edtLocation.setText(userMapAddress);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferenceManager.loadUserMapAddress() != null) {
            String userMapAddress = sharedPreferenceManager.loadUserMapAddress();
            edtLocation.setText(userMapAddress);

        }    if (sharedPreferenceManager.loadUserImage() != null) {
            String loadUserImage = sharedPreferenceManager.loadUserImage();
            Glide.with(ReportProductsActivity.this).load(loadUserImage).into(ivReportImageOfProduct);

        }

    }

    private void inflateAppBar() {
        //      start of  inflate appBar layout
        Toolbar customToolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(customToolbar);
        TextView aboutUserAppBarTitle = findViewById(R.id.appbar_title);
        aboutUserAppBarTitle.setText(R.string.reportProducts);

        ivReportImageOfProduct = findViewById(R.id.iv_report);
    }

    public void goToMap(View view) {
        startActivity(new Intent(ReportProductsActivity.this, MapsActivity.class));
    }

    public void send(View view) {
        sendMail();
    }

    private void sendMail() {
        File filelocation = null;
        if (resultUri.getPath() != null) {
            filelocation = new File(resultUri.getPath());
        }
        Uri path = Uri.fromFile(filelocation);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
        emailIntent.setType("vnd.android.cursor.dir/email");
        String to[] = {"ddg.project.kw2019@gmail.com"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        if (path != null) {
// the attachment
            emailIntent.putExtra(Intent.EXTRA_STREAM, path);

        }
// the mail subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Report product");// the mail subject


        String uriText = "&body=" + Uri.encode(
                edtProductName.getText().toString() + "\n" +
                        edtProductBarCode.getText().toString() + "\n" +
                        edtProductDescription.getText().toString() + "\n" +
                        edtLocation.getText().toString() + "\n" +
                        edtStoreName.getText().toString() + "\n" +
                        edtStoreDescription.getText().toString()


        );
        Uri uri = Uri.parse(uriText);

        emailIntent.setData(uri);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    // open phone camera
    public void openCamera(View view) {

        if (isPermissionGranted()) {

            onImageClicked();
        }


    }

    //check user accept permission or not
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

    // result after permission

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


    // open user camera to pic image
    public void onImageClicked() {


        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);


    }


    // result after camera shooting
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                ivReportImageOfProduct.setVisibility(View.VISIBLE);
                Glide.with(ReportProductsActivity.this).load(resultUri).into(ivReportImageOfProduct);
                                          sharedPreferenceManager.changeUserImage(resultUri.toString(),"notNull");
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Error in upload Image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
