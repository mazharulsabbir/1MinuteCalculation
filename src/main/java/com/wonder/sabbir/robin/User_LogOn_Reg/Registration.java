package com.wonder.sabbir.robin.User_LogOn_Reg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.wonder.sabbir.robin.DatabaseHelper.DatabaseConnection;
import com.wonder.sabbir.robin.DatabaseHelper.userRegistrationDatabase;
import com.wonder.sabbir.robin.R;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLite;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {
    private static final String TAG = "Registration";
    private static final String PERSONAL_INFORMATION = "Personal Information";
    private static final String FIELD = "totalScore";
    private static final String TOTALSCORE = "TOTALSCORE";
    private InterstitialAd mInterstitialAd;

    SQLite sqLite;

    EditText mName, mEmail, mPassword, mRePassword;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Registration");


        sqLite = new SQLite(this);

        mName = findViewById(R.id.mName);
        mEmail = findViewById(R.id.mEmailAddress);
        mPassword = findViewById(R.id.mPassword);
        mRePassword = findViewById(R.id.mRePassword);

        //interestitial ads
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


    }

    public void saveAndContinue(View v) {


        String name = mName.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String rePassword = mRePassword.getText().toString();

        if(password.equals(rePassword) && password.length()>=6){
            if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                //SQLITE DATABASAE
                boolean result = sqLite.saveInfo(name, email, password);
                if (result == true){
                    mName.setText("");
                    mEmail.setText("");
                    mPassword.setText("");
                    mRePassword.setText("");

                    Toast.makeText(this, "REGISTRATION COMPLETE", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this, UserLogin.class));
                    finish();
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }

//                    final Snackbar sncakbar = Snackbar.make(v,"REGISTRATION COMPLETE",Snackbar.LENGTH_INDEFINITE);
//                    sncakbar.getView().setBackgroundColor(getResources().getColor(R.color.colorBlue));
//                    sncakbar.setActionTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                    sncakbar.show();
//                    sncakbar.setAction("LOGIN", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(Registration.this, UserLogin.class));
//                            finish();
//                        }
//                    });
                }
                else{
                    mEmail.setText("");
                    Toast.makeText(this, "USERNAME IS NOT AVAILABLE", Toast.LENGTH_SHORT).show();
//                    final Snackbar sncakbar = Snackbar.make(v,"ALREADY MEMBER",Snackbar.LENGTH_INDEFINITE);
//                    sncakbar.getView().setBackgroundColor(getResources().getColor(R.color.colorBlue));
//                    sncakbar.setActionTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                    sncakbar.show();
//                    sncakbar.setAction("NEW", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                        }
//                    });
//                    sncakbar.setAction("LOGIN", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(Registration.this, UserLogin.class));
//                            finish();
//                        }
//                    });
                }

            }
        }else{
            if(name.isEmpty())
                mName.setError("REQUIRED");
            if(email.isEmpty())
                mEmail.setError("REQUIRED");
            if(password.length()<6)
                mPassword.setError("AT LEAST 6");
            if(password.isEmpty())
                mPassword.setError("REQUIRED");
            if(!rePassword.equals(password))
                mRePassword.setError("PASSWORD NOT MATCHED");
            Toast.makeText(this, "PLEASE CHECK ERRORS", Toast.LENGTH_SHORT).show();


        }

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,UserLogin.class));
        finish();
    }
}
