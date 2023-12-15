package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateQuizActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    private EditText questionEditText, option1EditText, option2EditText, option3EditText, option4EditText;
    private RadioGroup optionsRadioGroup;
    private Button addQuestionButton, finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        questions = new ArrayList<>();

        questionEditText = findViewById(R.id.questionEditText);
        option1EditText = findViewById(R.id.option1EditText);
        option2EditText = findViewById(R.id.option2EditText);
        option3EditText = findViewById(R.id.option3EditText);
        option4EditText = findViewById(R.id.option4EditText);

        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        optionsRadioGroup.check(R.id.option1RadioButton);

        addQuestionButton = findViewById(R.id.addQuestionButton);
        finishButton = findViewById(R.id.finishButton);

        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestion();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishQuizCreation();
            }
        });
    }

    private void addQuestion() {
        String questionText = questionEditText.getText().toString().trim();
        String option1 = option1EditText.getText().toString().trim();
        String option2 = option2EditText.getText().toString().trim();
        String option3 = option3EditText.getText().toString().trim();
        String option4 = option4EditText.getText().toString().trim();


        RadioButton selectedRadioButton = findViewById(optionsRadioGroup.getCheckedRadioButtonId());
        int correctOptionIndex = optionsRadioGroup.indexOfChild(selectedRadioButton) + 1;

        Question question = new Question(questionText, new String[]{option1, option2, option3, option4}, correctOptionIndex);
        questions.add(question);

        questionEditText.setText("");
        option1EditText.setText("");
        option2EditText.setText("");
        option3EditText.setText("");
        option4EditText.setText("");
        optionsRadioGroup.check(R.id.option1RadioButton);
    }

    private void finishQuizCreation() {

        Intent resultIntent = new Intent();
        resultIntent.putExtra("questions", questions);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}