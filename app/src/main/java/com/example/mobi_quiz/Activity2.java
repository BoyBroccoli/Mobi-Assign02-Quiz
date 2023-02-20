package com.example.mobi_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

Button startquiz;
TextView quizQuestion;

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_2);
    quizQuestion = findViewById(R.id.currentQuestion);

    ArrayList<String> deffList = getIntent().getStringArrayListExtra("deffList");
    ArrayList<String> termList = getIntent().getStringArrayListExtra("termsList");
    quizQuestion.setText(deffList.get(1));
}
}

