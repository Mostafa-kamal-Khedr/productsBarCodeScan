package com.ddg.project.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ddg.project.R;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

public class ScannerActivity extends AppCompatActivity {
    private DecoratedBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_layout);

        barcodeView = (DecoratedBarcodeView) findViewById(R.id.view_scanner);
        barcodeView.decodeContinuous(callback);

    }


    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            //Process your scan result here
            String resultString = result.getText();

            Toast.makeText(ScannerActivity.this, resultString, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };
}
