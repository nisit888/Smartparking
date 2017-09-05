package com.user.smartparking;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText userName;
    private EditText userCarNumber;
    private Button btnSubmitRegister;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String _userName;
    private String _userCarNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userName = (EditText) findViewById(R.id.userName);
        userCarNumber = (EditText) findViewById(R.id.userCarNumber);
        btnSubmitRegister = (Button) findViewById(R.id.btnSubmitRegister);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("Auth:", user.getUid());
                }

            }
        };


    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);

    }

    public void onclicktologin(View view) {
        Intent b = new Intent(AuthActivity.this, MainActivity.class);
        _userName = userName.getText().toString();
        _userCarNumber = userCarNumber.getText().toString();

        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Log.w("Auth:", userCarNumber.getText().toString());

                }
            }
        });

        startActivity(b);
    }


}

