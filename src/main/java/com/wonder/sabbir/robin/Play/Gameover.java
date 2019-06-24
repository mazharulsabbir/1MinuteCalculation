package com.wonder.sabbir.robin.Play;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.wonder.sabbir.robin.Activity.MainActivity;
import com.wonder.sabbir.robin.R;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLiteHelper;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;


public class Gameover extends AppCompatActivity {
    private static final String TAG = "Gameover";
    private static final String PLAYER = "PLAYERNAME";
    private static final String PLAYER_EMAIL = "PLAYER_EMAIL";
    private static final String SAVE_TO = "totalScore";
    private InterstitialAd mInterstitialAd;


    ImageButton replay, home, back;
    TextView totalNumber, totalNoOfQues, totalNoOfCorrect, totalNoOfWrong;
    String  mPlayerName,mPlayerEmail;
    int totalScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gameover);

        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Game Over");

        replay = findViewById(R.id.btn_replay);
        home = findViewById(R.id.btn_backtohome);
        back = findViewById(R.id.btn_backtoplay);
        totalNumber = findViewById(R.id.tv_totalMarks);

        //interestitial ads

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interestitialAds));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d(TAG, "The interstitial wasn't loaded yet.");
        }


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

            }
        });




    Intent intent = getIntent();
        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        mPlayerName =(mSharedPreference.getString(PLAYER, ""));
        mPlayerEmail =(mSharedPreference.getString(PLAYER_EMAIL, ""));

        retriveAdd();
        retriveSub();
        retriveMul();
        retriveDiv();
        retriveSqrt();
        retriveSquare();
        retriveRandom();
        retriveFact();

        totalNumber.setText(intent.getStringExtra("Score"));
//        totalNoOfQues.setText(intent.getStringExtra("TotalQues"));
//        totalNoOfCorrect.setText(intent.getStringExtra("CorrectAnswer"));
//        totalNoOfWrong.setText(intent.getStringExtra("WrongAnswer"));

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Gameover.this, MainActivity.class);
                saveTotalScore();

                startActivity(i);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Gameover.this, play.class);
                saveTotalScore();

                startActivity(i);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

            }
        });
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Gameover.this, play.class);
                saveTotalScore();


                startActivity(i);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

    }
    public void saveTotalScore() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        boolean insertTotal = sqLiteHelper.saveScores(mPlayerEmail,mPlayerName,SAVE_TO,totalScore);
        if (!insertTotal) {
            sqLiteHelper.updateScore(mPlayerEmail,mPlayerName,SAVE_TO,totalScore);
        }
    }


    public void retriveAdd() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append(cursor.getInt(2));
            }
            totalScore+= Integer.parseInt(stringBuffer.toString());
        }
    }

    public void retriveSub() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append(cursor.getInt(3));
            }

            totalScore+= Integer.parseInt(stringBuffer.toString());
        }

    }

    public void retriveMul() {


        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append(cursor.getInt(4));
            }
            totalScore+= Integer.parseInt(stringBuffer.toString());
        }

    }

    public void retriveDiv() {


        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append(cursor.getInt(5));
            }
            totalScore+= Integer.parseInt(stringBuffer.toString());
        }
    }

    public void retriveRandom() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append(cursor.getInt(6));
            }
            totalScore+= Integer.parseInt(stringBuffer.toString());
        }

    }

    public void retriveSqrt() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append(cursor.getInt(7));
            }
            totalScore+= Integer.parseInt(stringBuffer.toString());
        }
    }

    public void retriveSquare() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append(cursor.getInt(8));
            }
            totalScore+= Integer.parseInt(stringBuffer.toString());
        }
    }

    public void retriveFact() {


        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append(cursor.getInt(9));
            }
            totalScore+= Integer.parseInt(stringBuffer.toString());
        }

    }

    @Override
    public void onBackPressed() {

    }


}
