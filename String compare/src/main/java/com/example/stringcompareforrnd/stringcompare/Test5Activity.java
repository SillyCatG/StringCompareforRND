package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Garfield_2 on 2014/8/25.
 *
 * this class is let user to listen by the real person to read the key
 * and confirm with the system, result will be record
 */
public class Test5Activity extends Activity implements View.OnClickListener {
    private Button yesBtn,noBtn;
    private TextView key,title;
    private SharedPreferences sharePrefDate;
    private String intentPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test5_layout);

        yesBtn = (Button)findViewById(R.id.hexTest8YesButton);
        noBtn = (Button)findViewById(R.id.hexTest8NoButton);
        key = (TextView)findViewById(R.id.hexTest8Key);
        title = (TextView)findViewById(R.id.test8TV);
        sharePrefDate = getSharedPreferences("sharePref",0);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            intentPass = extras.getString("intentPass");
        else
            Toast.makeText(this, "Put Extra Null", Toast.LENGTH_LONG).show();

        randomKey();
        noBtn.setOnClickListener(this);
        yesBtn.setOnClickListener(this);
    }

    private void randomKey() {
        String randomStr = "";
        if(intentPass.equals("hexGroup4Test5")){
            title.setText("Test 5");
            String[] keys = {"7502 a934 8e50 23f8 57f2 09e3 8d47 b593 82bb 8422",
                    "7502 a934 8e50 23f8 57f2 09e3 8b47 b593 82bb 8422",
                    "7502 a934 8e50 23f8 57f2 09e3 8d47 b593 82db 8422",};
            randomStr = keys[new Random().nextInt(keys.length)];
        }else if(intentPass.equals("hexGroup2Test5")){
            title.setText("Test 5");
            String[] keys = {"72 03 a4 75 2b 0a 3d 47 90 58 72 a0 93 74 90 57 db 01 92 73",
                    "72 03 a4 75 2b 08 3d 47 90 58 72 a0 93 74 90 57 db 01 92 73",
                    "72 03 a4 75 2b 0a 3d 47 90 58 72 a0 98 74 90 57 db 01 92 73",};
            randomStr = keys[new Random().nextInt(keys.length)];
        }else if(intentPass.equals("base32Test5")){
            title.setText("Test 5");
            String[] keys = {"4839 tueg 9894 03gr xlwe",
                    "4a39 tueg 9894 03gr xlwe",
                    "4839 tueg 9a94 03gr xlwe",};
            randomStr = keys[new Random().nextInt(keys.length)];
        }else if(intentPass.equals("englishTest5")){
            title.setText("Test 4");
            String[] keys = {"bore sarah ceres gar smirk skulk shank kt tidy source",
                    "bore sarah ceres gar smirk skulk shank ki tidy source",
                    "bore sarah ceres gar smirk skulk shank kt tedi source",};
            randomStr = keys[new Random().nextInt(keys.length)];
        }else if(intentPass.equals("chineseTest5")){
            title.setText("Test 4");
            String[] keys = {"TaiShan GongLi FenBi ShengRi YiSheng JieShu BingShan BaiYun ZuQiu XingQiYi",
                    "TaiShan GongNi FenBi ShengRi YiSheng JieShu BingShan BaiYun ZuQiu XingQiYi",
                    "TaiShan GongLi FenBi ShengRi YiSheng JieYun BingShan BaiYun ZuQiu XingQiYi",};
            randomStr = keys[new Random().nextInt(keys.length)];
        }
        key.setText(randomStr);

    }

    @Override
    public void onClick(View v) {
        if(v == yesBtn || v == noBtn){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            Intent intent = null;

            if(intentPass.equals("hexGroup4Test5")){
                if((key.getText().toString().equals("7502 a934 8e50 23f8 57f2 09e3 8d47 b593 82bb 8422")
                        && v == yesBtn) || (v == noBtn &&
                        !key.getText().toString().equals("7502 a934 8e50 23f8 57f2 09e3 8d47 b593 82bb 8422")))
                    shareEdit.putBoolean("test9Result",true);
                else
                    shareEdit.putBoolean("test9Result",false);
                shareEdit.putBoolean("isTest1Done",true);
                shareEdit.putString("hexGroup4Test5EndTime",strDate);
                intent = new Intent(Test5Activity.this, SubMenuActivity.class);
                intent.putExtra("intentPass","hex");
            }else if(intentPass.equals("hexGroup2Test5")){
                if((key.getText().toString().equals("72 03 a4 75 2b 0a 3d 47 90 58 72 a0 93 74 90 57 db 01 92 73")
                        && v == yesBtn) || (v == noBtn &&
                        !key.getText().toString().equals("72 03 a4 75 2b 0a 3d 47 90 58 72 a0 93 74 90 57 db 01 92 73")))
                    shareEdit.putBoolean("test10Result",true);
                else
                    shareEdit.putBoolean("test10Result",false);
                shareEdit.putBoolean("isTest2Done",true);
                shareEdit.putString("hexGroup2Test5EndTime",strDate);
                intent = new Intent(Test5Activity.this, SubMenuActivity.class);
                intent.putExtra("intentPass","hex");
            }else if(intentPass.equals("base32Test5")){
                if((key.getText().toString().equals("4839 tueg 9894 03gr xlwe")
                        && v == yesBtn) || (v == noBtn &&
                        !key.getText().toString().equals("4839 tueg 9894 03gr xlwe")))
                    shareEdit.putBoolean("base32Test5Result",true);
                else
                    shareEdit.putBoolean("base32Test5Result",false);
                shareEdit.putBoolean("isBase32Done",true);
                shareEdit.putString("base32Test5EndTime", strDate);
                intent = new Intent(Test5Activity.this, MainMenuActivity.class);
            }else if(intentPass.equals("englishTest5")){
                if((key.getText().toString().equals("bore sarah ceres gar smirk skulk shank kt tidy source")
                        && v == yesBtn) || (v == noBtn &&
                        !key.getText().toString().equals("bore sarah ceres gar smirk skulk shank kt tidy source")))
                    shareEdit.putBoolean("englishTest4Result",true);
                else
                    shareEdit.putBoolean("englishTest4Result",false);
                shareEdit.putBoolean("isEngDone",true);
                shareEdit.putString("englishTest4EndTime",strDate);
                intent = new Intent(Test5Activity.this, SubMenuActivity.class);
                intent.putExtra("intentPass","word");
            }else if(intentPass.equals("chineseTest5")){
                if((key.getText().toString().equals("TaiShan GongLi FenBi ShengRi YiSheng JieShu BingShan BaiYun ZuQiu XingQiYi")
                        && v == yesBtn) || (v == noBtn &&
                        !key.getText().toString().equals("TaiShan GongLi FenBi ShengRi YiSheng JieShu BingShan BaiYun ZuQiu XingQiYi")))
                    shareEdit.putBoolean("chineseTest4Result",true);
                else
                    shareEdit.putBoolean("chineseTest4Result",false);
                shareEdit.putBoolean("isChiDone",true);
                shareEdit.putString("chineseTest4EndTime",strDate);
                intent = new Intent(Test5Activity.this, SubMenuActivity.class);
                intent.putExtra("intentPass","word");
            }



            shareEdit.commit();
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Intent Null",Toast.LENGTH_LONG).show();
            }
        }
    }
}
