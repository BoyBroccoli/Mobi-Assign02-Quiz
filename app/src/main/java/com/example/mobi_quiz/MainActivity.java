package com.example.mobi_quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.widget.*;
import android.view.*;
import java.io.*; // File IO from java

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

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
                    }
                    break;
                default:
                    btnClicked = false;
                    break;
            } // end switch

        } // end on click
    }; // end listener

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
            InputStream is = getResources().openRawResource(R.raw.delimiters);

            // putting the stream into a buffer
            br = new BufferedReader(new InputStreamReader(is));

            System.out.println("File in RAW is open");

            while((str = br.readLine()) != null){
                // reading one row at a time into string delimieter
                // Use parse here and split instead of sout
                // put into deffList and tList,and then hash
                System.out.println(str);

                System.out.println("File in RAW is Closed");
            }
            is.close();
        }catch (IOException e){
            // catch specificly first
            e.printStackTrace();

        } catch (Exception e){
            // catch genericCould have a routine that closes resource
            e.printStackTrace();
        }
    }
}