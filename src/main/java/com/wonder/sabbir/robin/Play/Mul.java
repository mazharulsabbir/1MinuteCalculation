package com.wonder.sabbir.robin.Play;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wonder.sabbir.robin.DatabaseHelper.DatabaseConnection;
import com.wonder.sabbir.robin.R;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLiteHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Mul extends AppCompatActivity {

    private static final String TAG = "Mul";
    private static final String LAVEL = "LAVEL";
    private static final String NAME = "PLAY_GAME";

    private static final String FIELD = "totalScore";
    private static final String PLAYER = "PLAYERNAME";
    private static final String SAVE_TO = "Multiplication";
    private static final String PLAYER_EMAIL = "PLAYER_EMAIL";
    String mPlayerName, mPlayerEmail, checkLavel = "", getLavel;


    private static final long START_TIME_IN_MILLIS = 60000;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    DocumentReference myRef;

    Button btn1, btn2, btn3, btn4;
    TextView tv_score, tv_quesview, tv_timeremaining;
    int points = 0, totalPoints = 0, value1, value2, lavel = 0,
            btn_testcase, correctanswer = 0, wrongAnswer = 0, totalQuesNumber = 0,quesCase, answer,i=10;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    Vibrator vibrate;
    int totalPoint = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //jhdsjfsdd
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mul);

        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Multiplication");
        //jshdfjhsdjf


        AdView mAdView;

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        tv_score = findViewById(R.id.tv_points);
        tv_quesview = findViewById(R.id.tv_quesshow);

        tv_timeremaining = findViewById(R.id.tv_timeramaining);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        progressBar = findViewById(R.id.progressBar);

        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        mPlayerName = (mSharedPreference.getString(PLAYER, ""));
        mPlayerEmail = (mSharedPreference.getString(PLAYER_EMAIL, ""));
        getLavel = mSharedPreference.getString(LAVEL, "1");

        swithchLavel(Integer.parseInt(getLavel));
        myRef = firestore.document(mPlayerName + "/" + TAG);

        retriveData();


        tv_score.setText("Score : " + points);
        setQuesOnTextView();
        setAnswerChoiceOnButton();
        answerButtonOnClick();


        startTimer();
    }

    public void saveToFirebase() {
        totalPoint += points;
        DatabaseConnection data = new DatabaseConnection(Integer.toString(points), Integer.toString(totalPoint));
        myRef.set(data);
    }

