package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Garfield_2 on 2014/6/12.
 *
 * this class is let user to select one of the option to continue the test
 */
public class SubMenuActivity extends Activity implements View.OnClickListener {
    private SharedPreferences sharePrefDate;
    private Button test1Btn,test2Btn;
    private boolean isTest1Done,isTest2Done;
    private String intentPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_menu);

        test1Btn = (Button)findViewById(R.id.sub_menu_btn_1);
        test2Btn = (Button)findViewById(R.id.sub_menu_btn_2);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            intentPass = extras.getString("intentPass");

        if(intentPass.equals("hex")){
            test1Btn.setText("Group of 4");
            test2Btn.setText("Group of 2");
        }else if(intentPass.equals("word")){
            test1Btn.setText("English Word");
            test2Btn.setText("Chinese Word");
        }

        update();
        test1Btn.setOnClickListener(this);
        test2Btn.setOnClickListener(this);
    }

    public void update(){
        sharePrefDate=getSharedPreferences("sharePref",0);
        if(intentPass.equals("hex")){
            isTest1Done = sharePrefDate.getBoolean("isTest1Done",false);
            isTest2Done = sharePrefDate.getBoolean("isTest2Done",false);
        }else if(intentPass.equals("word")){
            isTest1Done = sharePrefDate.getBoolean("isEngDone",false);
            isTest2Done = sharePrefDate.getBoolean("isChiDone",false);
        }
        if(isTest1Done)
            test1Btn.setEnabled(false);
        else
            test1Btn.setEnabled(true);
        if(isTest2Done)
            test2Btn.setEnabled(false);
        else
            test2Btn.setEnabled(true);

        if(isTest1Done && isTest2Done){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            if(intentPass.equals("hex")){
                shareEdit.putBoolean("isHexDone",true);
            }else if(intentPass.equals("word")){
                shareEdit.putBoolean("isWordDone",true);
            }
            Intent mainMenuIntent = new Intent(SubMenuActivity.this, MainMenuActivity.class);
            mainMenuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainMenuIntent);
            shareEdit.commit();
        }
    }



    @Override
    public void onBackPressed(){


    }

    @Override
    public void onClick(View v) {
        if(v == test1Btn || v == test2Btn){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            Intent intent = null;
            if(intentPass.equals("hex") && v == test1Btn){
                intent = new Intent(SubMenuActivity.this, Test1Activity.class);
                shareEdit.putString("hexGroup4Test1StartTime",strDate);
                intent.putExtra("intentPass","hexGroup4Test1");
            }else if(intentPass.equals("word") && v == test1Btn){
                intent = new Intent(SubMenuActivity.this, Test1Activity.class);
                intent.putExtra("intentPass","englishTest1");
                shareEdit.putString("englishTest1StartTime",strDate);
            }else if(intentPass.equals("hex") && v == test2Btn){
                intent = new Intent(SubMenuActivity.this, Test1Activity.class);
                intent.putExtra("intentPass","hexGroup2Test1");
                shareEdit.putString("hexGroup2Test1StartTime",strDate);
            }else if(intentPass.equals("word") && v == test2Btn){
                intent = new Intent(SubMenuActivity.this, Test1Activity.class);
                intent.putExtra("intentPass","chineseTest1");
                shareEdit.putString("chineseTest1StartTime",strDate);
            }
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Intent Null",Toast.LENGTH_LONG).show();
            }
            shareEdit.commit();
        }
    }
}
