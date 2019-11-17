package com.example.barcodescanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


public class CodeGenerateActivity extends AppCompatActivity {

    CardView text; CardView web; CardView Skype; CardView Fb; CardView insta; CardView Twitter;
    CardView Link; CardView Vcard; CardView sms; CardView email; CardView phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_code_generate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       text = findViewById(R.id.textCode);
       web = findViewById(R.id.web);
       Skype = findViewById(R.id.skype);
       Fb = findViewById(R.id.fb);
       insta = findViewById(R.id.insta);
       Twitter = findViewById(R.id.twitter);
       Link = findViewById(R.id.link);
       Vcard = findViewById(R.id.vcard);
       sms = findViewById(R.id.Sms);
       email = findViewById(R.id.Email);
       phoneNo = findViewById(R.id.phone_no);


        //click on Text cardView
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, TextCodeActivity.class);
                startActivity(i);
            }
        });

        //click on Website cardView
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, WebsiteActivity.class);
                startActivity(i);
            }
        });

        //click on Skype cardView
        Skype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, SkypeActivity.class);
                startActivity(i);
            }
        });

        //click on Fb cardView
        Fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, FbActivity.class);
                startActivity(i);
            }
        });

        //click on Instagram cardView
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, InstagramActivity.class);
                startActivity(i);
            }
        });

        //click on Twitter cardView
        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(CodeGenerateActivity.this, TwitterActivity.class);
                startActivity(i);
            }
        });

        //click on Linkdein cardView
        Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, LinkdeinActivity.class);
                startActivity(i);
            }
        });

        //click on Vcard cardView
        Vcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, VcardActivity.class);
                startActivity(i);
            }
        });

        //click on Sms cardView
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, SmsActivity.class);
                startActivity(i);
            }
        });

        //click on Email cardView
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, EmailActivity.class);
                startActivity(i);
            }
        });

        //click on Phone No cardView
        phoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CodeGenerateActivity.this, PhoneNoActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}







