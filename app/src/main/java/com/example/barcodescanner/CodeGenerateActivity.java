package com.example.barcodescanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class CodeGenerateActivity extends AppCompatActivity {

    EditText text;
    Button get_Button;
    ImageView imageView;
    String text2QR;
    ImageView imageBack;
    ImageView imageSave;
    ImageView imageShare;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_generate);
        text = findViewById(R.id.edit_text);
        get_Button = findViewById(R.id.gn_btn);
        imageView = findViewById(R.id.code_image);
        imageBack = findViewById(R.id.back);
        imageSave = findViewById(R.id.save);
        imageShare = findViewById(R.id.share);


        //generate the bitmap
        get_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text2QR = text.getText().toString().trim();
                if (text2QR != null) {
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(text2QR, BarcodeFormat.QR_CODE, 200, 200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    text.setVisibility(View.INVISIBLE);
                    get_Button.setVisibility(View.INVISIBLE);
                    imageSave.setVisibility(View.VISIBLE);
                    imageShare.setVisibility(View.VISIBLE);

                }
            }

        });


        //back press
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();;
            }
        });

        //shave bitmap image to internal storage of your device
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File pictureFile = getOutputMediaFile();
                if (pictureFile == null) {
                    Log.d("Tag",
                            "Error creating media file, check storage permissions: ");// e.getMessage());
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
                    fos.close();
                    Toast.makeText(getApplicationContext(),"save",Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    Log.d("Tag", "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("Tag", "Error accessing file: " + e.getMessage());
                }
            }
        });

        //share bitmap to all apps
        imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,"title", null);
                Uri bitmapUri = Uri.parse(bitmapPath);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });


    }


    //path for saving image(bitmap)
    private  File getOutputMediaFile(){

        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

}



