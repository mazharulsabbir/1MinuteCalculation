package com.wonder.sabbir.robin.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.wonder.sabbir.robin.Play.play;
import com.wonder.sabbir.robin.R;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLite;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLiteHelper;
import com.wonder.sabbir.robin.User_LogOn_Reg.UserLogin;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String FIELD = "totalScore";
    private static final String PLAYER_NAME = "PLAYERNAME";
    private static final String PLAYER_EMAIL = "PLAYER_EMAIL";
    private static final String SHARED_PREF = "USER_INFO";
    private static final String TOTAL_SCORE = "totalScore";

    SQLiteHelper sqLiteHelper;
    SQLite sqLite;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ProgressDialog progressDialog;
    ImageButton buttonPlay, buttonSettings, buttonleaderboard;
    TextView mName, mHighScore, mTotalScore, mRank;
    String mPlayerName, email;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CUSTOM TOOLBAR
        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Home");

        //SQLITE DATABASE
        sqLiteHelper = new SQLiteHelper(MainActivity.this);
        sqLite = new SQLite(this);

        //FIND VIA ID
        buttonPlay = findViewById(R.id.btn_play);
        buttonleaderboard = findViewById(R.id.btn_leaderboard);
        buttonSettings = findViewById(R.id.btn_settings);

        mName = findViewById(R.id.playerName);
        mHighScore = findViewById(R.id.textView_high_score);
        mTotalScore = findViewById(R.id.user_Score);
        mRank = findViewById(R.id.user_Global_rank);

        //ADVIEW START
        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent i = getIntent();
        mPlayerName = i.getStringExtra(PLAYER_NAME);
        email = i.getStringExtra(PLAYER_EMAIL);

        //SAVE PLAYER NAME & EMAIL TO SHARED PREFERENCE
        if (mPlayerName != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor1 = prefs.edit();
            editor1.putString(PLAYER_NAME, mPlayerName);
            editor1.putString(PLAYER_EMAIL, email);
            editor1.apply();

            SharedPreferences data = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putString(PLAYER_NAME, mPlayerName);
            editor.putString(PLAYER_EMAIL, email);
            editor.apply();
        }


        if (mPlayerName == null) {
            SharedPreferences receive = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
            mPlayerName = receive.getString(PLAYER_NAME, null);
            email = receive.getString(PLAYER_EMAIL, null);
        }

        //GER USER INFO FROM DATABASE & SAVE INFO TO SHARED PREFERENCE
        Cursor cursor = sqLite.getUserName(email);
        StringBuffer buffer = new StringBuffer();

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                buffer.append(cursor.getString(1));
            }
            cursor.close();
            mPlayerName = buffer.toString();
        }
        sqLiteHelper.close();

        if (mPlayerName != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor1 = prefs.edit();
            editor1.putString(PLAYER_NAME, mPlayerName);
            editor1.putString(PLAYER_EMAIL, email);
            editor1.apply();

            SharedPreferences data = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putString(PLAYER_NAME, mPlayerName);
            editor.putString(PLAYER_EMAIL, email);
            editor.apply();
        }

        //SHOW USER NAME TO TEXTVIEW
        mName.setText(mPlayerName);

        //SAVE USR NAME AND EMAIL TO ANOTHER SQLITE DATABASE.
        sendUserInfoToScoreList(email, mPlayerName);

        //RECEIVE TOTAL & HIGH SCORES
        mReceiveData();
        if (mTotalScore.getText() == null)
            mTotalScore.setText("0");
        getBestScore();


        boolean res = sqLiteHelper.userPosition(email);
        if (res)
            mRank.setText("Position: " + sqLiteHelper.position);


        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i;
                    i = new Intent(MainActivity.this, play.class);
                    i.putExtra(PLAYER_NAME, mPlayerName);
                    i.putExtra(PLAYER_EMAIL, email);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                }
            }
        });
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences receive = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
                email = receive.getString(PLAYER_EMAIL, null);
                Intent i = new Intent(MainActivity.this, Settings.class);
                i.putExtra(PLAYER_EMAIL, email);
                startActivity(i);
            }
        });
        buttonleaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(MainActivity.this, LeaderBoard.class));
                } catch (Exception e) {
                }
            }
        });
    }

    public void sendUserInfoToScoreList(String userMail, String userName) {
        try {
            boolean info = sqLiteHelper.saveScores(userMail, userName, TOTAL_SCORE, 0);
            if (!info)
                sqLiteHelper.updateEmai_Name(userMail, userName);
        } catch (Exception e) {
            Log.d(TAG, "MAIN ACTIVITY" + e.getMessage());
        }

    }

    public void mReceiveData() {
        Cursor cursor = sqLiteHelper.getSingleData(email);
        if (cursor.getCount() == 0) {
        }
        StringBuffer stringBuffer = new StringBuffer();

        while (cursor.moveToNext()) {
            stringBuffer.append(cursor.getInt(10));
        }
        cursor.close();
        sqLiteHelper.close();
        mTotalScore.setText(NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(stringBuffer.toString())));

    }

    public void getBestScore() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getHighScore();
        int score = 0;
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                score = cursor.getInt(10);
            }
            cursor.close();
            mHighScore.setText(NumberFormat.getNumberInstance(Locale.US).format(score).toString());
        }
        sqLiteHelper.close();
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(this, UserLogin.class));
                finish();
                return true;
            //Code
            case R.id.about:
                Toast.makeText(getApplicationContext(), "About Clicked", Toast.LENGTH_SHORT).show();
                //Code
                return true;
            case R.id.my_profile:
                Toast.makeText(getApplicationContext(), "My Profile Clicked", Toast.LENGTH_SHORT).show();
                //Code
                // Call methods
                return true;
            case R.id.help:
                Toast.makeText(getApplicationContext(), "Help Clicked", Toast.LENGTH_SHORT).show();
                //Code
                // Call methods
                return true;
            case R.id.Close:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
