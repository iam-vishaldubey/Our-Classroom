package com.example.ourclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Login extends AppCompatActivity  {


    @Override
    public void onBackPressed() {
        
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView textView = findViewById(R.id.new_here);
        String text = "New here ? Create an account";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);

            }

            @Override
            public void onClick(@NonNull View widget) {
                OpenActivity();
            }
            public void OpenActivity()
            {
                Intent intent = new Intent (Login.this, Homenavigation.class);
                startActivity(intent);

            }


        };
        ss.setSpan(clickableSpan, 11,28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    };








    }
