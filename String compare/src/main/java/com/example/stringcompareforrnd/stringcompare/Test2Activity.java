package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Garfield_2 on 2014/6/14.
 *
 * this class its let user to choose one of the giving key
 * and submit to the test, system will record the result of the test
 */
public class Test2Activity extends Activity implements View.OnClickListener {
    private Button submitBtn;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;
    private SharedPreferences sharePrefDate;
    private String intentPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2_layout);

        submitBtn = (Button)findViewById(R.id.hexTest3SubmitBtn);
        radioButton1 = (RadioButton)findViewById(R.id.hexTest3RadioButton1);
        radioButton2 = (RadioButton)findViewById(R.id.hexTest3RadioButton2);
        radioButton3 = (RadioButton)findViewById(R.id.hexTest3RadioButton3);
        radioButton4 = (RadioButton)findViewById(R.id.hexTest3RadioButton4);
        radioButton5 = (RadioButton)findViewById(R.id.hexTest3RadioButton5);
        Bundle extras = getIntent().getExtras();
        sharePrefDate=getSharedPreferences("sharePref",0);
        if(extras != null)
            intentPass = extras.getString("intentPass");
        else
            Toast.makeText(this, "Put Extra Null", Toast.LENGTH_LONG).show();
        submitBtn.setOnClickListener(this);
        if(intentPass.equals("hexGroup2Test2")){
            radioButton1.setText("4f 75 7b 4f 72 31 4e 70 47 7c 33 32 48 2e 54 29 2b 5a 69 24");
            radioButton2.setText("4f 75 7d 4f 72 31 4e 70 4a 7c 33 32 48 2e 59 29 2b 5a 69 24");
            radioButton3.setText("4f 75 7d 4f 72 3T 4e 70 4a 7c 33 32 48 2e 56 29 2b 5a 69 24");
            radioButton4.setText("4f 7a 7d 4f 72 31 4e 70 4a 7c 33 32 48 2f 59 29 2b 5a 69 24");
            radioButton5.setText("4f 75 7d 4f 72 31 4e 78 4a 7c 33 32 48 2e 59 29 2d 5a 69 24");
        }else if(intentPass.equals("englishTest2")){
            radioButton1.setText("plane dogma aye ug marco ki tonic google ivory kapok");
            radioButton2.setText("plane dogma aye ug marco kiki tonic gogo ivory kapok");
            radioButton3.setText("plane dogma aye ug marco ki tonic gogo ivory kapok");
            radioButton4.setText("plane dogma are ug marco ki tonic gogo ivory kapok");
            radioButton5.setText("plane dogma aye ug marco ki tonix gogo ivory kapok");
        }else if(intentPass.equals("chineseTest2")){
            radioButton1.setText("jiemu shazi zhongwen wenzi jiqi shuohuang lanse keai yifu dianqi");
            radioButton2.setText("jiemo shazi zhongwen wenzhi jiqi shuohuang lanse keai yifu dianqi");
            radioButton3.setText("jiemu shazhi zhongwen wenzi jiqi suohuang lanse keai yifu dianqi");
            radioButton4.setText("jimu shazi zhongwen wenzi jiqi shuohuang lanse keai yifu dianqi");
            radioButton5.setText("jiemu shazi zhongwen wenzhi jiqi shuohuang lanshe keai yifu dianqi");
        }else if(intentPass.equals("base32Test2")){
            radioButton1.setText("zh7t vt4b 3ud3 ahg5 s3u3");
            radioButton2.setText("zh71 vy4b 3ub3 ahg5 53u3");
            radioButton3.setText("zh7t vy4b 3ud3 ahg5 s3u3");
            radioButton4.setText("zh7t vy4b 30g3 ahg5 s3u3");
            radioButton5.setText("zh7t vy4b 3ub3 ahg5 s3u3");
        }
    }

    @Override
    public void onBackPressed(){


    }

    @Override
    public void onClick(View v) {
        if(v == submitBtn){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            Intent intent = null;
            if(intentPass.equals("hexGroup2Test2")){
                if(radioButton2.isChecked())
                    shareEdit.putBoolean("test4Result",true);
                else
                    shareEdit.putBoolean("test4Result",false);
                shareEdit.putString("hexGroup2Test2EndTime",strDate);
                shareEdit.putString("hexGroup2Test3StartTime",strDate);
                intent = new Intent(Test2Activity.this,Test3bActivity.class);
            }else if(intentPass.equals("englishTest2")){
                if(radioButton3.isChecked())
                    shareEdit.putBoolean("englishTest2Result",true);
                else
                    shareEdit.putBoolean("englishTest2Result",false);
                shareEdit.putString("englishTest2EndTime",strDate);
                shareEdit.putString("englishTest3StartTime",strDate);
                intent = new Intent(Test2Activity.this,Test3Activity.class);
                intent.putExtra("intentPass","englishTest3");
            }else if(intentPass.equals("chineseTest2")){
                if(radioButton1.isChecked())
                    shareEdit.putBoolean("chineseTest2Result",true);
                else
                    shareEdit.putBoolean("chineseTest2Result",false);
                shareEdit.putString("chineseTest2EndTime",strDate);
                shareEdit.putString("chineseTest3StartTime",strDate);
                intent = new Intent(Test2Activity.this,Test3Activity.class);
                intent.putExtra("intentPass","chineseTest3");
            }else if(intentPass.equals("base32Test2")){
                if(radioButton5.isChecked())
                    shareEdit.putBoolean("base32Test2Result",true);
                else
                    shareEdit.putBoolean("base32Test2Result",false);
                shareEdit.putString("base32Test2EndTime", strDate);
                shareEdit.putString("base32Test3StartTime", strDate);
                intent = new Intent(Test2Activity.this,Test3Activity.class);
                intent.putExtra("intentPass","base32Test3");
            }else if(intentPass.equals("hexGroup4Test2")){
                if(radioButton4.isChecked())
                    shareEdit.putBoolean("test3Result",true);
                else
                    shareEdit.putBoolean("test3Result",false);
                shareEdit.putString("hexGroup4Test2EndTime",strDate);
                shareEdit.putString("hexGroup4Test3StartTime",strDate);
                intent = new Intent(Test2Activity.this,Test3Activity.class);
                intent.putExtra("intentPass","hexGroup4Test3");
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
