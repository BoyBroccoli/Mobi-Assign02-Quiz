package com.example.mobi_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity2 extends AppCompatActivity {

Button startquiz;
TextView quizQuestion;

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_2);
    quizQuestion = findViewById(R.id.currentQuestion);

    Intent i = getIntent();

    ArrayList<String> deffList = i.getStringArrayListExtra("deffList");
    ArrayList<String> termList = i.getStringArrayListExtra("termsList");
    quizQuestion.setText(deffList.get(1));
Bundle data = i.getExtras();
    Map<String,String> map = new HashMap<String,String>();
    map = data.getParcelable("hashMap"); // trying to pass hashmap to intent


}
}

