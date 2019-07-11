package com.ddg.project.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ddg.project.R;
import com.ddg.project.db.Products;
import com.ddg.project.helper.BottomNavigationViewHelper;
import com.ddg.project.helper.SharedPreferenceManager;
import com.ddg.project.ui.fragment.HomeFragment;
import com.ddg.project.ui.fragment.MoreFragment;
import com.ddg.project.ui.fragment.ScanFragment;
import com.google.android.gms.vision.barcode.Barcode;
import com.jaeger.library.StatusBarUtil;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;


public class MainActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    private BottomNavigationView navigation;
    private static final int RequestPermissionCode = 1;
    private String scanResultContents;
    private BarcodeReader barcodeReader;
    private FrameLayout homeFlActivityFrameContainer;
    private ConstraintLayout scanContainer;
    private Products products;
    private List<String> productsBarCode;
    private SharedPreferenceManager sharedPreferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);

        StatusBarUtil.setTransparent(MainActivity.this);

        init();


    }


    //init method to set initial value
    private void init() {
        products = new Products();
        productsBarCode = products.getProductsBarCode();

        scanContainer = findViewById(R.id.scanContainer);
        homeFlActivityFrameContainer = findViewById(R.id.home_fl_activity_frame_container);
        navigation = findViewById(R.id.home_bn_home_bottom_nav);
        loadFragment(new HomeFragment());
        // disable shifting mode
//        BottomNavigationViewHelper.disableShiftMode(navigation);

        //set listener to BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    //check if app has camera permission or not
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
                    Toast.makeText(MainActivity.this, R.string.PermissionGranted, Toast.LENGTH_SHORT).show();

                    homeFlActivityFrameContainer.setVisibility(View.GONE);
                    scanContainer.setVisibility(View.VISIBLE);

//                    loadFragment(new ScanFragment(MainActivity.this));

                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, R.string.PermissionDenied, Toast.LENGTH_SHORT).show();
                }


                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }


 /*   public void scan() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();

    }*/

  /*  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {

            scanResultContents = scanResult.getContents();



            if (scanResultContents !=null){
                Toast.makeText(this, scanResultContents, Toast.LENGTH_SHORT).show();

            }else {

                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();


            }




        }

    }*/


    //SET FRAGMENT NAVIGATION
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.botm_nav_home:
                    loadFragment(new HomeFragment());
                    scanContainer.setVisibility(View.GONE);
                    homeFlActivityFrameContainer.setVisibility(View.VISIBLE);
                    return true;
                case R.id.botm_nav_scan:

                    if (isPermissionGranted()) {


                        homeFlActivityFrameContainer.setVisibility(View.GONE);
                        scanContainer.setVisibility(View.VISIBLE);

                        loadFragment(new ScanFragment(MainActivity.this));

                    }

                    return true;
                case R.id.botm_nav_more:
                    scanContainer.setVisibility(View.GONE);

                    homeFlActivityFrameContainer.setVisibility(View.VISIBLE);


                    loadFragment(new MoreFragment());
                    return true;

            }
            return false;
        }
    };


    // load fragment method to make transactions between fragments 
    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_fl_activity_frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        if (R.id.botm_nav_home != navigation.getSelectedItemId()) {
            //Return to Wall Fragment
            navigation.setSelectedItemId(R.id.botm_nav_home);
        } else {
            //Finish App
            finish();


//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(0);
        }
    }

    // success dialog when barcode is original
    private void openSuccessDialog(final Barcode barcode) {
        final Dialog dialog2 = new Dialog(MainActivity.this);

        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.dialog_success);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        TextView tv_dialog_productName = dialog2.findViewById(R.id.tv_dialog_productName);
        tv_dialog_productName.setText(barcode.displayValue);
        TextView tvDialogProductDetails = dialog2.findViewById(R.id.tv_dialog_productDetails);
        tvDialogProductDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.buycott.com/upc/" + barcode.displayValue));
                intent.putExtra(SearchManager.QUERY, barcode.displayValue);

                startActivity(intent);
            }
        });
        TextView tv_dialog_rescan = dialog2.findViewById(R.id.tv_dialog_rescan);
        tv_dialog_rescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
            }
        });
           /* ImageView ivAboutUsBack = dialog2.findViewById(R.id.iv_aboutUsBack);
            ivAboutUsBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog2.dismiss();
                }
            });*/
        dialog2.show();

    }

    // report dialog when barcode is fake

    private void openFakeDialog() {
        sharedPreferenceManager = new SharedPreferenceManager(MainActivity.this);
        final Dialog dialog2 = new Dialog(MainActivity.this);

        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.dialog_success);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView ivDialogSuccess = dialog2.findViewById(R.id.iv_dialog_success);
        ivDialogSuccess.setImageResource(R.drawable.ic_error);

        TextView tv_dialog_successDetails = dialog2.findViewById(R.id.tv_dialog_successDetails);
        tv_dialog_successDetails.setText("This product is a non-genuine and unlicensed product from the manufacturer, contributed to the disposal of Fake products by pressing Report product");


        TextView tv_dialog_productName = dialog2.findViewById(R.id.tv_dialog_productName);
        tv_dialog_productName.setVisibility(View.GONE);
        TextView tv_dialog_original = dialog2.findViewById(R.id.tv_dialog_original);
        tv_dialog_original.setText("Fake Product");


        TextView tvDialogProductDetails = dialog2.findViewById(R.id.tv_dialog_productDetails);
        tvDialogProductDetails.setText("Report Product");
        tvDialogProductDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceManager.cleanUserLocation();
                sharedPreferenceManager.cleanSignUpImages();
                startActivity(new Intent(MainActivity.this, ReportProductsActivity.class));
            }
        });
        TextView tv_dialog_rescan = dialog2.findViewById(R.id.tv_dialog_rescan);
        tv_dialog_rescan.setText("Cancel");
        tv_dialog_rescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
            }
        });

/*
            ivAboutUsBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog2.dismiss();
                }
            });
*/
        dialog2.show();

    }


    /**
     * override method
     * this method to get data after scan
     *
     * @param barcode
     */
    @Override
    public void onScanned(final Barcode barcode) {
        // play beep sound
        barcodeReader.playBeep();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String displayValue1 = barcode.displayValue;
//                Toast.makeText(MainActivity.this, displayValue1, Toast.LENGTH_LONG).show();

                if (productsBarCode.contains(displayValue1)) {
                    openSuccessDialog(barcode);


                } else {

                    openFakeDialog();


                }

            }
        });

//        barcodeReader.pauseScanning();

    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String s) {

    }

    @Override
    public void onCameraPermissionDenied() {
//        Toast.makeText(MainActivity.this, "Camera permission denied!", Toast.LENGTH_LONG).show();
    }
}
