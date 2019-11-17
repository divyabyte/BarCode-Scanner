package com.example.barcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScanResultActivity extends AppCompatActivity {

    public static TextView textView;
    String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        textView = findViewById(R.id.scan_result);

        Bundle extras = getIntent().getExtras();
        newString= extras.getString("scanResult");
        textView.setText(newString);


    }
}
