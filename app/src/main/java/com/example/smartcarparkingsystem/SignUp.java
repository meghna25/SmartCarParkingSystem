package com.example.smartcarparkingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText name, pw;
    Button signup;

    TextView signin;
    FirebaseAuth mfirebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         name=findViewById(R.id.editTextTextPersonName3);
         pw=findViewById(R.id.editTextTextPersonName4);
         signup=findViewById(R.id.button3);

         signin=findViewById(R.id.textView10);
         mfirebaseauth=FirebaseAuth.getInstance();

         signin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(SignUp.this, LotSelectionModule.class);
                 startActivity(intent);

             }
         });

         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             {
                 String email=name.getText().toString().trim();
                 String password=pw.getText().toString().trim();
                 if(email.isEmpty())
                 {
                     name.setError("Please enter email ID");
                     name.requestFocus();
                 }
                 else if(password.isEmpty())
                 {
                     pw.setError("Enter your password");
                     pw.requestFocus();
                 }
                 else if(email.isEmpty() && password.isEmpty())
                 {
                     Toast.makeText(SignUp.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                 }
                 else if(!(email.isEmpty() && password.isEmpty()))
                 {
                    mfirebaseauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                Intent i=new Intent(SignUp.this, LotSelectionModule.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(SignUp.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                 }

                 else
                 {
                     Toast.makeText(SignUp.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                 }
             }
         });

    }
}