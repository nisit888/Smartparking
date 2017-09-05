package com.user.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.TimePickerDialog;


import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class ResisActivity extends AppCompatActivity {

    public DatabaseReference myRef;

    public String _resisNumberCar;
    public String _resisUserName;
    public String _resisDate;
    public String _resisBuild;
    public String _resisPositionCar;
    public String _resisTime;

    private TextView _numberCar;
    private Button btnSubmitRe;
    private Button btnCancelRe;
    private EditText resisUserName;
    private EditText resisNumberCar;
    private TextView resisDate;
    private EditText resisBuild;
    private EditText resisPositionCar;
    private TextView resisTime;

    private DatePickerDialog mDatePicker;
    private TimePickerDialog mTimePicker;
    private Calendar mCalendar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resis);

        //Intent i = getIntent();
        //       String _userName = i.getStringExtra("username");
        //       String _numberCar = i.getStringExtra("numbercar");

        btnSubmitRe = (Button) findViewById(R.id.btnSubmitRe);
        btnCancelRe = (Button) findViewById(R.id.btnCancelRe);
        resisUserName = (EditText) findViewById(R.id.resisUserName);
        resisNumberCar = (EditText) findViewById(R.id.resisNumberCar);
        resisDate = (TextView) findViewById(R.id.resisDate);
        resisBuild = (EditText) findViewById(R.id.resisBuild);
        resisPositionCar = (EditText) findViewById(R.id.resisPositionCar);
        resisTime = (TextView) findViewById(R.id.resisTime);

        mCalendar = Calendar.getInstance();

        mDatePicker = DatePickerDialog.newInstance(onDateSetListener,
                mCalendar.get(Calendar.YEAR),       // ปี
                mCalendar.get(Calendar.MONTH),      // เดือน
                mCalendar.get(Calendar.DAY_OF_MONTH),// วัน (1-31)
                false);

        mTimePicker = TimePickerDialog.newInstance(onTimeSetListener,
                10,     // หน่วยเข็มชั่วโมง
                30,     // เข็มนาที
                true,   // ใช้ระบบนับแบบ 24-Hr หรือไม่? (0 - 23 นาฬิกา)
                false); // ให้สั่นหรือไม่?



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map map = (Map) dataSnapshot.getValue();
                String _numberCar = String.valueOf(map.get("Carnumbar.Carnumber"));
                String _userName = String.valueOf(map.get(".Username"));

                resisNumberCar.setText(_numberCar);
                resisUserName.setText(_userName);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent x = new Intent(ResisActivity.this, MainActivity.class);
            startActivity(x);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onclickCancle(View v) {
        Intent a = new Intent(ResisActivity.this, ResisActivity.class);
        startActivity(a);
    }

    public void onclickSubmit(View v) {

        _resisNumberCar = resisNumberCar.getText().toString();
        _resisUserName = resisUserName.getText().toString();
        _resisBuild = resisBuild.getText().toString();
        _resisDate = resisDate.getText().toString();
        _resisPositionCar = resisPositionCar.getText().toString();
        _resisTime = resisTime.getText().toString();
        Intent a = new Intent(ResisActivity.this, UserActivity.class);


        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mUserRef = mRootRef.child(""+_resisNumberCar);

        mUserRef.child("Carnumber").setValue("" + _resisNumberCar);
        mUserRef.child("Username").setValue("" + _resisUserName);
        mUserRef.child("Time").setValue("" + _resisDate);
        mUserRef.child("Date").setValue(""+ _resisTime);
        mUserRef.child("Position").setValue("" + _resisPositionCar);
        mUserRef.child("Building").setValue("" + _resisBuild);


        startActivity(a);
    }

    private TimePickerDialog.OnTimeSetListener onTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(RadialPickerLayout radialPickerLayout, int hourOfDay, int minute) {
                    resisDate.setText(" " + hourOfDay + " : " + minute);
                }
            };

    private DatePickerDialog.OnDateSetListener onDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
                    mCalendar.set(year, month, day);
                    Date date = mCalendar.getTime();
                    String textDate = dateFormat.format(date);

                    resisTime.setText(textDate);
                }
            };

    public void onclickDate(View view) {
        mTimePicker.show(getSupportFragmentManager(), "timePicker");
    }

    public void onclickTime(View view) {
        mDatePicker.setYearRange(2000, 2020);
        mDatePicker.show(getSupportFragmentManager(), "datePicker");
    }
}
