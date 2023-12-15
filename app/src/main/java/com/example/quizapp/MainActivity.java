package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CREATE_QUIZ = 1;
    private ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createQuizButton = findViewById(R.id.createQuizButton);
        Button startQuizButton = findViewById(R.id.startQuizButton);

        createQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCreateQuizActivity();
            }
        });

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startCreateQuizActivity() {
        Intent intent = new Intent(this, CreateQuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CREATE_QUIZ);
    }

    private void startQuiz() {
        // Проверка наличия вопросов для викторины
        if (hasQuestions()) {
            Intent quizIntent = new Intent(this, QuizActivity.class);
            quizIntent.putExtra("questions", questions);
            startActivity(quizIntent);
        } else {

        }
    }

    private boolean hasQuestions() {
        return questions != null && !questions.isEmpty();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CREATE_QUIZ && resultCode == RESULT_OK) {
            questions = (ArrayList<Question>) data.getSerializableExtra("questions");
        }
    }
}