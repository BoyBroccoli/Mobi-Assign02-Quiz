package com.example.mobi_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity2 extends AppCompatActivity {
    private TextView quizQuestion;

    // buttons
    private Button btnCorrectTerm, term2Btn, term3Btn, term4Btn, nextBtn;

    HashMap<String,String> map = new HashMap<String,String>();// create map

    ArrayList<String> deffList = new ArrayList<String>();
    ArrayList<String> termList = new ArrayList<String>();

    int questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // setting up button link
        setUpAndLinkBtns();



        Intent intent = getIntent();

        deffList = intent.getStringArrayListExtra("deffList"); // passing deffList
        termList = intent.getStringArrayListExtra("termsList"); // passing termList

        // adding ArrayLists to hashmap

        for(int i = 0; i< deffList.size(); i++){
            map.put(deffList.get(i),termList.get(i)); // populating map
        }


            String currentQ = deffList.get(questionIndex);
            quizQuestion.setText(currentQ);

    }

    // On Click Listenr for term btns
    public View.OnClickListener termsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()){
                case R.id.btnCorrectTerm:
                    // do something
                    break;
                case R.id.btnWrongTerm1:
                    //do something
                    break;
                case R.id.btnWrongTerm2:
                    // do something
                    break;
                case R.id.btnWrongTerm3:
                    // do something
                    break;
                case R.id.btnNext:
                    // do something
                    questionIndex++;
                    String currentQ = deffList.get(questionIndex);
                    quizQuestion.setText(currentQ);
                default:
                    //do something
                    break;
            }
        }
    };



    public void setUpAndLinkBtns(){

        // adding current question textView
        quizQuestion = findViewById(R.id.currentQTxtView);

        // buttons
        btnCorrectTerm = findViewById(R.id.btnCorrectTerm);
        btnCorrectTerm.setOnClickListener(termsClickListener);

        term2Btn = findViewById(R.id.btnWrongTerm1);
        term2Btn.setOnClickListener(termsClickListener);

        term3Btn = findViewById(R.id.btnWrongTerm2);
        term3Btn.setOnClickListener(termsClickListener);

        term4Btn = findViewById(R.id.btnWrongTerm3);
        term4Btn.setOnClickListener(termsClickListener);

        nextBtn = findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(termsClickListener);
    }
}