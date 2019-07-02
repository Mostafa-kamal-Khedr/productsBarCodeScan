package com.ddg.project.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.ddg.project.R;
import com.ddg.project.helper.BottomNavigationViewHelper;
import com.ddg.project.ui.fragment.HomeFragment;
import com.ddg.project.ui.fragment.MoreFragment;
import com.ddg.project.ui.fragment.ScanFragment;
import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private static final int RequestPermissionCode = 1;
    private String scanResultContents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StatusBarUtil.setTransparent(MainActivity.this);

        init();

    }

    //init method to set initial value
    private void init() {

        navigation = findViewById(R.id.home_bn_home_bottom_nav);
        loadFragment(new HomeFragment());
        // disable shifting mode
        BottomNavigationViewHelper.disableShiftMode(navigation);

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


                    loadFragment(new ScanFragment());

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
                    return true;
                case R.id.botm_nav_scan:


                    loadFragment(new ScanFragment());

                    return true;
                case R.id.botm_nav_more:

              /*      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        String cameraId = null; // Usually back camera is at 0 position.
                        try {
                            cameraId = camManager.getCameraIdList()[0];
                            camManager.setTorchMode(cameraId, true);   //Turn ON
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }*/

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
}
