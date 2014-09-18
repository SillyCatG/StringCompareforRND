package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Garfield_2 on 2014/7/1.
 *
 * this class is let user to listen the key from the system and confirm it
 * user is able to reply the sound and the result will be record
 */
public class Test4Activity extends Activity implements View.OnClickListener {
    private ImageButton soundButton;
    private Button yesButton,noButton;
    private TextView key;
    private MediaPlayer soundPlay;
    private SharedPreferences sharePrefDate;
    private Context context = this;
    private String intentPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test4_layout);

        soundButton = (ImageButton)findViewById(R.id.hexTest7SoundButton);
        yesButton = (Button)findViewById(R.id.hexTest7YesButton);
        noButton = (Button)findViewById(R.id.hexTest7NoButton);
        key = (TextView)findViewById(R.id.hexTest7Key);
        sharePrefDate = getSharedPreferences("sharePref",0);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            intentPass = extras.getString("intentPass");
        else
            Toast.makeText(this, "Put Extra Null", Toast.LENGTH_LONG).show();
        randomKey();
        noButton.setOnClickListener(this);
        yesButton.setOnClickListener(this);
        soundButton.setOnClickListener(this);
    }

    private void randomKey() {
        String randomStr = "";
        if(intentPass.equals("base32Test4")){
            String[] keys = {"7dxb cels qg4c dfnb gkqb",
                    "7dsb cels qg4c bfnb gkqb",
                    "7dxb cels qg4c dsnn gaqb",};
            randomStr = keys[new Random().nextInt(keys.length)];
        }else if(intentPass.equals("hexGroup2Test4")){
            String[] keys = {"94 78 70 37 55 7d 66 bd 77 65 23 7d b6 4d 10 c0 0b be 20 57",
                    "94 78 70 37 55 1d 66 bd 77 65 23 7b b6 4d 10 C0 0b be 20 57",
                    "94 78 70 87 55 7d 66 bd 77 65 23 7d b6 4d 70 C0 0b be 20 57",};
            randomStr = keys[new Random().nextInt(keys.length)];
        }else if(intentPass.equals("hexGroup4Test4")){
            String[] keys = {"6c21 51d4 07cd e9e1 0ea3 ee28 2cdc 58a5 c4b7 0b16",
                    "6c21 51d4 07c0 e9e1 0ea3 ee28 2cdc 56a5 c4b7 0b16",
                    "6c21 51d4 d7cd e9e1 0ea3 ee28 2cdc 58a5 c4d7 0b16",};
            randomStr = keys[new Random().nextInt(keys.length)];
        }
        key.setText(randomStr);

    }

    @Override
    public void onBackPressed(){


    }

    @Override
    public void onClick(View v) {
        if(v == yesButton || v == noButton){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            Intent intent = null;
            if(intentPass.equals("base32Test4")){
                if((key.getText().toString().equals("7dxb cels qg4c dfnb gkqb")
                        && v == yesButton)||(v == noButton
                        && !key.getText().toString().equals("7dxb cels qg4c dfnb gkqb")))
                    shareEdit.putBoolean("base32Test4Result",true);
                else
                    shareEdit.putBoolean("base32Test4Result",false);
                shareEdit.putString("base32Test4EndTime", strDate);
                shareEdit.putString("base32Test5StartTime", strDate);
                intent = new Intent(Test4Activity.this, Test5Activity.class);
                intent.putExtra("intentPass","base32Test5");
            }else if(intentPass.equals("hexGroup2Test4")){
                if((key.getText().toString().equals("94 78 70 37 55 7d 66 bd 77 65 23 7d b6 4d 10 c0 0b be 20 57")
                        && v == yesButton) || (v == noButton &&
                        !key.getText().toString().equals("94 78 70 37 55 7d 66 bd 77 65 23 7d b6 4d 10 c0 0b be 20 57")))
                    shareEdit.putBoolean("test8Result",true);
                else
                    shareEdit.putBoolean("test8Result",false);
                shareEdit.putString("hexGroup2Test4EndTime",strDate);
                shareEdit.putString("hexGroup2Test5StartTime",strDate);
                intent = new Intent(Test4Activity.this, Test5Activity.class);
                intent.putExtra("intentPass","hexGroup2Test5");
            }else if(intentPass.equals("hexGroup4Test4")){
                if((key.getText().toString().equals("6c21 51d4 07cd e9e1 0ea3 ee28 2cdc 58a5 c4b7 0b16")
                        && v == yesButton) || (v == noButton &&
                        !key.getText().toString().equals("6c21 51d4 07cd e9e1 0ea3 ee28 2cdc 58a5 c4b7 0b16")))
                    shareEdit.putBoolean("test7Result",true);
                else
                    shareEdit.putBoolean("test7Result",false);
                shareEdit.putString("hexGroup4Test4EndTime",strDate);
                shareEdit.putString("hexGroup4Test5StartTime",strDate);
                intent = new Intent(Test4Activity.this, Test5Activity.class);
                intent.putExtra("intentPass","hexGroup4Test5");
            }
            shareEdit.commit();
            if(soundPlay != null)
                soundPlay.stop();

            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Intent Null",Toast.LENGTH_LONG).show();
            }
        }
        if(v == soundButton){
            if(soundPlay == null){
                if(intentPass.equals("base32Test4")){
                    soundPlay = MediaPlayer.create(context,R.raw.base32_2);
                }else if(intentPass.equals("hexGroup2Test4")){
                    soundPlay = MediaPlayer.create(context,R.raw.hexgroup2);
                }else if(intentPass.equals("hexGroup4Test4")){
                    soundPlay = MediaPlayer.create(context,R.raw.keysound1);
                }
            }
            if(soundPlay.isPlaying()) {
                soundPlay.stop();
                soundPlay.release();
                soundPlay = null;
            }else{
                soundPlay.start();
            }
        }
    }
}
