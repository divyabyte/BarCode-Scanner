package com.example.barcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class SmsActivity extends AppCompatActivity {

    ImageView back;
    ImageView done;
    EditText textValue;
    String strUserName;
    String strMsg;
    Bitmap bitmap;
    EditText msgValue;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sms);


        back = findViewById(R.id.arrowSms);
        done = findViewById(R.id.doneSms);
        textValue = findViewById(R.id.msgPhoneValue);
        msgValue = findViewById(R.id.msgValue);

        //onBackPressed
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strUserName = textValue.getText().toString();
                strMsg = msgValue.getText().toString();
                if(strUserName.matches("") || strMsg.matches("")) {
                    Toast.makeText(SmsActivity.this,"All item should be filled",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(strUserName.length() != 10){

                    textValue.setError("Check Your Mobile No");
                    return;

                }
                else {
                    value = "To:  " + strUserName + " Sms: " + strMsg;
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(value, BarcodeFormat.QR_CODE, 200, 200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        bitmap = barcodeEncoder.createBitmap(bitMatrix);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }



                    Intent i = new Intent(SmsActivity.this, SplashWaitActivity.class);
                    i.putExtra("bitmap",bitmap);
                    startActivity(i);

                }}

        });
    }


}
