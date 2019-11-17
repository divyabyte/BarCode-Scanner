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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailActivity extends AppCompatActivity {

    ImageView back;
    ImageView done;
    EditText textValue;
    String strUserName;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_email);

        back = findViewById(R.id.arrowEmail);
        done = findViewById(R.id.doneEmail);
        textValue = findViewById(R.id.emailValue);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strUserName = textValue.getText().toString();
                if(strUserName.matches("")) {
                    textValue.setError("The item cannot be empty");
                    return;
                }
                else if(!isEmailValid(strUserName)){

                    textValue.setError("Check Your Email");
                    return;

                }
                else{

                    if (strUserName != null) {
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        try {
                            BitMatrix bitMatrix = multiFormatWriter.encode(strUserName, BarcodeFormat.QR_CODE, 200, 200);
                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                            bitmap = barcodeEncoder.createBitmap(bitMatrix);

                        } catch (WriterException e) {
                            e.printStackTrace();
                        }

                    }

                    Intent i = new Intent(EmailActivity.this, SplashWaitActivity.class);
                    i.putExtra("bitmap",bitmap);
                    startActivity(i);

                }
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
