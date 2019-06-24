package com.wonder.sabbir.robin.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wonder.sabbir.robin.Play.Add;
import com.wonder.sabbir.robin.Play.Div;
import com.wonder.sabbir.robin.Play.Factorial;
import com.wonder.sabbir.robin.Play.Mul;
import com.wonder.sabbir.robin.Play.Rand;
import com.wonder.sabbir.robin.Play.Sqrt;
import com.wonder.sabbir.robin.Play.Square;
import com.wonder.sabbir.robin.Play.Sub;
import com.wonder.sabbir.robin.Play.play;
import com.wonder.sabbir.robin.R;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLite;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLiteHelper;

public class Gamelevel extends AppCompatActivity {

    private static final String PLAYER_EMAIL = "PLAYER_EMAIL";
    private static final String NAME = "PLAY_GAME";
    private static final String PLAYER = "PLAYERNAME";

    private static final String ADD = "Addition";
    private static final String SUB = "Subtraction";
    private static final String MUL = "Multiplication";
    private static final String DIV = "Division";
    private static final String RAND = "Random";
    private static final String SQRT = "PowerRoot";
    private static final String SQUARE = "Power";
    private static final String FACTORIAL = "Factorial";
    private static final String LAVEL = "LAVEL";
    private static final String EMPTY = "totalScore";
    private static final String COLUMN_INDEX = "COLUMN_INDEX";

    String mPlayerName, mPlayName, checkLavel = "1", mPlayerEmail, mColumnIndex;

    SharedPreferences prefs;
    SharedPreferences.Editor editor1;

