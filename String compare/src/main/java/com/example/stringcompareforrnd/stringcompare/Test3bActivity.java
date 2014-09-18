package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Garfield_2 on 2014/6/15.
 *
 * this class is let user build the string of the key with the given string
 * user select from the check box to build and submit, system will record the result
 */
public class Test3bActivity extends Activity implements View.OnClickListener {
    private Button submit;
    private TextView result;
    private String message;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,
            checkBox8,checkBox9,checkBox10,checkBox11,checkBox12,checkBox13,checkBox14,checkBox15,
            checkBox16,checkBox17,checkBox18,checkBox19,checkBox20,checkBox21;
    private SharedPreferences sharePrefDate;
    private ArrayList<String> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test3b_layout);
        message = "";

        wordList = new ArrayList<String>();
        submit = (Button)findViewById(R.id.hex_test6_submit_button);
        result = (TextView)findViewById(R.id.hexText6KeyResult);
        checkBox1 = (CheckBox)findViewById(R.id.hexTest6CheckBox1);
        checkBox2 = (CheckBox)findViewById(R.id.hexTest6CheckBox2);
        checkBox3 = (CheckBox)findViewById(R.id.hexTest6CheckBox3);
        checkBox4 = (CheckBox)findViewById(R.id.hexTest6CheckBox4);
        checkBox5 = (CheckBox)findViewById(R.id.hexTest6CheckBox5);
        checkBox6 = (CheckBox)findViewById(R.id.hexTest6CheckBox6);
        checkBox7 = (CheckBox)findViewById(R.id.hexTest6CheckBox7);
        checkBox8 = (CheckBox)findViewById(R.id.hexTest6CheckBox8);
        checkBox9 = (CheckBox)findViewById(R.id.hexTest6CheckBox9);
        checkBox10 = (CheckBox)findViewById(R.id.hexTest6CheckBox10);
        checkBox11 = (CheckBox)findViewById(R.id.hexTest6CheckBox11);
        checkBox12 = (CheckBox)findViewById(R.id.hexTest6CheckBox12);
        checkBox13 = (CheckBox)findViewById(R.id.hexTest6CheckBox13);
        checkBox14 = (CheckBox)findViewById(R.id.hexTest6CheckBox14);
        checkBox15 = (CheckBox)findViewById(R.id.hexTest6CheckBox15);
        checkBox16 = (CheckBox)findViewById(R.id.hexTest6CheckBox16);
        checkBox17 = (CheckBox)findViewById(R.id.hexTest6CheckBox17);
        checkBox18 = (CheckBox)findViewById(R.id.hexTest6CheckBox18);
        checkBox19 = (CheckBox)findViewById(R.id.hexTest6CheckBox19);
        checkBox20 = (CheckBox)findViewById(R.id.hexTest6CheckBox20);
        checkBox21 = (CheckBox)findViewById(R.id.hexTest6CheckBox21);
        sharePrefDate=getSharedPreferences("sharePref",0);

        result.setText("The Key is:");
        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBox5.setOnClickListener(this);
        checkBox6.setOnClickListener(this);
        checkBox7.setOnClickListener(this);
        checkBox8.setOnClickListener(this);
        checkBox9.setOnClickListener(this);
        checkBox10.setOnClickListener(this);
        checkBox11.setOnClickListener(this);
        checkBox12.setOnClickListener(this);
        checkBox13.setOnClickListener(this);
        checkBox14.setOnClickListener(this);
        checkBox15.setOnClickListener(this);
        checkBox16.setOnClickListener(this);
        checkBox17.setOnClickListener(this);
        checkBox18.setOnClickListener(this);
        checkBox19.setOnClickListener(this);
        checkBox20.setOnClickListener(this);
        checkBox21.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    private void stringChecked(String type,String string){
        String tempString = "";
        if(type.equals("add"))
            wordList.add(string);
        else
            wordList.remove(string);
        if(wordList.size()>0)
            for(String tmp : wordList)tempString += tmp+" ";
        message = tempString.trim();
        result.setText("The Key is: "+message);
    }

    @Override
    public void onBackPressed(){


    }

    @Override
    public void onClick(View v) {
        if(v instanceof CheckBox){
            CheckBox tmpCheckBox = (CheckBox)v;
            if(tmpCheckBox.isChecked())
                stringChecked("add",tmpCheckBox.getText().toString());
            else
                stringChecked("remove",tmpCheckBox.getText().toString());
        }
        if(v == submit){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            Log.i("test 6",message);
            if(message.equals("30 69 74 f1 ed aa f6 a5 11 7e 0b 2b 86 08 d8 47 9e 64 37 13"))
                shareEdit.putBoolean("test6Result",true);
            else
                shareEdit.putBoolean("test6Result",false);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            shareEdit.putString("hexGroup2Test3EndTime",strDate);
            shareEdit.putString("hexGroup2Test4StartTime",strDate);
            shareEdit.commit();
            Intent intent = new Intent(Test3bActivity.this, Test4Activity.class);
            intent.putExtra("intentPass","hexGroup2Test4");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
