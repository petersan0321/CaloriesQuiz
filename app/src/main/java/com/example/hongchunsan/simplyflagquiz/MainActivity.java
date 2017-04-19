package com.example.hongchunsan.simplyflagquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //for version 2; dynamic change q and asnwers and image

    private List<String> quizFlagNameList; //countries in current quiz

    private List<Integer> quizFlagPhotoList;

    private int i = 0;

    private int totalGuesses;

    private int correctAnswers;

    private String correctAnswer;

    private Handler handler; //used to delay loading next flag

    private Animation shakeAnimation; //animation for incorrect guess

    private ImageView flagImageView;

    private RelativeLayout quizLinearLayout;

    private TextView result;

    private TextView questionNumberTextView;

    private static final String TAG = "FlagQuiz Activity";

    private static final int FLAGS_IN_QUIZ = 4;

    //private FragmentManager fm;

    private Button btnA;

    private Button btnB;

    private Button btnC;

    private Button btnD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizFlagNameList = new ArrayList<>();
        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.incorrect_shake); //***must use getApplicationContext()
        shakeAnimation.setRepeatCount(3);
        flagImageView = (ImageView) findViewById(R.id.imageView);
        quizLinearLayout = (RelativeLayout) findViewById(R.id.activity_main);
        handler = new Handler();
        quizFlagNameList = new ArrayList<>();
        quizFlagNameList.add("1167");
        quizFlagNameList.add("176");
        quizFlagNameList.add("711");
        quizFlagNameList.add("275");
        quizFlagPhotoList = new ArrayList<>();
        quizFlagPhotoList.add(R.drawable.hoegarrden);
        quizFlagPhotoList.add(R.drawable.cheesewhopper);
        quizFlagPhotoList.add(R.drawable.snickers);

        correctAnswer = quizFlagNameList.get(0);
        correctAnswers = 0;
        totalGuesses=0;

        btnA = (Button) findViewById(R.id.buttonA);
        btnB = (Button) findViewById(R.id.buttonB);
        btnC = (Button) findViewById(R.id.buttonC);
        btnD = (Button) findViewById(R.id.buttonD);
        result = (TextView) findViewById(R.id.textViewResult);
        questionNumberTextView = (TextView) findViewById(R.id.questionnumber);

        questionNumberTextView.setText(getString(R.string.question, (correctAnswers + 1), FLAGS_IN_QUIZ));

        // final TextView result = (TextView) findViewById(R.id.textViewResult);

        btnA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //result.setText("Your answer is corret!");
                //  Intent startQ2Activity = new Intent(v.getContext(),Main2Activity.class);
                //  startActivity(startQ2Activity);

                checkAnswer(btnA.getText().toString().equalsIgnoreCase(correctAnswer), btnA);

            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnB.getText().toString().equalsIgnoreCase(correctAnswer), btnB);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnC.getText().toString().equalsIgnoreCase(correctAnswer), btnC);
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnD.getText().toString().equalsIgnoreCase(correctAnswer), btnD);
            }

        });
    }

    private void animate(boolean animateOut) {
// prevent animation into the the UI for the first flag
        // if (correctAnswers == 0)
        //     return;
        // calculate center x and center y
        int centerX = (quizLinearLayout.getLeft() +
                quizLinearLayout.getRight()) / 2; // calculate center x
        int centerY = (quizLinearLayout.getTop() +
                quizLinearLayout.getBottom()) / 2; // calculate center y
        // calculate animation radius
        int radius = Math.max(quizLinearLayout.getWidth(),
                quizLinearLayout.getHeight());
        Animator animator;
// if the quizLinearLayout should animate out rather than in
        if (animateOut) {
// create circular reveal animation
            animator = ViewAnimationUtils.createCircularReveal(
                    quizLinearLayout, centerX, centerY, radius, 0);
            animator.addListener(
                    new AnimatorListenerAdapter() {
                        // called when the animation finishes
                        @Override
                        public void onAnimationEnd(Animator animation) {
// loadNextFlag();
                            result.setText("");
                            //correctAnswer = quizFlagNameList.get(1);
                            //flagImageView.setImageResource(R.drawable.china);
                            btnA.setEnabled(true);
                            btnB.setEnabled(true);
                            btnC.setEnabled(true);
                            btnD.setEnabled(true);
                            //result.setText("");

                            if (correctAnswers == FLAGS_IN_QUIZ) {
//reached the end of question
                                // questionNumberTextView.setText(getString(R.string.question, (correctAnswers), FLAGS_IN_QUIZ));
                            }
                            else {

                                correctAnswer = quizFlagNameList.get(correctAnswers);
                                flagImageView.setImageResource(quizFlagPhotoList.get(i));
                                i =  i + 1;
                                questionNumberTextView.setText(getString(R.string.question, (correctAnswers+1), FLAGS_IN_QUIZ));
                            }


                        }
                    }
            );
        } else { // if the quizLinearLayout should animate in
            animator = ViewAnimationUtils.createCircularReveal(
                    quizLinearLayout, centerX, centerY, 0, radius);
        }
        animator.setDuration(500); // set animation duration to 500 ms
        animator.start(); // start the animation
    }


    private void checkAnswer(boolean correct, Button buttonName) {
        totalGuesses++;
        if (correct) {

//result.setText("");
            // loadNextFlag();
            //questionNumberTextView.setText(getString(R.string.question, (correctAnswers), FLAGS_IN_QUIZ));
            // if the user has correctly identified FLAGS_IN_QUIZ flags
            correctAnswers++;
            result.setText("Your answer is correct!");
            if (correctAnswers == FLAGS_IN_QUIZ) {

                Toast.makeText(MainActivity.this, getString(R.string.result, totalGuesses, (correctAnswers / (double) totalGuesses) * 100), Toast.LENGTH_SHORT).show();
//questionNumberTextView.setText(getString(R.string.question, (correctAnswers), FLAGS_IN_QUIZ));
            } else {
// questionNumberTextView.setText(getString(R.string.question, (correctAnswers), FLAGS_IN_QUIZ));
                handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                animate(true);
                            }
                        }, 2000); //2000 milliseconds for 2-second delay

            }
        } else {
            result.setText("Incorrect!");
            flagImageView.startAnimation(shakeAnimation);//play shake
            buttonName.setEnabled(false);
        }
    }




}
