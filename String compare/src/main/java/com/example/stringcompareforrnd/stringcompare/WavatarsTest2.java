package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Garfield_2 on 2014/7/13.
 */
public class WavatarsTest2 extends Activity implements View.OnClickListener {
    private Button submit,yesBtn,noBtn;
    private ImageView image;
    private EditText input;
    private String message;
    private ImageLoader imageLoader;
    private TextView textShow;
    private Context context = this;
    private SharedPreferences sharePrefDate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wavatars_layout);

        submit = (Button)findViewById(R.id.wavSubmitButton);
        yesBtn = (Button)findViewById(R.id.wavYesBtn);
        noBtn = (Button)findViewById(R.id.wavNoBtn);
        textShow = (TextView)findViewById(R.id.wavTextView);
        image = (ImageView)findViewById(R.id.wavImageView);
        input = (EditText)findViewById(R.id.wavET);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        sharePrefDate=getSharedPreferences("sharePref",0);

        input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    submit.callOnClick();
                    return true;
                }
                return false;
            }
        });
        showView("notShow");
        submit.setOnClickListener(this);
        yesBtn.setOnClickListener(this);
        noBtn.setOnClickListener(this);
    }

    private void showView(String s) {
        if(s.equals("show")){
            yesBtn.setVisibility(View.VISIBLE);
            noBtn.setVisibility(View.VISIBLE);
            textShow.setVisibility(View.VISIBLE);
        }else{
            yesBtn.setVisibility(View.GONE);
            noBtn.setVisibility(View.GONE);
            textShow.setVisibility(View.GONE);
        }
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest)
                hexString.append(Integer.toHexString(0xFF & aMessageDigest));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onBackPressed(){


    }


    @Override
    public void onClick(View v) {
        if(v == yesBtn || v == noBtn){
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            shareEdit.putBoolean("isWavDone", true);
            String s = "elephant";
            if((s.equals(input.getText().toString()) && v == yesBtn)
                    || (!s.equals(input.getText().toString()) &&  v == noBtn))
                shareEdit.putBoolean("wavTest2Result",true);
            else
                shareEdit.putBoolean("wavTest2Result",false);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            shareEdit.putString("wavatarTest2EndTime",strDate);
            shareEdit.putString("wavatarTest3StartTime",strDate);
            shareEdit.commit();
            Intent startIntent = new Intent(WavatarsTest2.this,WavatarsTest3.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(startIntent);
        }
        if(v == submit){
            if(!input.getText().toString().equals("")){
                message=md5(input.getText().toString());
                String s = "http://www.gravatar.com/avatar.php?gravatar_id="+message+"&r=PG&s=100&default=identicon";
                imageLoader.displayImage(s, image);
                showView("show");
            }else{
                Toast.makeText(context,"You Have Not Enter Any Text",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
