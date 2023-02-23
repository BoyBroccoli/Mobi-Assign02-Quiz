package com.example.mobi_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Activity2 extends AppCompatActivity {
    final int NUM_OF_CHOICES = 4; // for button choices

    private TextView quizQuestion;

    // buttons
    private Button term1Btn, term2Btn, term3Btn, term4Btn, nextBtn;


    // Arrays and Hashmap
    ArrayList<String> deffList = new ArrayList<String>();
    ArrayList<String> termList = new ArrayList<String>();
    HashMap<String,String> map = new HashMap<String,String>();// create map
    ArrayList<String> btnAnswerChoices = new ArrayList<>(NUM_OF_CHOICES);

    Button[] btnArray = {term1Btn, term2Btn, term3Btn, term4Btn};




    String currentQ; // to hold current question

    int questionIndex = 0; // to index through deffList

    int answersCorrect = 0; // to hold the num of correct answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // setting up button link
        setUpAndLinkBtns();

        // passing over Intent info and Creating HashMap
        setupIntentAndHashMap();

        // shuffling deff list
        shuffleDeffList(deffList);

        // sets textView to new definition and populates the btns with terms
        newDeffAndNewTerm();


    }

    // On Click Listener for term btns
    public View.OnClickListener termsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()){
                case R.id.btnTerm1:
                    // do something
                    checkBtnAnswer(term1Btn);
                    break;
                case R.id.btnTerm2:
                    //do something
                    checkBtnAnswer(term2Btn);

                    break;
                case R.id.btnTerm3:
                    // do something
                    checkBtnAnswer(term3Btn);

                    break;
                case R.id.btnTerm4:
                    // do something
                    checkBtnAnswer(term4Btn);

                    break;
                case R.id.btnNext:
                    // do something
                    newDeffAndNewTerm();
                    System.out.println(answersCorrect);
                default:
                    //do something
                    break;
            }
        }
    };

    // Establishes the Intent and Populates the HashMap
    public void setupIntentAndHashMap(){

        Intent i = getIntent();

        deffList = i.getStringArrayListExtra("deffList"); // passing deffList
        termList = i.getStringArrayListExtra("termsList"); // passing termList

        createHashMap();
    }

    // Populates the Hashmap
    public void createHashMap(){
        for(int i = 0; i< deffList.size(); i++){
            map.put(deffList.get(i),termList.get(i)); // populating map
        }
    }

    // Checks if button text matches the correct Term
    public void checkBtnAnswer(Button btn){
        if(btn.getText().toString().equals(getCurrentTerm())){
            answersCorrect++;
        }
    }


    // Displays a new Deff into the txtView, populates buttons with 4 new choices.
    public void newDeffAndNewTerm(){
        currentQ = deffList.get(questionIndex);

        // Setting view to current question
        quizQuestion.setText(getCurrentDeff());

        createBtnChoices();

        // setting text for buttons
//        for(int i = 0; i < btnArray.length; i++){
//            btnArray[i].setText("Hello World");
////            System.out.println(btnAnswerChoices.get(i));
//        }

        // had for loop to add to text, but kept saying btn was NULL
        term1Btn.setText(btnAnswerChoices.get(0));
        term2Btn.setText(btnAnswerChoices.get(1));
        term3Btn.setText(btnAnswerChoices.get(2));
        term4Btn.setText(btnAnswerChoices.get(3));


        questionIndex++;
    }


    // Shuffles the Deff List
    public void shuffleDeffList(ArrayList<String> deffList){
        Collections.shuffle(deffList);
    }


    // Remove the already shown Deff from the arrayList
    public void rmvAnsQuestion(){

    }

    // Gets the correct term based off current deff
    public String getCurrentTerm(){
        return map.get(currentQ);
    }


    // Returns the current Deff
    public String getCurrentDeff(){
        return currentQ;
    }


    // Populates the 4 button choices with 4 unique terms
    public void createBtnChoices(){

        btnAnswerChoices= new ArrayList<>(NUM_OF_CHOICES); // creates a new list each time it is called
        Random rand = new Random();
        int randoIndex = rand.nextInt(termList.size()); // generates a random num to index terms

        // adding the current correct answer from map to the choices
        btnAnswerChoices.add(getCurrentTerm());

        // populating btnAnswerChoices with random options
        for(int i = 0; i<(NUM_OF_CHOICES -1); i++){

            while(btnAnswerChoices.contains(termList.get(randoIndex))){
                randoIndex = rand.nextInt(termList.size());
            }
            btnAnswerChoices.add(termList.get(randoIndex));

            // System.out.println(btnAnswerChoices);
        }

        // now shuffling the order
        Collections.shuffle(btnAnswerChoices);

    }

    // Sets up the java and xml for Buttons and Views
    public void setUpAndLinkBtns(){

        // adding current question textView
        quizQuestion = findViewById(R.id.currentQTxtView);

        // buttons
        term1Btn = findViewById(R.id.btnTerm1);
        term1Btn.setOnClickListener(termsClickListener);

        term2Btn = findViewById(R.id.btnTerm2);
        term2Btn.setOnClickListener(termsClickListener);

        term3Btn = findViewById(R.id.btnTerm3);
        term3Btn.setOnClickListener(termsClickListener);

        term4Btn = findViewById(R.id.btnTerm4);
        term4Btn.setOnClickListener(termsClickListener);

        nextBtn = findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(termsClickListener);
    }
}