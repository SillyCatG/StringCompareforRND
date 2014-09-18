package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by Garfield_2 on 2014/7/10.
 *
 * this class is about to select the option of the test
 * and it able to click admin option to manage the test result
 */
public class MainMenuActivity extends Activity implements View.OnClickListener {
    private Button hexButton,base32Button,wordButton,wavButton,qrCodeButton,admin;
    private SharedPreferences sharePrefDate;
    private Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        hexButton = (Button)findViewById(R.id.hexadecimalButton);
        base32Button = (Button)findViewById(R.id.base32Button);
        wordButton = (Button)findViewById(R.id.wordButton);
        wavButton = (Button)findViewById(R.id.wavButton);
        qrCodeButton = (Button)findViewById(R.id.qrCodeButton);
        admin = (Button)findViewById(R.id.mainMenuAdmin);
        sharePrefDate=getSharedPreferences("sharePref",0);
        /*
        "update()" is the method to manage the buttons are enable or not
        button will be enable once the test was done
         */
        update();
        hexButton.setOnClickListener(this);
        base32Button.setOnClickListener(this);
        wordButton.setOnClickListener(this);
        wavButton.setOnClickListener(this);
        qrCodeButton.setOnClickListener(this);
        admin.setOnClickListener(this);

    }

    private void loginSuccess(){
        /*
        if admin login success it will create a temp name as
        "Current Result" is able the let us read the test process
        and jump into the result intent
         */
        createResult("Current Result");
        Intent resultIntent = new Intent(MainMenuActivity.this, ResultActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(resultIntent);
    }

    private void update(){
        // manage the button
        if(sharePrefDate.getBoolean("isHexDone",false))
            hexButton.setEnabled(false);
        else
            hexButton.setEnabled(true);
        if(sharePrefDate.getBoolean("isBase32Done",false))
            base32Button.setEnabled(false);
        else
            base32Button.setEnabled(true);
        if(sharePrefDate.getBoolean("isWordDone",false))
            wordButton.setEnabled(false);
        else
            wordButton.setEnabled(true);
        if(sharePrefDate.getBoolean("isWavDone",false))
            wavButton.setEnabled(false);
        else
            wavButton.setEnabled(true);
        if(sharePrefDate.getBoolean("isQRcodeDone",false))
            qrCodeButton.setEnabled(false);
        else
            qrCodeButton.setEnabled(true);
        // display the dialog once all test complied and create tester unique id
        if(sharePrefDate.getBoolean("isHexDone",false)
                && sharePrefDate.getBoolean("isBase32Done",false)
                && sharePrefDate.getBoolean("isWordDone",false)
                && sharePrefDate.getBoolean("isWavDone",false)
                && sharePrefDate.getBoolean("isQRcodeDone",false)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);    // create message dialog
            builder.setMessage("You Had Done All The Tests,\n"+
            "Thank You For Your Cooperation").setTitle("Done");
            // find out all the file in this application as a file list
            String[] fileLists = getApplicationContext().fileList();
            String newName;
            boolean validName=false;
            // if the file list empty, create file "S1"
            if(fileLists.length == 0) {
                newName = "S1";
                createResult(newName);
            /*
            if the file list not empty
            search the list and create the unique id
              */
            }else{
                int x=1;
                newName="S"+x;
                /*
                while the valid file name is false
                keep adding 1 into the X value
                until that is the unique id
                and set the valid file name as true
                 */
                while(!validName) {
                    if (Arrays.asList(fileLists).contains(newName)){
                        ++x;
                        newName = "S"+x;
                    }else
                        validName=true;
                }
                /*
                check if the file had been created at once already
                if the file not been created, create it and record
                as file created into the shared preference
                 */
                if(!sharePrefDate.getBoolean("isFileCreate",false)) {
                    createResult(newName);
                    SharedPreferences.Editor shareEdit = sharePrefDate.edit();
                    shareEdit.putBoolean("isFileCreate",true);
                    shareEdit.commit();
                }
            }



            /*
                                                    Hints:

            To remove specific values: SharedPreferences.Editor.remove() followed by a commit()

            To remove them all SharedPreferences.Editor.clear() followed by a commit()

             */

            //build a dialog to show message and dismiss after 5 sec
            final AlertDialog dialog = builder.create();
            dialog.show();                        // show the dialog
            Thread close = new Thread() {        // create a thread
                @Override
                public void run() {
                    try {
                        sleep(5000);            // wait 5 second
                        dialog.dismiss();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            close.start();
        }
    }

    /*
    this method is taking the file name and store into the application
    also it will be recall all the tests result and time into the file name
    as file content
     */
    private void createResult(String name){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
        String strDate = sdf.format(c.getTime());
        // recall all the test result as string
        String result="Tester Number: "+name+"\r\n"
                +"Start Time: "+sharePrefDate.getString("StartTime","")+"\r\n"
                +"Finish Time: "+strDate+"\r\n"
                +"Number of Mistake: "+getFalseTest()+"\r\n\r\n"
                +"Hex group of 4 test 1 result: "+sharePrefDate.getBoolean("test1Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup4Test1StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup4Test1EndTime","")+"\r\n"
                +"Hex group of 4 test 2 result: "+sharePrefDate.getBoolean("test3Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup4Test2StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup4Test2EndTime","")+"\r\n"
                 +"Hex group of 4 test 3 result: "+sharePrefDate.getBoolean("test5Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup4Test3StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup4Test3EndTime","")+"\r\n"
                +"Hex group of 4 test 4 result: "+sharePrefDate.getBoolean("test7Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup4Test4StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup4Test4EndTime","")+"\r\n"
                +"Hex group of 4 test 5 result: "+sharePrefDate.getBoolean("test9Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup4Test5StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup4Test5EndTime","")+"\r\n"
                +"Hex group of 2 test 1 result: "+sharePrefDate.getBoolean("test2Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup2Test1StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup2Test1EndTime","")+"\r\n"
                +"Hex group of 2 test 2 result: "+sharePrefDate.getBoolean("test4Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup2Test2StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup2Test2EndTime","")+"\r\n"
                +"Hex group of 2 test 3 result: "+sharePrefDate.getBoolean("test6Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup2Test3StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup2Test3EndTime","")+"\r\n"
                +"Hex group of 2 test 4 result: "+sharePrefDate.getBoolean("test8Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup2Test4StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup2Test4EndTime","")+"\r\n"
                +"Hex group of 2 test 5 result: "+sharePrefDate.getBoolean("test10Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("hexGroup2Test5StartTime","")+"\t - \t"
                +sharePrefDate.getString("hexGroup2Test5EndTime","")+"\r\n"
                +"base 32 test 1 result: "+sharePrefDate.getBoolean("base32Test1Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("base32Test1StartTime","")+"\t - \t"
                +sharePrefDate.getString("base32Test1EndTime","")+"\r\n"
                +"base 32 test 2 result: "+sharePrefDate.getBoolean("base32Test2Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("base32Test2StartTime","")+"\t - \t"
                +sharePrefDate.getString("base32Test2EndTime","")+"\r\n"
                +"base 32 test 3 result: "+sharePrefDate.getBoolean("base32Test3Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("base32Test3StartTime","")+"\t - \t"
                +sharePrefDate.getString("base32Test3EndTime","")+"\r\n"
                +"base 32 test 4 result: "+sharePrefDate.getBoolean("base32Test4Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("base32Test4StartTime","")+"\t - \t"
                +sharePrefDate.getString("base32Test4EndTime","")+"\r\n"
                +"base 32 test 5 result: "+sharePrefDate.getBoolean("base32Test5Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("base32Test5StartTime","")+"\t - \t"
                +sharePrefDate.getString("base32Test5EndTime","")+"\r\n"
                +"english test 1 result: "+sharePrefDate.getBoolean("englishTest1Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("englishTest1StartTime","")+"\t - \t"
                +sharePrefDate.getString("englishTest1EndTime","")+"\r\n"
                +"english test 2 result: "+sharePrefDate.getBoolean("englishTest2Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("englishTest2StartTime","")+"\t - \t"
                +sharePrefDate.getString("englishTest2EndTime","")+"\r\n"
                +"english test 3 result: "+sharePrefDate.getBoolean("englishTest3Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("englishTest3StartTime","")+"\t - \t"
                +sharePrefDate.getString("englishTest3EndTime","")+"\r\n"
                +"english test 4 result: "+sharePrefDate.getBoolean("englishTest4Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("englishTest4StartTime","")+"\t - \t"
                +sharePrefDate.getString("englishTest4EndTime","")+"\r\n"
                +"chinese test 1 result: "+sharePrefDate.getBoolean("chineseTest1Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("chineseTest1StartTime","")+"\t - \t"
                +sharePrefDate.getString("chineseTest1EndTime","")+"\r\n"
                +"chinese test 2 result: "+sharePrefDate.getBoolean("chineseTest2Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("chineseTest2StartTime","")+"\t - \t"
                +sharePrefDate.getString("chineseTest2EndTime","")+"\r\n"
                +"chinese test 3 result: "+sharePrefDate.getBoolean("chineseTest3Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("chineseTest3StartTime","")+"\t - \t"
                +sharePrefDate.getString("chineseTest3EndTime","")+"\r\n"
                +"chinese test 4 result: "+sharePrefDate.getBoolean("chineseTest4Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("chineseTest4StartTime","")+"\t - \t"
                +sharePrefDate.getString("chineseTest4EndTime","")+"\r\n"
                +"wavatar test 1 result: "+sharePrefDate.getBoolean("wavTest1Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("wavatarTest1StartTime","")+"\t - \t"
                +sharePrefDate.getString("wavatarTest1EndTime","")+"\r\n"
                +"wavatar test 2 result: "+sharePrefDate.getBoolean("wavTest2Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("wavatarTest2StartTime","")+"\t - \t"
                +sharePrefDate.getString("wavatarTest2EndTime","")+"\r\n"
                +"wavatar test 3 result: "+sharePrefDate.getBoolean("wavTest3Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("wavatarTest3StartTime","")+"\t - \t"
                +sharePrefDate.getString("wavatarTest3EndTime", "")+"\r\n"
                +"wavatar test 4 result: "+sharePrefDate.getBoolean("wavTest4Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("wavatarTest4StartTime","")+"\t - \t"
                +sharePrefDate.getString("wavatarTest4EndTime", "")+"\r\n"
                +"QR Code test 1 result: "+sharePrefDate.getBoolean("qrCodeTest1Result",false)
                +"\t\t\t\t"+sharePrefDate.getString("qrCodeTest1StartTime","")+"\t - \t"
                +sharePrefDate.getString("qrCodeTest1EndTime", "")+"\r\n";
        // create file and input file content
        FileOutputStream fos;
        try {
            fos= openFileOutput(name, Context.MODE_PRIVATE);
            fos.write(result.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    this method is for counting the falsies in the test
    and return to int
     */
    private int getFalseTest(){
        int mistake = 0;
        if(!sharePrefDate.getBoolean("test1Result",false))++mistake;
        if(!sharePrefDate.getBoolean("test3Result",false))++mistake;
        if(!sharePrefDate.getBoolean("test5Result",false))++mistake;
        if(!sharePrefDate.getBoolean("test7Result",false))++mistake;
        if(!sharePrefDate.getBoolean("test2Result",false))++mistake;
        if(!sharePrefDate.getBoolean("test4Result",false))++mistake;
        if(!sharePrefDate.getBoolean("test6Result",false))++mistake;
        if(!sharePrefDate.getBoolean("test8Result",false))++mistake;
        if(!sharePrefDate.getBoolean("test9Result",false))++mistake;
        if(!sharePrefDate.getBoolean("test10Result",false))++mistake;
        if(!sharePrefDate.getBoolean("base32Test1Result",false))++mistake;
        if(!sharePrefDate.getBoolean("base32Test2Result",false))++mistake;
        if(!sharePrefDate.getBoolean("base32Test3Result",false))++mistake;
        if(!sharePrefDate.getBoolean("base32Test4Result",false))++mistake;
        if(!sharePrefDate.getBoolean("base32Test5Result",false))++mistake;
        if(!sharePrefDate.getBoolean("englishTest1Result",false))++mistake;
        if(!sharePrefDate.getBoolean("englishTest2Result",false))++mistake;
        if(!sharePrefDate.getBoolean("englishTest3Result",false))++mistake;
        if(!sharePrefDate.getBoolean("englishTest4Result",false))++mistake;
        if(!sharePrefDate.getBoolean("chineseTest1Result",false))++mistake;
        if(!sharePrefDate.getBoolean("chineseTest2Result",false))++mistake;
        if(!sharePrefDate.getBoolean("chineseTest3Result",false))++mistake;
        if(!sharePrefDate.getBoolean("chineseTest4Result",false))++mistake;
        if(!sharePrefDate.getBoolean("wavTest1Result",false))++mistake;
        if(!sharePrefDate.getBoolean("wavTest2Result",false))++mistake;
        if(!sharePrefDate.getBoolean("wavTest3Result",false))++mistake;
        if(!sharePrefDate.getBoolean("wavTest4Result",false))++mistake;
        if(!sharePrefDate.getBoolean("qrCodeTest1Result",false))++mistake;
        return mistake;
    }

    @Override
    public void onBackPressed(){


    }

    @Override
    public void onClick(View v) {
        if(v == hexButton){
            Intent hexIntent = new Intent(MainMenuActivity.this, SubMenuActivity.class);
            hexIntent.putExtra("intentPass","hex");
            hexIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(hexIntent);
        }else if(v == base32Button){
            Intent base32Intent = new Intent(MainMenuActivity.this, Test1Activity.class);
            base32Intent.putExtra("intentPass","base32Test1");
            base32Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(base32Intent);
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            shareEdit.putString("base32Test1StartTime", strDate);
            shareEdit.commit();
        }else if(v == wordButton){
            Intent wordIntent = new Intent(MainMenuActivity.this, SubMenuActivity.class);
            wordIntent.putExtra("intentPass","word");
            wordIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(wordIntent);
        }else if(v == wavButton){
            Intent wavIntent = new Intent(MainMenuActivity.this, WavatarsTest1.class);
            wavIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(wavIntent);
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            shareEdit.putString("wavatarTest1StartTime", strDate);
            shareEdit.commit();
        }else if(v == qrCodeButton){
            Intent qrCodeIntent = new Intent(MainMenuActivity.this, QRcodeTest1Activity.class);
            qrCodeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(qrCodeIntent);
            SharedPreferences.Editor shareEdit = sharePrefDate.edit();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            String strDate = sdf.format(c.getTime());
            shareEdit.putString("qrCodeTest1StartTime", strDate);
            shareEdit.commit();
        }else if(v == admin){
            final Dialog login = new Dialog(context);
            login.setContentView(R.layout.login);
            // set the dialog size
            login.getWindow().setLayout(1200,800);
            // set dialog title message
            login.setTitle("Please Login With Your Password");
            final EditText loginPwd = (EditText)login.findViewById(R.id.loginPwdET);
            Button loginBtn = (Button)login.findViewById(R.id.loginBtn);
            loginBtn.setText("Login");
            loginBtn.setTextColor(Color.parseColor("#00FF0D"));
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        /*
                        the default password is 0000
                        i did not implement too much on the password part
                        because the password i just trying to block of the
                        tester may want to read the result of the test or
                        miss click on the button
                         */
                    String pwd = sharePrefDate.getString("LoginPwd","0000");
                    if(pwd.equals(loginPwd.getText().toString())){
                        login.dismiss();
                        loginSuccess();
                    }else {
                        login.dismiss();
                        Toast.makeText(context,"Input Password Incorrect",Toast.LENGTH_LONG).show();
                    }
                }
            });
            login.show();
        }
    }
}
