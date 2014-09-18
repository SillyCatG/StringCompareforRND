package com.example.stringcompareforrnd.stringcompare;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Garfield_2 on 2014/8/14.
 */
public class ResultActivity extends Activity{
    private Button restart,loadResult,createFile,delete;
    private TextView resultTV;
    private Spinner testList;
    private SharedPreferences sharePrefDate;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        restart = (Button)findViewById(R.id.restartBtn);
        loadResult = (Button)findViewById(R.id.adminLoadResult);
        createFile = (Button)findViewById(R.id.createFile);
        testList = (Spinner)findViewById(R.id.adminSpinner);
        resultTV = (TextView)findViewById(R.id.adminResultTV);
        delete = (Button)findViewById(R.id.delete);
        sharePrefDate=getSharedPreferences("sharePref",0);

        // go back to start of the application
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        /*
        this button to create file into the internal storage of the device
        getting file name form the spinner and the file content
        create directory in the internal storage
        create the file as the same name of the spinner file name
        input the content into the file
         */
        createFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting the selected file name from the spinner
                String selectFile = String.valueOf(testList.getSelectedItem());
                if(selectFile.equals("Current Result")){
                    Toast.makeText(context,"Selected File Not Allow To Create",Toast.LENGTH_LONG).show();
                    return;
                }
                String value = "";
                FileInputStream fis;
                // search the device directory
                File sdCard = Environment.getExternalStorageDirectory();
                // create folder
                File dir = new File (sdCard.getAbsolutePath() + "/dir1/dir2");
                dir.mkdirs();
                // setting the file name as selected spinner file name and add ".txt"
                String fileName = selectFile+".txt";
                // setup the directory and file name of the file
                File file = new File(dir, fileName);
                FileOutputStream fileOut;
                try {
                    // recall the selected file from the spinner
                    fis = openFileInput(selectFile);
                    // getting the content of the select file
                    byte[] input = new byte[fis.available()];
                    // put it into string value
                    while(fis.read(input) != -1){
                        value += new String(input);
                    }
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // if the file from that directory is not exist
                if(!file.exists()){
                    try {
                        fileOut = new FileOutputStream(file);
                        // putting the string value into the file
                        fileOut.write(value.getBytes());
                        fileOut.close();
                        // show file create success token
                        Toast.makeText(context,"File Created",Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                // if the file from that directory is already exist
                }else{
                    // show file already exist token
                    Toast.makeText(context,"File Exists",Toast.LENGTH_LONG).show();
                }
            }
        });
        //getting the selected file content from the spinner
        loadResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting the selected file name from the spinner
                String selectFile = String.valueOf(testList.getSelectedItem());
                String value = "";
                FileInputStream fis;
                try {
                    fis = openFileInput(selectFile);
                    // getting the file content as byte
                    byte[] input = new byte[fis.available()];
                    // getting the byte as string
                    while(fis.read(input) != -1){
                        value += new String(input);
                    }
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // set text view as string content
                resultTV.setText(value);

            }
        });
        // this method is for loading up the file list into the spinner
        getFileName();
        /*
        this button is to delete the file which is selected
        the dialog will show up when you click the button
        the dialog will ask you to input the password to
        confirm the delete file option
         */
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create dialog in context
                final Dialog login = new Dialog(context);
                login.setContentView(R.layout.login);
                // setting the size of the dialog windows
                login.getWindow().setLayout(1200,800);
                login.setTitle("Are You Want To Delete This Result?");
                final EditText loginPwd = (EditText)login.findViewById(R.id.loginPwdET);
                Button loginBtn = (Button)login.findViewById(R.id.loginBtn);
                loginBtn.setText("Delete");
                loginBtn.setTextColor(Color.parseColor("#00FF0D"));
                loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pwd = sharePrefDate.getString("LoginPwd","0000");
                        if(pwd.equals(loginPwd.getText().toString())){
                            // getting the selected file from the spinner
                            String selectFile = String.valueOf(testList.getSelectedItem());
                            // delete file
                            deleteFile(selectFile);
                            // renew the file in the spinner
                            getFileName();
                            login.dismiss();
                        }else {
                            login.dismiss();
                            Toast.makeText(context, "Input Password Incorrect", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                login.show();
            }
        });

    }
    // to getting the file list into the spinner
    private void getFileName(){
        // getting all the saved file in this application as list
        String[] fileList = getApplicationContext().fileList();
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, fileList);
        ArrayAdapter<String> fileAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        testList.setAdapter(fileAdapter);
    }

}
