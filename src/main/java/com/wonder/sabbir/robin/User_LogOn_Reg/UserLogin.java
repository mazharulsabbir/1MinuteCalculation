package com.wonder.sabbir.robin.User_LogOn_Reg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.wonder.sabbir.robin.Activity.MainActivity;
import com.wonder.sabbir.robin.R;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLite;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class UserLogin extends AppCompatActivity {
    private static final String SHARED_PREF = "SHARED_PREF";
    private static final String SHARED_NAME = "SHARED_NAME";
    private static final String SHARED_PASSWORD = "SHARED_PASSWORD";
    private static final String PLAYER_NAME = "PLAYERNAME";
    private static final String PLAYER_EMAIL = "PLAYER_EMAIL";
    private static final String PLAYER_EMAIL_LOGIN = "PLAYER_LOGIN";
    private static final String PLAYER_NAME_LOGIN = "PLAYER_LOGIN";
    private InterstitialAd mInterstitialAd;

    private AdView mAdView;
    CheckBox saveUserInfo;
    EditText mEmail, mPassword;
    SQLite sqLite;
    String mPlayerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_login);
        sqLite = new SQLite(this);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);

        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Login");

        saveUserInfo = findViewById(R.id.checkBoxRememberPassword);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interestitialAds));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
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

        SharedPreferences receive = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        String mUsername = receive.getString(SHARED_NAME, "Sabbir");
        String mPass = receive.getString(SHARED_PASSWORD, "Sabbir");
        mPassword.setText(mPass);
        mEmail.setText(mUsername);

//        if (mUsername.isEmpty() && mPass.isEmpty()){
//
//        }else{
//            Intent i;
//            i = new Intent(this, MainActivity.class);
//            startActivity(i);
//            finish();
//        }

    }

    public void loginPress(View v) {

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            final Snackbar sncakbar = Snackbar.make(v, "EMPTY FIELD", Snackbar.LENGTH_SHORT);
            sncakbar.getView().setBackgroundColor(getResources().getColor(R.color.colorRed));
            sncakbar.setActionTextColor(getResources().getColor(R.color.colorPrimaryDark));
            sncakbar.show();
        } else {
            boolean result = sqLite.findUser(email, password);
            if (result) {

                SharedPreferences data = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor1 = prefs.edit();
                if (saveUserInfo.isChecked()) {
                    editor.putString(SHARED_NAME, email);
                    editor.putString(SHARED_PASSWORD, password);
                    editor.apply();


                    editor1.putString(PLAYER_NAME_LOGIN, mPlayerName);
                    editor1.putString(PLAYER_EMAIL_LOGIN, email);
                    editor1.apply();

                } else {

                    editor.putString(SHARED_NAME, "");
                    editor.putString(SHARED_PASSWORD, "");
                    editor.apply();

                    editor1.putString(PLAYER_NAME_LOGIN, "");
                    editor1.putString(PLAYER_EMAIL_LOGIN, "");
                    editor1.apply();

                }

                Cursor cursor = sqLite.getUserName(email);

                if (cursor.getCount() != 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()) {
                        stringBuffer.append(cursor.getString(1));
                    }
                    editor.putString(PLAYER_NAME, stringBuffer.toString());
                    editor.apply();
                    mPlayerName = stringBuffer.toString();
                }

                Intent i;
                i = new Intent(this, MainActivity.class);
                i.putExtra(PLAYER_NAME, mPlayerName);
                i.putExtra(PLAYER_EMAIL, email);
                startActivity(i);
                finish();

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

            } else {
                final Snackbar sncakbar = Snackbar.make(v, "INCORRECT USERNAME OR PASSWORD", Snackbar.LENGTH_SHORT);
                sncakbar.getView().setBackgroundColor(getResources().getColor(R.color.colorRed));
                sncakbar.setActionTextColor(getResources().getColor(R.color.colorPrimaryDark));
                sncakbar.show();

            }
        }

    }

    public void Registration(View v) {
        startActivity(new Intent(this, Registration.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
