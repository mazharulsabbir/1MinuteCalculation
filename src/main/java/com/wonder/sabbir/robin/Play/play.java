package com.wonder.sabbir.robin.Play;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wonder.sabbir.robin.Activity.Gamelevel;
import com.wonder.sabbir.robin.Activity.MainActivity;
import com.wonder.sabbir.robin.DatabaseHelper.DatabaseConnection;
import com.wonder.sabbir.robin.R;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLiteHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class play extends AppCompatActivity {
    private static final String TAG = "play";
    private static final String NAME = "PLAY_GAME";

    private static final String ADD = "Addition";
    private static final String SUB = "Subtraction";
    private static final String MUL = "Multiplication";
    private static final String DIV = "Division";
    private static final String RAND = "Random";
    private static final String SQRT = "PowerRoot";
    private static final String SQUARE = "Power";
    private static final String FACTORIAL = "Factorial";

    private static final String TOTAL_SCORE = "totalScore";
    private static final String COLLECTION = "TOTALSCORE";
    private static final String PLAYER = "PLAYERNAME";
    private static final String PLAYER_EMAIL = "PLAYER_EMAIL";
    private static final String COLUMN_INDEX = "COLUMN_INDEX";

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    CardView btn_add, btn_sub, btn_mul, btn_div, btn_rand, btn_square, btn_sqrt, btn_fact;
    TextView sAdd, sSub, sMul, sDiv, sSqrt, sSquare, sRandom, sFactorial;
    int totalScore;
    ProgressDialog progressDialog;
    //m=marks;
    String mPlayerName, mPlayerEmail;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//jhjjhkc
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play);
        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Play");
//jshdfjhsdf
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor5 = sharedPreferences.edit();

        progressDialog = new ProgressDialog(play.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Your Scores Are Loading..!");
        progressDialog.setCancelable(false);
//        progressDialog.show();

        sAdd = findViewById(R.id.tv_add_score);
        sSub = findViewById(R.id.tv_sub_score);
        sMul = findViewById(R.id.tv_mul_score);
        sDiv = findViewById(R.id.tv_div_score);
        sSqrt = findViewById(R.id.tv_sqrt_score);
        sSquare = findViewById(R.id.tv_square_score);
        sRandom = findViewById(R.id.tv_rand_score);
        sFactorial = findViewById(R.id.tv_fact_score);

        btn_add = findViewById(R.id.btn_plus);
        btn_sub = findViewById(R.id.btn_minus);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);
        btn_rand = findViewById(R.id.btn_rand);
        btn_square = findViewById(R.id.btn_square);
        btn_sqrt = findViewById(R.id.btn_sqrt);
        btn_fact = findViewById(R.id.btn_fact);

        Intent intent = getIntent();
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        mPlayerName = (mSharedPreference.getString(PLAYER, ""));
        mPlayerEmail = (mSharedPreference.getString(PLAYER_EMAIL, ""));

        retriveAdd();
        retriveSub();
        retriveMul();
        retriveDiv();
        retriveSqrt();
        retriveSquare();
        retriveRandom();
        retriveFact();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(play.this, Gamelevel.class);
                editor5.putString(NAME, ADD);
                editor5.putString(COLUMN_INDEX, "1");
                editor5.apply();
                startActivity(i);
                finish();
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(play.this, Gamelevel.class);
                editor5.putString(NAME, SUB);
                editor5.putString(COLUMN_INDEX, "2");
                editor5.apply();
                startActivity(i);
                finish();

//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(play.this, Gamelevel.class);
                editor5.putString(NAME, MUL);
                editor5.putString(COLUMN_INDEX, "3");
                editor5.apply();
                startActivity(i);
                finish();

//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(play.this, Gamelevel.class);
                editor5.putString(NAME, DIV);
                editor5.putString(COLUMN_INDEX, "4");
                editor5.apply();
                startActivity(i);
                finish();

//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        btn_rand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(play.this, Gamelevel.class);
                editor5.putString(NAME, RAND);
                editor5.putString(COLUMN_INDEX, "5");
                editor5.apply();
                startActivity(i);
                finish();
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        btn_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(play.this, Gamelevel.class);
                editor5.putString(NAME, SQUARE);
                editor5.putString(COLUMN_INDEX, "7");
                editor5.apply();
                startActivity(i);
                finish();

//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        btn_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(play.this, Gamelevel.class);
                editor5.putString(NAME, SQRT);
                editor5.putString(COLUMN_INDEX, "6");
                editor5.apply();
                startActivity(i);
                finish();

//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        btn_fact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(play.this, Gamelevel.class);
                editor5.putString(NAME, FACTORIAL);
                editor5.putString(COLUMN_INDEX, "8");
                editor5.apply();
                startActivity(i);
                finish();

//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

    public void retriveAdd() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(2));
            }
            sAdd.setText("Total Score: " +
                    NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(stringBuffer.toString())));
            totalScore += Integer.parseInt(stringBuffer.toString());
        }
    }

    public void retriveSub() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(3));
            }
            sSub.setText("Total Score: " +
                    NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(stringBuffer.toString())));
            totalScore += Integer.parseInt(stringBuffer.toString());
        }

    }

    public void retriveMul() {


        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(4));
            }
            sMul.setText("Total Score: " +
                    NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(stringBuffer.toString())));
            totalScore += Integer.parseInt(stringBuffer.toString());
        }

    }

    public void retriveDiv() {


        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(5));
            }
            sDiv.setText("Total Score: " +
                    NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(stringBuffer.toString())));
            totalScore += Integer.parseInt(stringBuffer.toString());
        }
    }

    public void retriveRandom() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(6));
            }
            sRandom.setText("Total Score: " +
                    NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(stringBuffer.toString())));
            totalScore += Integer.parseInt(stringBuffer.toString());
        }

    }

    public void retriveSqrt() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(7));
            }
            sSqrt.setText("Total Score: " +
                    NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(stringBuffer.toString())));
            totalScore += Integer.parseInt(stringBuffer.toString());
        }
    }

    public void retriveSquare() {

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(8));
            }
            sSquare.setText("Total Score: " +
                    NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(stringBuffer.toString())));
            totalScore += Integer.parseInt(stringBuffer.toString());
        }
    }

    @SuppressLint("SetTextI18n")
    public void retriveFact() {


        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getSingleData(mPlayerEmail);

        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(9));
            }
            //stringBuffer.toString()

            sFactorial.setText("Total Score: " +
                    NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(stringBuffer.toString())));
            totalScore += Integer.parseInt(stringBuffer.toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.user_help:
                return true;
            //Code
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(play.this, MainActivity.class));
        finish();
    }
}
