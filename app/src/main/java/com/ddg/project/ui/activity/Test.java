package com.ddg.project.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.Toast;

import com.ddg.project.R;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class Test extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener{
    private BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);
        

    }

    @Override
    public void onScanned(final Barcode barcode) {
        // play beep sound
        barcodeReader.playBeep();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Test.this, barcode.displayValue, Toast.LENGTH_LONG).show();

            }
        });
           int x=0;
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
        Toast.makeText(Test.this, "Camera permission denied!", Toast.LENGTH_LONG).show();
    }      }
