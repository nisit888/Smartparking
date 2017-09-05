package com.user.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Building6Activity extends AppCompatActivity {

    private EditText _userName;
    private EditText _numberCar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building6);

//        Intent i = getIntent();
  //      String _userName = i.getStringExtra("username");
    //    String _numberCar = i.getStringExtra("numbercar");
        //Toast.makeText(getApplicationContext(),_userName + ":",Toast.LENGTH_LONG).show();


    }
    public void onclickBook6(View v) {
        Intent a = new Intent(Building6Activity.this, ResisActivity.class);
        startActivity(a);
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
            Intent x = new Intent(Building6Activity.this, MainActivity.class);
            startActivity(x);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
