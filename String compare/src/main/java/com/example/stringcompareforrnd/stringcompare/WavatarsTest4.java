package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


/**
 * Created by Garfield_2 on 2014/8/11.
 *
 * this class is let user to change the photo match with the key
 * system will be record the result
 */
public class WavatarsTest4 extends Activity implements View.OnClickListener {
    private ImageButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    private SharedPreferences sharePrefDate;
    private Button submitBtn;
    private Object[] ones,twos,threes,fours,fives,sixs,sevens,eights,nights;
    private int index1,index2,index3,index4,index5,index6,index7,index8,index9=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wav_test3);

        btn1 = (ImageButton)findViewById(R.id.wavTest3ImageBtn1);
        btn2 = (ImageButton)findViewById(R.id.wavTest3ImageBtn2);
        btn3 = (ImageButton)findViewById(R.id.wavTest3ImageBtn3);
        btn4 = (ImageButton)findViewById(R.id.wavTest3ImageBtn4);
        btn5 = (ImageButton)findViewById(R.id.wavTest3ImageBtn5);
        btn6 = (ImageButton)findViewById(R.id.wavTest3ImageBtn6);
        btn7 = (ImageButton)findViewById(R.id.wavTest3ImageBtn7);
        btn8 = (ImageButton)findViewById(R.id.wavTest3ImageBtn8);
        btn9 = (ImageButton)findViewById(R.id.wavTest3ImageBtn9);
        submitBtn = (Button)findViewById(R.id.wavTest3SubmitBtn);
        sharePrefDate=getSharedPreferences("sharePref",0);
        ones = new Object[]{R.drawable.one1, R.drawable.two1,R.drawable.three1,
        R.drawable.four1,R.drawable.five1};
        twos = new Object[]{R.drawable.one2, R.drawable.two2,R.drawable.three2,
                R.drawable.four2,R.drawable.five2};
        threes = new Object[]{R.drawable.one3, R.drawable.two3,R.drawable.three3,
                R.drawable.four3,R.drawable.five3};
        fours = new Object[]{R.drawable.one4,R.drawable.two4,R.drawable.three4,
                R.drawable.four4,R.drawable.five4};
        fives = new Object[]{R.drawable.one5,R.drawable.two5,R.drawable.three5,
                R.drawable.four5,R.drawable.five5};
        sixs = new Object[]{R.drawable.one6,R.drawable.two6,R.drawable.three6,
                R.drawable.four6,R.drawable.five6};
        sevens = new Object[]{R.drawable.one7,R.drawable.two7,R.drawable.three7,
                R.drawable.four7,R.drawable.five7};
        eights = new Object[]{R.drawable.one8,R.drawable.two8,R.drawable.three8,
                R.drawable.four8,R.drawable.five8};
        nights = new Object[]{R.drawable.one9,R.drawable.two9,R.drawable.three9,
                R.drawable.four9,R.drawable.five9};
        btn1.setBackgroundResource((Integer) randomKey(ones));
        btn2.setBackgroundResource((Integer) randomKey(twos));
        btn3.setBackgroundResource((Integer) randomKey(threes));
        btn4.setBackgroundResource((Integer) randomKey(fours));
        btn5.setBackgroundResource((Integer) randomKey(fives));
        btn6.setBackgroundResource((Integer) randomKey(sixs));
        btn7.setBackgroundResource((Integer) randomKey(sevens));
        btn8.setBackgroundResource((Integer) randomKey(eights));
        btn9.setBackgroundResource((Integer) randomKey(nights));

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    private Object randomKey(Object[] array){
        int randomNum = new Random().nextInt(5);
        if(array == ones){
            index1 = randomNum;
            return ones[index1];
        }else if(array == twos){
            index2 = randomNum;
            return twos[index2];
        }else if(array == threes){
            index3 = randomNum;
            return threes[index3];
        }else if(array == fours){
            index4 = randomNum;
            return fours[index4];
        }else if(array == fives){
            index5 = randomNum;
            return fives[index5];
        }else if(array == sixs){
            index6 = randomNum;
            return sixs[index6];
        }else if(array == sevens){
            index7 = randomNum;
            return sevens[index7];
        }else if(array == eights){
            index8 = randomNum;
            return eights[index8];
        }else if(array == nights){
            index9 = randomNum;
            return nights[index9];
        }
        else
        return null;
    }

    @Override
    public void onBackPressed(){


    }

    @Override
    public void onClick(View v) {
        if(v instanceof ImageButton){
            if(v == btn1){
                ++index1;
                if(index1>4)index1=0;
                btn1.setBackgroundResource((Integer) ones[index1]);
            }else if(v == btn2){
                ++index2;
                if(index2>4)index2=0;
                btn2.setBackgroundResource((Integer) twos[index2]);
            }else if(v == btn3){
                ++index3;
                if(index3>4)index3=0;
                btn3.setBackgroundResource((Integer) threes[index3]);
            }else if(v == btn4){
                ++index4;
                if(index4>4)index4=0;
                btn4.setBackgroundResource((Integer) fours[index4]);
            }else if(v == btn5){
                ++index5;
                if(index5>4)index5=0;
                btn5.setBackgroundResource((Integer) fives[index5]);
            }else if(v == btn6){
                ++index6;
                if(index6>4)index6=0;
                btn6.setBackgroundResource((Integer) sixs[index6]);
            }else if(v == btn7){
                ++index7;
                if(index7>4)index7=0;
                btn7.setBackgroundResource((Integer) sevens[index7]);
            }else if(v == btn8){
                ++index8;
                if(index8>4)index8=0;
                btn8.setBackgroundResource((Integer) eights[index8]);
            }else if(v == btn9){
                ++index9;
                if(index9>4)index9=0;
                btn9.setBackgroundResource((Integer) nights[index9]);
            }
        }
        if(v == submitBtn){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            if(index1 == 1 && index2 == 1 && index3 == 1 && index4 == 1
                    && index5 == 1 && index6 == 1 && index7 == 1
                    && index8 == 1 && index9 == 1)
                shareEdit.putBoolean("wavTest4Result",true);
            else
                shareEdit.putBoolean("wavTest4Result",false);
            shareEdit.putBoolean("isWavDone", true);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            shareEdit.putString("wavatarTest4EndTime",strDate);
            shareEdit.commit();
            Intent startIntent = new Intent(WavatarsTest4.this,MainMenuActivity.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(startIntent);
        }
    }
}
