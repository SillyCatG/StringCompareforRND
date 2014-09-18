package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Garfield_2 on 2014/8/11.
 *
 * this class is let user to choose the multi choice key and submit
 * system will record the result
 */
public class WavatarsTest3 extends Activity implements View.OnClickListener {
    private Button submitBtn;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,
    radioButton5;
    private ImageView image1,image2,image3,image4,image5;
    private SharedPreferences sharePrefDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wav_test2);

        submitBtn = (Button)findViewById(R.id.wavTest2SubmitBtn);
        radioButton1 = (RadioButton)findViewById(R.id.wavTest2RadioBtn1);
        radioButton2 = (RadioButton)findViewById(R.id.wavTest2RadioBtn2);
        radioButton3 = (RadioButton)findViewById(R.id.wavTest2RadioBtn3);
        radioButton4 = (RadioButton)findViewById(R.id.wavTest2RadioBtn4);
        radioButton5 = (RadioButton)findViewById(R.id.wavTest2RadioBtn5);
        image1 = (ImageView)findViewById(R.id.wavTest2ImageView1);
        image2 = (ImageView)findViewById(R.id.wavTest2ImageView2);
        image3 = (ImageView)findViewById(R.id.wavTest2ImageView3);
        image4 = (ImageView)findViewById(R.id.wavTest2ImageView4);
        image5 = (ImageView)findViewById(R.id.wavTest2ImageView5);
        sharePrefDate=getSharedPreferences("sharePref",0);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    private void update(){
        radioButton1.setChecked(false);
        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
        radioButton4.setChecked(false);
        radioButton5.setChecked(false);
    }

    @Override
    public void onBackPressed(){


    }

    @Override
    public void onClick(View v) {
        if(v instanceof ImageView){
            update();
            if(v == image1)
                radioButton1.setChecked(true);
            else if(v == image2)
                radioButton2.setChecked(true);
            else if(v == image3)
                radioButton3.setChecked(true);
            else if(v == image4)
                radioButton4.setChecked(true);
            else if(v == image5)
                radioButton5.setChecked(true);
        }
        if(v == submitBtn){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            if(radioButton1.isChecked())
                shareEdit.putBoolean("wavTest3Result",true);
            else
                shareEdit.putBoolean("wavTest3Result",false);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            shareEdit.putString("wavatarTest3EndTime",strDate);
            shareEdit.putString("wavatarTest4StartTime",strDate);
            shareEdit.commit();
            Intent startIntent = new Intent(WavatarsTest3.this,WavatarsTest4.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(startIntent);
        }
    }
}
