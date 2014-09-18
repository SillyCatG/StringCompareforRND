package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Garfield_2 on 2014/6/12.
 *
 * this class is about to input the string into the edit text box
 * when user click the submit button, the system will getting the
 * string from the edit text box and compare the string with the
 * correct string, if compare is false, system will send the
 * share preferences as record and save for later
 */
public class Test1Activity extends Activity implements View.OnClickListener {
    private SharedPreferences sharePrefDate;
    private EditText inputET;
    private String intentPass;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1_layout);

        submitBtn = (Button)findViewById(R.id.hexTest1SubmitBtn);
        inputET = (EditText)findViewById(R.id.hexTest1ET);
        inputET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    submitBtn.callOnClick();
                    return true;
                }
                return false;
            }
        });
        sharePrefDate=getSharedPreferences("sharePref",0);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            intentPass = extras.getString("intentPass");
        else
            Toast.makeText(this,"Put Extra Null",Toast.LENGTH_LONG).show();
        submitBtn.setOnClickListener(this);
    }


    @Override
    public void onBackPressed(){


    }

    @Override
    public void onClick(View v) {
        if(v == submitBtn){
            Intent intent = null;
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            String test1String = inputET.getText().toString();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            String compareString;
            if(intentPass.equals("hexGroup4Test1")){
                intent = new Intent(Test1Activity.this,Test2Activity.class);
                intent.putExtra("intentPass","hexGroup4Test2");
                compareString = "357b 4f66 3027 2a27 7c37 6f43 204d 4768 4b35 3275";
                shareEdit.putString("hexGroup4Test1EndTime",strDate);
                shareEdit.putString("hexGroup4Test2StartTime",strDate);
                if(test1String.equalsIgnoreCase(compareString))
                    shareEdit.putBoolean("test1Result",true);
                else
                    shareEdit.putBoolean("test1Result",false);
            }else if(intentPass.equals("hexGroup2Test1")){
                intent = new Intent(Test1Activity.this,Test2Activity.class);
                intent.putExtra("intentPass","hexGroup2Test2");
                compareString = "2b 70 28 38 27 29 5b 2d 75 52 39 2d 28 25 2f 36 3f 45 55 31";
                shareEdit.putString("hexGroup2Test1EndTime",strDate);
                shareEdit.putString("hexGroup2Test2StartTime",strDate);
                if(test1String.equalsIgnoreCase(compareString))
                    shareEdit.putBoolean("test2Result",true);
                else
                    shareEdit.putBoolean("test2Result",false);
            }else if(intentPass.equals("englishTest1")){
                intent = new Intent(Test1Activity.this,Test2Activity.class);
                intent.putExtra("intentPass","englishTest2");
                compareString = "effie keats 92 banish romp poll kerry worse astm qy";
                shareEdit.putString("englishTest1EndTime",strDate);
                shareEdit.putString("englishTest2StartTime",strDate);
                if(test1String.equalsIgnoreCase(compareString))
                    shareEdit.putBoolean("englishTest1Result",true);
                else
                    shareEdit.putBoolean("englishTest1Result",false);
            }else if(intentPass.equals("chineseTest1")){
                intent = new Intent(Test1Activity.this,Test2Activity.class);
                intent.putExtra("intentPass","chineseTest2");
                compareString = "lianxi suiji 1983 shanghai yinyong shenghuo baozheng meihao niurou daojian";
                shareEdit.putString("chineseTest1EndTime",strDate);
                shareEdit.putString("chineseTest2StartTime",strDate);
                if(test1String.equalsIgnoreCase(compareString))
                    shareEdit.putBoolean("chineseTest1Result",true);
                else
                    shareEdit.putBoolean("chineseTest1Result",false);
            }else if(intentPass.equals("base32Test1")){
                intent = new Intent(Test1Activity.this,Test2Activity.class);
                intent.putExtra("intentPass","base32Test2");
                compareString = "p65d wbnp yzes 7mtp gtbp";
                shareEdit.putString("base32Test1EndTime",strDate);
                shareEdit.putString("base32Test2StartTime",strDate);
                if(test1String.equalsIgnoreCase(compareString))
                    shareEdit.putBoolean("base32Test1Result",true);
                else
                    shareEdit.putBoolean("base32Test1Result",false);
            }else
                Toast.makeText(this,"Extra Invalid",Toast.LENGTH_LONG).show();
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else
                Toast.makeText(this,"Intent Null",Toast.LENGTH_LONG).show();
            shareEdit.commit();

        }
    }
}
