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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {


EditText txt_fullname,txt_mobileno,txt_email,txt_enrollmentno,txt_password;
Button signup;
    DatabaseReference databaseReference;
FirebaseAuth firebaseAuth;


    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Type Casting
        txt_fullname =(EditText)findViewById(R.id.txt_fullname);
        txt_mobileno =(EditText)findViewById(R.id.txt_mobileno);
        txt_email =(EditText)findViewById(R.id.txt_email);
        txt_enrollmentno =(EditText)findViewById(R.id.txt_enrollmentno);
        txt_password =(EditText)findViewById(R.id.txt_password);
        signup =(Button) findViewById(R.id.signup);


databaseReference = FirebaseDatabase.getInstance().getReference("Students");

firebaseAuth =FirebaseAuth.getInstance();
// signup btn event

signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        final String fullname=txt_fullname.getText().toString();
        final String mobileno=txt_mobileno.getText().toString();
        final String email=txt_email.getText().toString();
        final String enrollmentno=txt_enrollmentno.getText().toString();
        String password=txt_password.getText().toString();


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            student information =new student(
                                    fullname,
                                    mobileno,
                                    email,
                                    enrollmentno
                            );

                            FirebaseDatabase.getInstance().getReference("Students")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),Homenavigation.class));
                                }
                            });


                        } else {
                            Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });



    }
});







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

