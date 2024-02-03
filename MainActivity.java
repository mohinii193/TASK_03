package com.example.quizapptask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import color.MAGENTA;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionTextView;
    TextView QuestionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitbtn;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextView = findViewById(R.id.total_question);
        QuestionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitbtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitbtn.setOnClickListener(this);

        totalQuestionTextView.setText("Total question : "+totalQuestion);

        loadNewQuestion();



    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            loadNewQuestion();
            if (selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        } else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }
    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        QuestionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);


    }
    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();

        }
        void restartQuiz(){
            score = 0;
            currentQuestionIndex = 0;
            loadNewQuestion();



        }
}