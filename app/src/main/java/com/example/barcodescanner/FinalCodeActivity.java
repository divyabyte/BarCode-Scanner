package com.example.barcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinalCodeActivity extends AppCompatActivity {

    Bitmap bitmap;
    ImageView image;
    ImageView share;
    ImageView save;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_generate);

        //get bitmap
        Intent i = getIntent();
        bitmap = i.getParcelableExtra("bitmapFinal");

        image = findViewById(R.id.image);
        save = findViewById(R.id.save);
        share = findViewById(R.id.share);
        back = findViewById(R.id.arrow);

        //set bitmap on imageview
        image.setImageBitmap(bitmap);

        //share bitmap
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
                Uri bitmapUri = Uri.parse(bitmapPath);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

        //save bitmap
        save.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(getApplicationContext(), "save", Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    Log.d("Tag", "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("Tag", "Error accessing file: " + e.getMessage());
                }
            }
        });

        //onBackPress
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
