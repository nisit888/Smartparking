package com.user.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.AlertDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private String _userName;
    private String _carNumber;

    public EditText txt_email, txt_password;
    public FirebaseAuth auth;
    public ProgressBar progressBar;
    public Button login_action, forget_pass;

    //public static String numbercar;

    public DatabaseReference myUserRef;
    private Button btnSubmit;
    private EditText userName;
    private EditText carNumber;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        userName = (EditText) findViewById(R.id.userName);
        carNumber = (EditText) findViewById(R.id.carNumber);



    }


        // DatabaseReference mMessagesRef = mRootRef.child("messages");




    public void onclickMaps(View v) {



        _userName = userName.getText().toString();
        _carNumber = carNumber.getText().toString();


        if (!_userName.isEmpty()) {
            Intent a = new Intent(MainActivity.this, UserActivity.class);
//            EditText _userName = (EditText)findViewById(R.id.userName);
//            EditText _number = (EditText)findViewById(R.id.number);
            a.putExtra("carnumber", _carNumber);
            a.putExtra("username", _userName);

//            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
//            DatabaseReference mUserRef = mRootRef.child("" + _numbarCar);
//
//            //Map<String,Object> _userName = new HashMap<String, Object>();
//            mUserRef.child("Username").setValue("" + _userName);
//            mUserRef.child("Carnumber").setValue("" + _carNumber);

            startActivity(a);
        } else {
            final AlertDialog.Builder dDialog = new AlertDialog.Builder(this);
            dDialog.setTitle("Oh!! ");
            dDialog.setIcon(android.R.drawable.btn_star_big_on);
            dDialog.setMessage("Please enter your E-mail");
            dDialog.setPositiveButton("Close", null);
            dDialog.show();
        }
    }

    public void onclicktoAuth(View view) {
        Intent b = new Intent(MainActivity.this, AuthActivity.class);
        startActivity(b);

    }
}

