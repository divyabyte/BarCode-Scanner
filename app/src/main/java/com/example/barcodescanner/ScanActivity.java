package com.example.barcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static java.lang.Boolean.TRUE;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    ImageView image;
    ImageView flash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        scannerView = new ZXingScannerView(this);
        setContentView(R.layout.activity_scan);

        scannerView= (ZXingScannerView) findViewById(R.id.m_scan);

        image = findViewById(R.id.image);
        flash = findViewById(R.id.btn_flash);


        //handle flash
        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                    String cameraId = null; // Usually back camera is at 0 position.
                    try {
                        cameraId = camManager.getCameraIdList()[0];
                        camManager.setTorchMode(cameraId, false);


                        scannerView.setFlash(true);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

               if(scannerView.getFlash() == TRUE){
                   scannerView.setFlash(false);
               }
               else{
                   scannerView.setFlash(true);
               }
            }
        });


    }

    @Override
    public void handleResult(Result rawResult) {
        Intent i = new Intent(ScanActivity.this,ScanResultActivity.class);
        i.putExtra("scanResult",rawResult.getText());
        startActivity(i);
    }


    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }




}
