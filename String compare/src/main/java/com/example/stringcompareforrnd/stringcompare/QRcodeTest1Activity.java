package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Garfield_2 on 2014/8/25.
 */
public class QRcodeTest1Activity extends Activity implements View.OnClickListener {
    private Button scan,yesBtn,noBtn;
    private TextView message,resultTV;
    private SharedPreferences sharePrefDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_test1);

        scan = (Button)findViewById(R.id.qrCodeScanBtn);
        yesBtn = (Button)findViewById(R.id.qrCodeTest1YesBtn);
        noBtn = (Button)findViewById(R.id.qrCodeTest1NoBtn);
        sharePrefDate=getSharedPreferences("sharePref",0);
        message = (TextView)findViewById(R.id.qrCodeTest1TV);
        resultTV = (TextView)findViewById(R.id.qrCodeTest1Result);

        showContent("notShow");
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(QRcodeTest1Activity.this);
                scanIntegrator.initiateScan();
                showContent("notShow");
            }
        });
        yesBtn.setOnClickListener(this);
        noBtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                IntentResult scanResult = IntentIntegrator.parseActivityResult(
                        requestCode, resultCode, data);
                if (scanResult == null) {
                    resultTV.setText("Scan Cancelled");
                    return;
                }
                final String result = scanResult.getContents(); // Your result

                if (result != null) {
                    resultTV.setText(result);
                    showContent("show");
                }
                break;
            default:
        }
    }

    private void showContent(String s){
        if(s.equals("show")){
            yesBtn.setVisibility(View.VISIBLE);
            noBtn.setVisibility(View.VISIBLE);
            message.setVisibility(View.VISIBLE);
        }else{
            yesBtn.setVisibility(View.GONE);
            noBtn.setVisibility(View.GONE);
            message.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed(){


    }

    @Override
    public void onClick(View v) {
        if(v == yesBtn || v == noBtn){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            if(v == yesBtn)
                shareEdit.putBoolean("qrCodeTest1Result",true);
            else
                shareEdit.putBoolean("qrCodeTest1Result",false);
            shareEdit.putBoolean("isQRcodeDone",true);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            shareEdit.putString("qrCodeTest1EndTime",strDate);
            shareEdit.commit();
            Intent startIntent = new Intent(QRcodeTest1Activity.this,MainMenuActivity.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(startIntent);
        }
    }
}
