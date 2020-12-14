package com.example.smartcarparkingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    EditText name, pw;
    Button signin;
    TextView signup;
    FirebaseAuth mfirebaseauth;
    Button googlesigninbtn;
    GoogleSignInClient mgooglesignin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mauthstatelistener;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.editTextTextPersonName);
        pw=findViewById(R.id.editTextTextPassword);
        signin=findViewById(R.id.button);
        googlesigninbtn=findViewById(R.id.button2);
        signup=findViewById(R.id.tvsignUp);
        mAuth = FirebaseAuth.getInstance();


        processRequest();

        mfirebaseauth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        mauthstatelistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseuser=mfirebaseauth.getCurrentUser();
                if(mFirebaseuser != null)
                {
                    Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this,LotSelectionModule.class);
                    startActivity(intent);
                }

            }
        };
        signin.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(MainActivity.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && password.isEmpty()))
                {
                    mfirebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(!(task.isSuccessful()))
                            {
                                Toast.makeText(MainActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intent=new Intent(MainActivity.this,LotSelectionModule.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        googlesigninbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processLogin();
            }
        });


    }



    private void processRequest()
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mgooglesignin= GoogleSignIn.getClient(this,gso);
    }

    private void processLogin()
    {
        Intent signInIntent = mgooglesignin.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken)
    {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i=new Intent(MainActivity.this,LotSelectionModule.class);
                                startActivity(i);
                                finish();
                            }
                        else
                            {
                                Toast.makeText(MainActivity.this, "Google sign in failed. Try again!", Toast.LENGTH_SHORT).show();
                            }

                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mfirebaseauth.addAuthStateListener(mauthstatelistener);

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Intent i=new Intent(MainActivity.this,LotSelectionModule.class);
            startActivity(i);
        }

    }
}