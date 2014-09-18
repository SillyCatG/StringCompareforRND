package com.example.stringcompareforrnd.stringcompare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    SharedPreferences sharePrefDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharePrefDate = getSharedPreferences("sharePref",0);
        Button startBtn = (Button) findViewById(R.id.startBtn);
        if(!isNetworkAvailable()){		 	// check if the network is connection
            failConnection();				// show the network error message
        }else{
            /*
            set start button and able to start the test
            also the system will record the time when the people start the test
             */
            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // creating the new intent
                    Intent startIntent = new Intent(MainActivity.this,MainMenuActivity.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(startIntent);
                    // creating the shared preferences
                    SharedPreferences.Editor shareEdit = sharePrefDate.edit();
                    // clear all the record
                    shareEdit.clear();
                    // taking the current time
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                    String strDate = sdf.format(c.getTime());
                    shareEdit.putString("StartTime",strDate);
                    shareEdit.commit();
                }
            });

        }



    }

    private void failConnection(){						// setup the network connection fail method
        AlertDialog.Builder builder = new AlertDialog.Builder(this);	// create message dialog
        builder.setMessage("Internet connetion is not found\n" +
                "Please connetion the internet")
                .setTitle("Internet Error");
        AlertDialog dialog = builder.create();
        dialog.show();						// show the dialog
        Thread close = new Thread(){		// create a thread
            @Override
            public void run(){
                try {
                    sleep(3000);			// wait 3 second
                    finish();				// close the program
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        close.start();						// start a closing thread


    }

    private boolean isNetworkAvailable() { // create check network connection boolean
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
