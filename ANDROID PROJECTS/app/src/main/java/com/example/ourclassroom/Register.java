package com.example.ourclassroom;

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
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Register extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {







    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);




        //status bar hider
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //status bar hider end


        //clickable text view
        TextView textView = findViewById(R.id.text_view);
        String text = "Already have an account ? LOG IN";
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
                Intent intent = new Intent (Register.this, Login.class);
                startActivity(intent);

            }


        };
        ss.setSpan(clickableSpan, 25,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

