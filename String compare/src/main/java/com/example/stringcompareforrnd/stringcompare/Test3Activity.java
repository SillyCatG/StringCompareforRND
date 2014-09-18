package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Garfield_2 on 2014/6/15.
 *
 * this class is let user build the string of the key with the given string
 * user select from the check box to build and submit, system will record the result
 */
public class Test3Activity extends Activity implements View.OnClickListener {
    private Button submit;
    private TextView result;
    private String message,intentPass;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,
            checkBox5,checkBox6,checkBox7,checkBox8,checkBox9,
            checkBox10,checkBox11,checkBox12,checkBox13,checkBox14,checkBox15;
    private SharedPreferences sharePrefDate;
    private ArrayList<String> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test3_layout);

        message = "";
        wordList = new ArrayList<String>();
        submit = (Button)findViewById(R.id.hex_test5_submit_button);
        result = (TextView)findViewById(R.id.hexText5KeyResult);
        checkBox1 = (CheckBox)findViewById(R.id.hexTest5CheckBox1);
        checkBox2 = (CheckBox)findViewById(R.id.hexTest5CheckBox2);
        checkBox3 = (CheckBox)findViewById(R.id.hexTest5CheckBox3);
        checkBox4 = (CheckBox)findViewById(R.id.hexTest5CheckBox4);
        checkBox5 = (CheckBox)findViewById(R.id.hexTest5CheckBox5);
        checkBox6 = (CheckBox)findViewById(R.id.hexTest5CheckBox6);
        checkBox7 = (CheckBox)findViewById(R.id.hexTest5CheckBox7);
        checkBox8 = (CheckBox)findViewById(R.id.hexTest5CheckBox8);
        checkBox9 = (CheckBox)findViewById(R.id.hexTest5CheckBox9);
        checkBox10 = (CheckBox)findViewById(R.id.hexTest5CheckBox10);
        checkBox11 = (CheckBox)findViewById(R.id.hexTest5CheckBox11);
        checkBox12 = (CheckBox)findViewById(R.id.hexTest5CheckBox12);
        checkBox13 = (CheckBox)findViewById(R.id.hexTest5CheckBox13);
        checkBox14 = (CheckBox)findViewById(R.id.hexTest5CheckBox14);
        checkBox15 = (CheckBox)findViewById(R.id.hexTest5CheckBox15);
        sharePrefDate=getSharedPreferences("sharePref",0);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            intentPass = extras.getString("intentPass");
        else
            Toast.makeText(this, "Put Extra Null", Toast.LENGTH_LONG).show();
        if(intentPass.equals("englishTest3")){
            checkBox1.setText("lake");
            checkBox2.setText("goof");
            checkBox3.setText("wiley");
            checkBox4.setText("embark");
            checkBox5.setText("soda");
            checkBox6.setText("ahoy");
            checkBox7.setText("bigot");
            checkBox8.setText("9000");
            checkBox9.setText("vocal");
            checkBox10.setText("pork");
            checkBox11.setText("1960");
            checkBox12.setText("can");
            checkBox13.setText("glyph");
            checkBox14.setText("viola");
            checkBox15.setText("ted");
        }else if(intentPass.equals("chineseTest3")){
            checkBox1.setText("520");
            checkBox2.setText("MingYun");
            checkBox3.setText("TianQi");
            checkBox4.setText("ZhongDong");
            checkBox5.setText("9494");
            checkBox6.setText("XiGua");
            checkBox7.setText("DaXiang");
            checkBox8.setText("LiMing");
            checkBox9.setText("ShuiTong");
            checkBox10.setText("YingXiong");
            checkBox11.setText("YiGe");
            checkBox12.setText("ChangShi");
            checkBox13.setText("YinWei");
            checkBox14.setText("MuTou");
            checkBox15.setText("DaHai");
        }else if(intentPass.equals("base32Test3")){
            checkBox1.setText("ty67");
            checkBox2.setText("sxui");
            checkBox3.setText("456f");
            checkBox4.setText("yu45");
            checkBox5.setText("ay4t");
            checkBox6.setText("4t67");
            checkBox7.setText("4d6y");
            checkBox8.setText("y833");
            checkBox9.setText("xr3t");
            checkBox10.setText("qd67");
            checkBox11.setText("5ioy");
            checkBox12.setText("2456");
            checkBox13.setText("y89e");
            checkBox14.setText("zx7f");
            checkBox15.setText("wjop");
        }
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
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            Intent intent = null;
            if(intentPass.equals("englishTest3")){
                if(message.equals("lake soda 9000 vocal embark can glyph bigot ahoy 1960"))
                    shareEdit.putBoolean("englishTest3Result",true);
                else
                    shareEdit.putBoolean("englishTest3Result",false);
                shareEdit.putString("englishTest3EndTime",strDate);
                shareEdit.putString("englishTest4StartTime",strDate);
                intent = new Intent(Test3Activity.this, Test5Activity.class);
                intent.putExtra("intentPass","englishTest5");
            }else if(intentPass.equals("chineseTest3")){
                if(message.equals("9494 MingYun 520 LiMing DaXiang ChangShi DaHai YiGe TianQi ZhongDong"))
                    shareEdit.putBoolean("chineseTest3Result",true);
                else
                    shareEdit.putBoolean("chineseTest3Result",false);
                shareEdit.putString("chineseTest3EndTime",strDate);
                shareEdit.putString("chineseTest4StartTime",strDate);
                intent = new Intent(Test3Activity.this, Test5Activity.class);
                intent.putExtra("intentPass","chineseTest5");
            }else if(intentPass.equals("base32Test3")){
                if(message.equals("456f 4d6y y89e xr3t yu45"))
                    shareEdit.putBoolean("base32Test3Result",true);
                else
                    shareEdit.putBoolean("base32Test3Result",false);
                shareEdit.putString("base32Test3EndTime", strDate);
                shareEdit.putString("base32Test4StartTime", strDate);
                intent = new Intent(Test3Activity.this, Test4Activity.class);
                intent.putExtra("intentPass","base32Test4");
            }else if(intentPass.equals("hexGroup4Test3")){
                if(message.equals("5e83 e758 e034 4517 9003 9877 38cc 69e4 5ec1 8e09"))
                    shareEdit.putBoolean("test5Result",true);
                else
                    shareEdit.putBoolean("test5Result",false);
                shareEdit.putString("hexGroup4Test3EndTime",strDate);
                shareEdit.putString("hexGroup4Test4StartTime",strDate);
                intent = new Intent(Test3Activity.this, Test4Activity.class);
                intent.putExtra("intentPass","hexGroup4Test4");
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