    int indexNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FINAL
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gamelevel);

        //TOOLBAR
        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Game Lavel");

        //SHARED PREFERENCE MANAGER
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor1 = prefs.edit();

        //GET STRING EXTRA
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        mPlayerName = (mSharedPreference.getString(PLAYER, ""));
        mPlayerEmail = (mSharedPreference.getString(PLAYER_EMAIL, ""));
        mPlayName = (mSharedPreference.getString(NAME, ""));
        mColumnIndex = mSharedPreference.getString(COLUMN_INDEX, "1");
        checkColumnIndex();
        saveLavel();
        playerLavel();
        checkPlayerLavel();

    }

    public void lavel1(View v) {

        Intent i;
        switch (mPlayName) {
            case ADD:
                i = new Intent(Gamelevel.this, Add.class);
                editor1.putString(LAVEL, "1");
                editor1.apply();
                startActivity(i);
                finish();
                break;
            case SUB:
                i = (new Intent(this, Sub.class));
                editor1.putString(LAVEL, "1");
                editor1.apply();
                startActivity(i);
                finish();
                break;
            case MUL:
                i = (new Intent(this, Mul.class));
                editor1.putString(LAVEL, "1");
                editor1.apply();
                startActivity(i);
                finish();
                break;
            case DIV:
                i = (new Intent(this, Div.class));
                editor1.putString(LAVEL, "1");
                editor1.apply();
                startActivity(i);
                finish();
                break;
            case SQRT:
                i = (new Intent(this, Sqrt.class));
                editor1.putString(LAVEL, "1");
                editor1.apply();
                startActivity(i);
                finish();
                break;
            case SQUARE:
                i = (new Intent(this, Square.class));
                editor1.putString(LAVEL, "1");
                editor1.apply();
                startActivity(i);
                finish();
                break;
            case FACTORIAL:
                i = (new Intent(this, Factorial.class));
                editor1.putString(LAVEL, "1");
                editor1.apply();
                startActivity(i);
                finish();
                break;
            case RAND:
                i = new Intent(this, Rand.class);
                editor1.putString(LAVEL, "1");
                editor1.apply();
                startActivity(i);
                finish();
                break;

        }

    }

    public void lavel2(View v) {

        if (Integer.parseInt(checkLavel) >= 2) {
            Intent i;
            String laval = "2";

            switch (mPlayName) {
                case ADD:
                    i = new Intent(Gamelevel.this, Add.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SUB:
                    i = (new Intent(this, Sub.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case MUL:
                    i = (new Intent(this, Mul.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case DIV:
                    i = (new Intent(this, Div.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQRT:
                    i = (new Intent(this, Sqrt.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQUARE:
                    i = (new Intent(this, Square.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case FACTORIAL:
                    i = (new Intent(this, Factorial.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case RAND:
                    i = new Intent(this, Rand.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
            }
        } else
            Toast.makeText(this, "Please Unlock First", Toast.LENGTH_SHORT).show();

    }

    public void lavel3(View v) {
        if (Integer.parseInt(checkLavel) >= 3) {
            Intent i;
            String laval = "3";

            switch (mPlayName) {
                case ADD:
                    i = new Intent(Gamelevel.this, Add.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SUB:
                    i = (new Intent(this, Sub.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case MUL:
                    i = (new Intent(this, Mul.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case DIV:
                    i = (new Intent(this, Div.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQRT:
                    i = (new Intent(this, Sqrt.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQUARE:
                    i = (new Intent(this, Square.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case FACTORIAL:
                    i = (new Intent(this, Factorial.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case RAND:
                    i = new Intent(this, Rand.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;

            }
        } else
            Toast.makeText(this, "Please Unlock First", Toast.LENGTH_SHORT).show();

    }

    public void lavel4(View v) {
        if (Integer.parseInt(checkLavel) >= 4) {
            Intent i;
            String laval = "4";

            switch (mPlayName) {
                case ADD:
                    i = new Intent(Gamelevel.this, Add.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SUB:
                    i = (new Intent(this, Sub.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case MUL:
                    i = (new Intent(this, Mul.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case DIV:
                    i = (new Intent(this, Div.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQRT:
                    i = (new Intent(this, Sqrt.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQUARE:
                    i = (new Intent(this, Square.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case FACTORIAL:
                    i = (new Intent(this, Factorial.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case RAND:
                    i = new Intent(this, Rand.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;

            }
        } else
            Toast.makeText(this, "Please Unlock First", Toast.LENGTH_SHORT).show();

    }

    public void lavel5(View v) {
        if (Integer.parseInt(checkLavel) >= 5) {
            Intent i;
            String laval = "5";
            switch (mPlayName) {
                case ADD:
                    i = new Intent(Gamelevel.this, Add.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SUB:
                    i = (new Intent(this, Sub.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case MUL:
                    i = (new Intent(this, Mul.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case DIV:
                    i = (new Intent(this, Div.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQRT:
                    i = (new Intent(this, Sqrt.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQUARE:
                    i = (new Intent(this, Square.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case FACTORIAL:
                    i = (new Intent(this, Factorial.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case RAND:
                    i = new Intent(this, Rand.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;

            }
        } else
            Toast.makeText(this, "Please Unlock First", Toast.LENGTH_SHORT).show();

    }

    public void lavel6(View v) {
        if (Integer.parseInt(checkLavel) >= 6) {
            Intent i;
            String laval = "6";

            switch (mPlayName) {
                case ADD:
                    i = new Intent(Gamelevel.this, Add.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SUB:
                    i = (new Intent(this, Sub.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case MUL:
                    i = (new Intent(this, Mul.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case DIV:
                    i = (new Intent(this, Div.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQRT:
                    i = (new Intent(this, Sqrt.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQUARE:
                    i = (new Intent(this, Square.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case FACTORIAL:
                    i = (new Intent(this, Factorial.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case RAND:
                    i = new Intent(this, Rand.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;

            }
        } else
            Toast.makeText(this, "Please Unlock First", Toast.LENGTH_SHORT).show();

    }

    public void lavel7(View v) {
        if (Integer.parseInt(checkLavel) >= 7) {
            Intent i;
            String laval = "7";
            switch (mPlayName) {
                case ADD:
                    i = new Intent(Gamelevel.this, Add.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SUB:
                    i = (new Intent(this, Sub.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case MUL:
                    i = (new Intent(this, Mul.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case DIV:
                    i = (new Intent(this, Div.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQRT:
                    i = (new Intent(this, Sqrt.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQUARE:
                    i = (new Intent(this, Square.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case FACTORIAL:
                    i = (new Intent(this, Factorial.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case RAND:
                    i = new Intent(this, Rand.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;

            }
        } else
            Toast.makeText(this, "Please Unlock First", Toast.LENGTH_SHORT).show();

    }

    public void lavel8(View v) {
        if (Integer.parseInt(checkLavel) >= 8) {
            Intent i;
            String laval = "8";
            switch (mPlayName) {
                case ADD:
                    i = new Intent(Gamelevel.this, Add.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SUB:
                    i = (new Intent(this, Sub.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case MUL:
                    i = (new Intent(this, Mul.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case DIV:
                    i = (new Intent(this, Div.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQRT:
                    i = (new Intent(this, Sqrt.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQUARE:
                    i = (new Intent(this, Square.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case FACTORIAL:
                    i = (new Intent(this, Factorial.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case RAND:
                    i = new Intent(this, Rand.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;

            }
        } else
            Toast.makeText(this, "Please Unlock First", Toast.LENGTH_SHORT).show();

    }

    public void lavel9(View v) {
        if (Integer.parseInt(checkLavel) >= 9) {
            Intent i;
            String laval = "9";
            switch (mPlayName) {
                case ADD:
                    i = new Intent(Gamelevel.this, Add.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SUB:
                    i = (new Intent(this, Sub.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case MUL:
                    i = (new Intent(this, Mul.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case DIV:
                    i = (new Intent(this, Div.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQRT:
                    i = (new Intent(this, Sqrt.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQUARE:
                    i = (new Intent(this, Square.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case FACTORIAL:
                    i = (new Intent(this, Factorial.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case RAND:
                    i = new Intent(this, Rand.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;

            }
        } else
            Toast.makeText(this, "Please Unlock First", Toast.LENGTH_SHORT).show();

    }

    public void lavel10(View v) {
        if (Integer.parseInt(checkLavel) >= 10) {
            Intent i;
            String laval = "10";
            switch (mPlayName) {
                case ADD:
                    i = new Intent(Gamelevel.this, Add.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SUB:
                    i = (new Intent(this, Sub.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case MUL:
                    i = (new Intent(this, Mul.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case DIV:
                    i = (new Intent(this, Div.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQRT:
                    i = (new Intent(this, Sqrt.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case SQUARE:
                    i = (new Intent(this, Square.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case FACTORIAL:
                    i = (new Intent(this, Factorial.class));
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;
                case RAND:
                    i = new Intent(this, Rand.class);
                    editor1.putString(LAVEL, laval);
                    editor1.apply();
                    startActivity(i);
                    finish();
                    break;

            }
        } else
            Toast.makeText(this, "Please Unlock First", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Gamelevel.this, play.class));
        finish();
    }

    public void saveLavel() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        boolean result = sqLiteHelper.completeLavel(mPlayerEmail, "totalScore", 3);
        if (!result)
            sqLiteHelper.updateLavel(mPlayerEmail, "totalScore", 3);
    }

    public void playerLavel() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Cursor cursor = sqLiteHelper.getCompletedLavel(mPlayerEmail);
        if (cursor.getCount() != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getInt(indexNumber));
            }
            cursor.close();
            checkLavel = (stringBuffer.toString());
            cursor.close();
            if (checkLavel.equals("") | checkLavel.equals("0"))
                checkLavel = "1";
        }
        sqLiteHelper.close();

    }

    private void checkPlayerLavel() {
        Button lvl1, lvl2, lvl3, lvl4, lvl5, lvl6, lvl7, lvl8, lvl9, lvl10;
        lvl1 = findViewById(R.id.lavel_play);
        lvl2 = findViewById(R.id.lavel_play2);
        lvl3 = findViewById(R.id.lavel_play3);
        lvl4 = findViewById(R.id.lavel_play4);
        lvl5 = findViewById(R.id.lavel_play5);
        lvl6 = findViewById(R.id.lavel_play6);
        lvl7 = findViewById(R.id.lavel_play7);
        lvl8 = findViewById(R.id.lavel_play8);
        lvl9 = findViewById(R.id.lavel_play9);
        lvl10 = findViewById(R.id.lavel_play10);

        switch (checkLavel) {
            case "1":
                lvl1.setText("Play!");
                break;

            case "2":
                lvl1.setText("Play!");
                lvl2.setText("Play!");
                break;
            case "3":
                lvl1.setText("Play!");
                lvl2.setText("Play!");
                lvl3.setText("Play!");
                break;
            case "4":
                lvl1.setText("Play!");
                lvl2.setText("Play!");
                lvl3.setText("Play!");
                lvl4.setText("Play!");
                break;
            case "5":
                lvl1.setText("Play!");
                lvl2.setText("Play!");
                lvl3.setText("Play!");
                lvl4.setText("Play!");
                lvl5.setText("Play!");
                break;
            case "6":
                lvl1.setText("Play!");
                lvl2.setText("Play!");
                lvl3.setText("Play!");
                lvl4.setText("Play!");
                lvl5.setText("Play!");
                lvl6.setText("Play!");
                break;
            case "7":
                lvl1.setText("Play!");
                lvl2.setText("Play!");
                lvl3.setText("Play!");
                lvl4.setText("Play!");
                lvl5.setText("Play!");
                lvl6.setText("Play!");
                lvl7.setText("Play!");
                break;
            case "8":
                lvl1.setText("Play!");
                lvl2.setText("Play!");
                lvl3.setText("Play!");
                lvl4.setText("Play!");
                lvl5.setText("Play!");
                lvl6.setText("Play!");
                lvl7.setText("Play!");
                lvl8.setText("Play!");
                break;
            case "9":
                lvl1.setText("Play!");
                lvl2.setText("Play!");
                lvl3.setText("Play!");
                lvl4.setText("Play!");
                lvl5.setText("Play!");
                lvl6.setText("Play!");
                lvl7.setText("Play!");
                lvl8.setText("Play!");
                lvl9.setText("Play!");
                break;

            case "10":
                lvl1.setText("Play!");
                lvl2.setText("Play!");
                lvl3.setText("Play!");
                lvl4.setText("Play!");
                lvl5.setText("Play!");
                lvl6.setText("Play!");
                lvl7.setText("Play!");
                lvl8.setText("Play!");
                lvl9.setText("Play!");
                lvl10.setText("Play!");
                break;

        }
    }

    private void checkColumnIndex() {
        switch (mColumnIndex) {
            case "1":
                indexNumber = 1;
                break;
            case "2":
                indexNumber = 2;
                break;
            case "3":
                indexNumber = 3;
                break;
            case "4":
                indexNumber = 4;
                break;
            case "5":
                indexNumber = 5;
                break;
            case "6":
                indexNumber = 6;
                break;
            case "7":
                indexNumber = 7;
                break;
            case "8":
                indexNumber = 8;
                break;
            case "9":
                indexNumber = 9;
                break;
            case "10":
                indexNumber = 10;
                break;


        }
    }

}
