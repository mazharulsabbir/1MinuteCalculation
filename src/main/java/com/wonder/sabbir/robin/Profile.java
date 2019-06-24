package com.wonder.sabbir.robin;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wonder.sabbir.robin.Activity.MainActivity;
import com.wonder.sabbir.robin.Activity.Settings;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLite;

public class Profile extends AppCompatActivity {
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
        setContentView(R.layout.activity_profile);
        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Profile");
    }
    public void updateInfo(View v) {
        SQLite sqLite = new SQLite(this);
        String email = userName_Email;
        String name = mName.getText().toString();
        String password = mRePassword.getText().toString();

        if(!name.isEmpty() && !password.isEmpty() && password.length()>=6){
            boolean result = sqLite.updateUser(email, name, password);
            if (result == true) {
                mName.setText("");
                mRePassword.setText("");
                Toast.makeText(this, "UPDATE COMPLETE", Toast.LENGTH_SHORT).show();
                final Snackbar sncakbar = Snackbar.make(v, "UPDATE COMPLETE. BACK HOME ?", Snackbar.LENGTH_INDEFINITE);
                sncakbar.getView().setBackgroundColor(getResources().getColor(R.color.colorToolbar));
                sncakbar.setActionTextColor(getResources().getColor(R.color.colorPrimaryDark));
                sncakbar.show();
                sncakbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Profile.this, MainActivity.class));
                        finish();
                    }
                });

            } else {
                Toast.makeText(this, "ERROR TO UPDATE", Toast.LENGTH_SHORT).show();

                final Snackbar sncakbar = Snackbar.make(v, "ERROR TO UPDATE", Snackbar.LENGTH_INDEFINITE);
                sncakbar.getView().setBackgroundColor(getResources().getColor(R.color.colorToolbar));
                sncakbar.setActionTextColor(getResources().getColor(R.color.colorPrimaryDark));
                sncakbar.show();
                sncakbar.setAction("TRY AGAIN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }else Toast.makeText(this, "NAME & PASSWORD REQUIRED.", Toast.LENGTH_SHORT).show();

    }

    public void profileUpdate(View v){
        startActivity(new Intent(this,Settings.class));
        finish();
    }

}
