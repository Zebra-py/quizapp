package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private TextView questionTextView, scoreTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton, retryButton, backToMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questions = (ArrayList<Question>) getIntent().getSerializableExtra("questions");
        currentQuestionIndex = 0;
        score = 0;

        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        nextButton = findViewById(R.id.nextButton);
        retryButton = findViewById(R.id.retryButton);
        backToMainButton = findViewById(R.id.backToMainButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNextButtonClick();
            }
        });

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartQuiz();
            } //Пробуем свои силы ещё раз
        });

        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Возвращаемся на главный экран
            }
        });

        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);

            questionTextView.setText(currentQuestion.getQuestion());

            optionsRadioGroup.removeAllViews();

            for (int i = 0; i < currentQuestion.getOptions().length; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(currentQuestion.getOptions()[i]);
                optionsRadioGroup.addView(radioButton);
            }
        } else {
            // Как только вопросы кончатся выведодит результат
            showResult();
        }
    }

    private void handleNextButtonClick() {
        if (currentQuestionIndex < questions.size()) {
            int selectedOptionIndex = optionsRadioGroup.indexOfChild(
                    findViewById(optionsRadioGroup.getCheckedRadioButtonId()));

            if (selectedOptionIndex == questions.get(currentQuestionIndex).getCorrectOptionIndex()) {
                score++;
            }

            currentQuestionIndex++;
            displayQuestion();
        }
    }

    private void showResult() {
        scoreTextView.setText("Ваш счёт: " + score + " из " + questions.size());
        scoreTextView.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.VISIBLE);
        backToMainButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.GONE);
    }

    private void restartQuiz() {
        // Сбрасываем всё и начинаем заново
        currentQuestionIndex = 0;
        score = 0;
        scoreTextView.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
        backToMainButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.VISIBLE);
        displayQuestion();
    }
}