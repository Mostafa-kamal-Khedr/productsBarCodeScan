package com.ddg.project.ui.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ddg.project.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment implements BarcodeReader.BarcodeReaderListener {
    private String scanResultContents;
    private Button btnScan;

    private static final int RequestPermissionCode = 1;
    private CheckBox rbScanFlashLight;
    private CameraManager camManager;
    private String cameraId;
    private BarcodeReader barcodeReader;
    Activity activity;

    @SuppressLint("ValidFragment")
    public ScanFragment(Activity activity) {
        this.activity=activity;
        // Required empty public constructor
    }  public ScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_scan, container, false);

        barcodeReader = (BarcodeReader) getActivity().getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);
//        IntentIntegrator.forSupportFragment(this).initiateScan();

        btnScan = inflate.findViewById(R.id.btn_scan);
        rbScanFlashLight = inflate.findViewById(R.id.rb_scan_flashLight);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setFlash(rbScanFlashLight);
        }


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPermissionGranted()) {
                    scan();

                }

            }
        });
        return inflate;


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setFlash(CheckBox rbScanFlashLight) {
        rbScanFlashLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        CameraManager camManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
                        String cameraId = null; // Usually back camera is at 0 position.
                        try {
                            cameraId = camManager.getCameraIdList()[0];
                            camManager.setTorchMode(cameraId, true);   //Turn ON
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        try {
                            String cameraId;
                            camManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
                            if (camManager != null) {
                                cameraId = camManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
                                camManager.setTorchMode(cameraId, false);
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Camera mCamera = Camera.open();
                        Camera.Parameters parameters = mCamera.getParameters();
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        mCamera.setParameters(parameters);
                        mCamera.stopPreview();
                    }

                }


            }
        });

    }


    public void scan() {

      /*  IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.initiateScan();*/


    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {

            scanResultContents = scanResult.getContents();


            if (scanResultContents != null) {
//                Toast.makeText(getActivity(), scanResultContents, Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(getActivity(), "Result Not Found", Toast.LENGTH_LONG).show();


            }


        }

    }


    //check if app has camera permission or not
    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {


                return true;
            } else {


                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
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
                    Toast.makeText(getActivity(), R.string.PermissionGranted, Toast.LENGTH_SHORT).show();


                    scan();

                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), R.string.PermissionDenied, Toast.LENGTH_SHORT).show();
                }


                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }

/*

    @Override
    public void onScanned(Barcode barcode) {

        final String displayValue = barcode.displayValue;
        // play beep sound
        barcodeReader.playBeep();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), displayValue, Toast.LENGTH_LONG).show();

            }
        });
        int x = 0;
    }
*/

    @Override
    public void onScanned(Barcode barcode) {

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
        Toast.makeText(getActivity(), "Camera permission denied!", Toast.LENGTH_LONG).show();
    }


}

