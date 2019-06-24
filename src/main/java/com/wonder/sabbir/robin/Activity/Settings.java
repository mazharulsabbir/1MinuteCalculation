package com.wonder.sabbir.robin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wonder.sabbir.robin.Profile;
import com.wonder.sabbir.robin.R;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLite;
import com.wonder.sabbir.robin.User_LogOn_Reg.UserLogin;

public class Settings extends AppCompatActivity {
    private static final String PLAYER_NAME = "PLAYERNAME";
    private static final String PLAYER_EMAIL = "PLAYER_EMAIL";

    EditText mName, mEmail, mPassword, mRePassword;
    String userName_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Settings");

    }
    public void profileSettings(View v){
        startActivity(new Intent(this, Profile.class));
        finish();
    }
    public void myHistory(View v) {
        startActivity(new Intent(this, LeaderBoard.class));
    }
    public void logoutUser(View v){
        startActivity(new Intent(this, UserLogin.class));
        finish();
    }
}