//    public void retriveData() {
//
//        myRef.get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if (documentSnapshot.exists()) {
//                            DatabaseConnection databaseConnection = documentSnapshot.
//                                    toObject(DatabaseConnection.class);
//                            String totalScore = databaseConnection.getTotalScore();
//                            totalPoint = Integer.parseInt(totalScore);
//                        }
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(Mul.this, "Error on Add\n" + e.toString(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//    }

    public void setQuesOnTextView() {
        NumberFormat format = new DecimalFormat("#0");
        value1 = Integer.parseInt(format.format(Math.random() * i));
        value2 = Integer.parseInt(format.format(Math.random() * i));
        int value3 = Integer.parseInt(format.format(Math.random() * i));


        double tc = (Math.random() * 2) + 1;
        int mb = (int) tc;

        switch (quesCase) {
            case 1:
                tv_quesview.setText("" + value1 + " x " + value2 + "");
                answer = value1 * value2;
                break;

            case 2:
                tv_quesview.setText("" + value1 + " x " + value2 + " x " + value3 + "");
                answer = value1 * value2 * value3;
                break;


        }
    }

    public void setAnswerChoiceOnButton() {
        try {
            double tc = (Math.random() * 4) + 1;
            int mb = (int) tc;

            switch (mb) {


                case 1: {


                    btn1.setText(Integer.toString(answer));
                    btn2.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    btn3.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    btn4.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    break;
                }
                case 2: {

                    btn2.setText(Integer.toString(answer));
                    btn4.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    btn3.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    btn1.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    break;
                }
                case 3: {

                    btn3.setText(Integer.toString(answer));
                    btn2.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    btn4.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    btn1.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    break;
                }
                case 4: {
                    btn4.setText(Integer.toString(answer));
                    btn2.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    btn3.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    btn1.setText(Integer.toString((int) (answer + Math.random() * 5) + 1));
                    break;
                }

            }
        } catch (Exception e) {

        }
    }

    public void answerButtonOnClick() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuesNumber++;
                if (btn1.getText().equals(Integer.toString(answer))) {
                    setQuesOnTextView();
                    setAnswerChoiceOnButton();
                    points += 30;
                    tv_score.setText("Score : " + points);
                    correctanswer++;
                    btn2.setClickable(true);
                    btn3.setClickable(true);
                    btn4.setClickable(true);
                } else {
                    wrongAnswer++;
                    vibrate.vibrate(200);
                    points -= 10;
                    btn1.setClickable(false);
                    tv_score.setText("Score : " + points);
                }
                if (wrongAnswer > 4)
                    mCountDownTimer.onFinish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuesNumber++;
                if (btn2.getText().equals(Integer.toString(answer))) {
                    setQuesOnTextView();
                    setAnswerChoiceOnButton();
                    points += 30;
                    tv_score.setText("Score : " + points);
                    correctanswer++;

                    btn1.setClickable(true);
                    btn3.setClickable(true);
                    btn4.setClickable(true);
                } else {
                    wrongAnswer++;
                    vibrate.vibrate(200);
                    points -= 10;
                    btn2.setClickable(false);
                    tv_score.setText("Score : " + points);
                }
                if (wrongAnswer > 4)
                    mCountDownTimer.onFinish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuesNumber++;
                if (btn3.getText().equals(Integer.toString(answer))) {
                    setQuesOnTextView();
                    setAnswerChoiceOnButton();
                    points += 30;
                    tv_score.setText("Score : " + points);
                    correctanswer++;

                    btn2.setClickable(true);
                    btn1.setClickable(true);
                    btn4.setClickable(true);
                } else {
                    wrongAnswer++;
                    vibrate.vibrate(200);
                    points -= 10;
                    tv_score.setText("Score : " + points);
                    btn3.setClickable(false);
                }
                if (wrongAnswer > 4)
                    mCountDownTimer.onFinish();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuesNumber++;
                if (btn4.getText().equals(Integer.toString(answer))) {
                    setQuesOnTextView();
                    setAnswerChoiceOnButton();
                    points += 30;
                    tv_score.setText("Score : " + points);
                    correctanswer++;

                    btn2.setClickable(true);
                    btn3.setClickable(true);
                    btn1.setClickable(true);
                } else {
                    wrongAnswer++;
                    vibrate.vibrate(200);
                    points -= 10;
                    tv_score.setText("Score : " + points);
                    btn4.setClickable(false);
                }
                if (wrongAnswer > 4)
                    mCountDownTimer.onFinish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder b = new AlertDialog.Builder(Mul.this);
        View v = getLayoutInflater().inflate(R.layout.activity_on_back, null);
        b.setView(v);
        final AlertDialog d = b.create();
        d.show();
        d.setCancelable(false);
        Button btn_Continue = v.findViewById(R.id.button_Continue);
        Button btn_back_Home = v.findViewById(R.id.button_Back_Home);
        pauseTimer();
        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.cancel();
                startTimer();
            }
        });
        btn_back_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    mCountDownTimer.onFinish();
                    d.cancel();
                    finish();
                } catch (Exception e) {

                }
            }
        });
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                progressBar.setProgress((int) (millisUntilFinished / 1000));
                tv_timeremaining.setText("Time Remaining : " + millisUntilFinished / 1000 + " seconds");
                //updateCountDownText();
            }

            @Override
            public void onFinish() {
                if (points < 0)
                    points = 0;
                mTimerRunning = false;
                saveToSQLite();
                saveToFirebase();
                Intent i = new Intent(Mul.this, Gameover.class);
                i.putExtra("Score", Integer.toString(points));
                i.putExtra("TotalQues", Integer.toString(totalQuesNumber));
                i.putExtra("CorrectAnswer", Integer.toString(correctanswer));
                int wrong = totalQuesNumber - correctanswer;
                i.putExtra("WrongAnswer", Integer.toString(wrong));
                final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String playerName = (mSharedPreference.getString(PLAYER, ""));
                String playerEmail = (mSharedPreference.getString(PLAYER_EMAIL, ""));

                i.putExtra(PLAYER, playerName);
                i.putExtra(PLAYER_EMAIL, playerEmail);

                startActivity(i);
                finish();
            }
        }.start();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    public void saveToSQLite() {
        saveHistory();
        completeLavel();
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        totalPoint += points;
        boolean insert = sqLiteHelper.saveScores(mPlayerEmail, mPlayerName, SAVE_TO, totalPoint);
        if (!insert) {
            sqLiteHelper.updateScore(mPlayerEmail, mPlayerName, SAVE_TO, totalPoint);
        }
    }

    public void retriveData() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(4));
            }
            totalPoint = Integer.parseInt(stringBuffer.toString());
        }

    }

    public void saveHistory() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        sqLiteHelper.insertHistory(mPlayerEmail, SAVE_TO, points);
    }

    public void saveLavel() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        boolean result = sqLiteHelper.completeLavel(mPlayerEmail, SAVE_TO, lavel);
        if (!result)
            sqLiteHelper.updateLavel(mPlayerEmail, SAVE_TO, lavel);
    }

    public void completeLavel() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getCompletedLavel(mPlayerEmail);
        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(3));
            }
            cursor.close();
            checkLavel = (stringBuffer.toString());

        }
        if (checkLavel.equals("") | checkLavel.equals("0")) {
            checkLavel = "1";
            lavel++;
        }
        if (getLavel.equals(checkLavel)) {
            lavel = Integer.parseInt(checkLavel.toString()) + 1;
        }
        if (Integer.parseInt(getLavel) < Integer.parseInt(checkLavel)) {
            lavel = Integer.parseInt(checkLavel.toString());
        }
        saveLavel();
    }

    public void swithchLavel(int mLavel) {
        switch (mLavel) {
            case 1:
                i = 5;
                quesCase = 1;
                break;
            case 2:
                i = 7;
                quesCase = 1;
                break;
            case 3:
                i = 10;
                quesCase = 1;
                break;
            case 4:
                i = 12;
                quesCase = 1;
                break;
            case 5:
                i = 15;
                quesCase = 2;
                break;
            case 6:
                i = 20;
                quesCase = 2;
                break;
            case 7:
                i = 25;
                quesCase = 2;
                break;
            case 8:
                i = 30;
                quesCase = 2;
                break;
            case 9:
                i = 40;
                quesCase = 2;
                break;
            case 10:
                i = 50;
                quesCase = 2;
                break;
        }
    }


}
