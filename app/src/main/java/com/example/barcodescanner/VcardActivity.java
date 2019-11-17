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

public class VcardActivity extends AppCompatActivity {

    ImageView back;
    ImageView done;
    EditText textValue;
    String strUserName;
    String strMsg;
    Bitmap bitmap;
    EditText msgValue;
    String value;
    EditText faxValue;
    EditText emailValue;
    String faxStr;
    String strNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_vcard);

        back = findViewById(R.id.arrowVcard);
        done = findViewById(R.id.doneVcard);
        textValue = findViewById(R.id.nameCardValue);
        msgValue = findViewById(R.id.phoneCardValue);
        faxValue = findViewById(R.id.faxValue);
        emailValue = findViewById(R.id.mailValue);

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
                strNo = emailValue.getText().toString();
                faxStr = faxValue.getText().toString();

                if(strUserName.matches("") || strMsg.matches("") || strNo.matches("") || faxStr.matches("")) {
                    Toast.makeText(VcardActivity.this,"All item should be filled",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(strMsg.length() != 10){
                    msgValue.setError("Check Your Mobile No");
                    return;
                }
                else if(!isEmailValid(strNo)){

                    emailValue.setError("Check Your Email");
                    return;

                }
                else {
                    value = "Name:  " + strUserName + " PhoneNo: " + strMsg + " Fax: " + faxStr + " Email: " + strNo;
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(value, BarcodeFormat.QR_CODE, 200, 200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        bitmap = barcodeEncoder.createBitmap(bitMatrix);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }



                    Intent i = new Intent(VcardActivity.this, SplashWaitActivity.class);
                    i.putExtra("bitmap",bitmap);
                    startActivity(i);

                }}

        });


    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
