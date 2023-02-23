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
    Button[] btnObjects = {term1Btn, term2Btn, term3Btn, term4Btn};



    String currentQ; // to hold current question

    int questionIndex = 0; // to index through deffList

    int answersCorrect = 0; // to hold the num of correct answers

    public boolean buttonsEnabled = true;

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
                    if(buttonsEnabled){
                        checkBtnAnswer(term1Btn);
                        //disableButtons();
                        //term1Btn.setEnabled(false);
                        term2Btn.setEnabled(false);
                        term3Btn.setEnabled(false);
                        term4Btn.setEnabled(false);
                    }
                    break;
                case R.id.btnTerm2:
                    //do something
                    if(buttonsEnabled){
                        checkBtnAnswer(term2Btn);
                        //disableButtons();
                        term1Btn.setEnabled(false);
                        //term2Btn.setEnabled(false);
                        term3Btn.setEnabled(false);
                        term4Btn.setEnabled(false);
                    }

                    break;
                case R.id.btnTerm3:
                    // do something
                    if(buttonsEnabled){
                        checkBtnAnswer(term3Btn);
                        //disableButtons();
                        term1Btn.setEnabled(false);
                        term2Btn.setEnabled(false);
                        //term3Btn.setEnabled(false);
                        term4Btn.setEnabled(false);
                    }

                    break;
                case R.id.btnTerm4:
                    // do something
                    if(buttonsEnabled){
                        checkBtnAnswer(term4Btn);
                        //disableButtons();
                        term1Btn.setEnabled(false);
                        term2Btn.setEnabled(false);
                        term3Btn.setEnabled(false);
                        //term4Btn.setEnabled(false);
                    }

                    break;
                case R.id.btnNext:
                    // do something
                    newDeffAndNewTerm();
                    System.out.println(answersCorrect);
                    enableButtons();
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

        // changed the above code into a method.
        setBtnText();


        // Keeps saying is NULL and Crashes
//        for(int i=0; i < btnArray.length; i++){
//            btnArray[i].setText(btnAnswerChoices.get(i));
//        }

        questionIndex++;
    }

    public void setBtnText(){
        term1Btn.setText(btnAnswerChoices.get(0));
        term2Btn.setText(btnAnswerChoices.get(1));
        term3Btn.setText(btnAnswerChoices.get(2));
        term4Btn.setText(btnAnswerChoices.get(3));
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

            // set a new index value
            while(btnAnswerChoices.contains(termList.get(randoIndex))){
                randoIndex = rand.nextInt(termList.size());
            }
            btnAnswerChoices.add(termList.get(randoIndex));

        }

        // now shuffling the order
        Collections.shuffle(btnAnswerChoices);

    }


    // disables buttons once a button is clicked.
    public void disableButtons(Button btn){
        buttonsEnabled = false;
        for(Button btnChoice: btnObjects){
            if(!btnChoice.equals(btn)){
                btnChoice.setEnabled(false);
            }
        }
//        term1Btn.setEnabled(false);
//        term2Btn.setEnabled(false);
//        term3Btn.setEnabled(false);
//        term4Btn.setEnabled(false);
    }

    // enables all the buttons after nxtbtn is clicked.
    public void enableButtons(){
        buttonsEnabled = true;
        term1Btn.setEnabled(true);
        term2Btn.setEnabled(true);
        term3Btn.setEnabled(true);
        term4Btn.setEnabled(true);
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