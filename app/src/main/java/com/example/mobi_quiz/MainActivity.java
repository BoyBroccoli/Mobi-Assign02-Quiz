package com.example.mobi_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.*;
import android.widget.*;
import android.view.*;
import java.io.*; // File IO from java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // creating array list
    ArrayList<String> deffList = new ArrayList<String>();
    ArrayList<String> termList = new ArrayList<String>();

    // Map<String,String> map = new HashMap<String,String>();// create map

    // Creating Buttons

    private Button btnQFile1;

    private TextView qNameTxtView, qOptionTxtView;

    // booleans
    boolean btnClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // linking btns
        setUpAndLinkBtns();
    }

    // On ClickListeners
    public View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnQFile1:
                    if(!btnClicked){
                        btnClicked = true;
                        // fileIo Method here
                        readQuizFile1();

                        // intent here
                        Intent i = new Intent(MainActivity.this, Activity2.class);
                        startIntentArrayProcess(i);

                        startActivity(i);

                    }
                    break;
                default:
                    btnClicked = false;
                    break;
            } // end switch

        } // end on click
    }; // end listener

    public void startIntentArrayProcess(Intent i){
        // intent here

        i.putStringArrayListExtra("deffList",deffList); // adding deff list to intent
        i.putStringArrayListExtra("termsList",termList); // adding terms list to intnet

    }

    public void setUpAndLinkBtns(){

        // linking resource tags to the code objects
        // Buttons
        btnQFile1 = findViewById(R.id.btnQFile1); // btn for choosing file1
        btnQFile1.setOnClickListener(btnClickListener);

        // TextViews
        // Quiz Name
        qNameTxtView = findViewById(R.id.qNameTxtView);
        //qNameTxtView.setOnClickListener(btnClickListener);

        // Quiz Choice
        qOptionTxtView = findViewById(R.id.qOptionTxtView);
        //qOptionTxtView.setOnClickListener(btnClickListener);

    }

    public void readQuizFile1(){
        String str = null;
        BufferedReader br = null;

        try {
            // putting file into stream
            InputStream is = getResources().openRawResource(R.raw.spellsquiz);

            // putting the stream into a buffer
            br = new BufferedReader(new InputStreamReader(is));

            System.out.println("File in RAW is open");



            while((str = br.readLine()) != null ){
                // reading one row at a time into string delimieter
                // Use parse here and split instead of sout
                // put into deffList and tList,and then hash
                //System.out.println(str);

                // Splitting the String

                    String[] separated = str.split("\\$\\$"); // will contain d1
                    String first = separated[0]; // contain term
                    //System.out.println(first);

                    String second = separated[1]; // will contain definition
                    //System.out.println(second);

                    // populating Array Lists with Tokens
                    termList.add(first);
                    deffList.add(second);

            }

            is.close(); // closing stream
            br.close(); // closing buffer
            System.out.println("File in RAW is Closed");

        }catch (IOException e){
            // catch specificly first
            e.printStackTrace();

        } catch (Exception e){
            // catch genericCould have a routine that closes resource
            e.printStackTrace();
        }
    }
}