package com.wonder.sabbir.robin.SQLiteDatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Gravity;
import android.widget.Toast;

import com.wonder.sabbir.robin.R;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "score_list.db";
    public static final int DATABASE_VERSION = 11;

    private static final String SCORE_LIST = "score_list";
    private static final String SCORE_HISTORY = "score_history";
    private static final String LAVEL_LIST = "game_lavel";
    private static final String PLAYER_NAME = "PLAYER";
    private static final String PLAYER_EMAIL = "Email";

    private static final String ADD = "Addition";
    private static final String SUB = "Subtraction";
    private static final String MUL = "Multiplication";
    private static final String DIV = "Division";
    private static final String RAND = "Random";
    private static final String SQRT = "PowerRoot";
    private static final String SQUARE = "Power";
    private static final String FACTORIAL = "Factorial";
    private static final String TOTAL_SCORE = "totalScore";

    private static final String SCORE_HISTORY_DATABASE = "CREATE TABLE " + SCORE_HISTORY + " ( " +
            PLAYER_EMAIL + " VARCHAR (255), HISTORY TEXT , SCORE INTEGER )";


    private static final String SCORE_LIST_DATABASE = "CREATE TABLE " + SCORE_LIST +
            " ( " +
            PLAYER_EMAIL + " VARCHAR (255) PRIMARY KEY," +
            PLAYER_NAME + " VARCHAR (255)," +
            ADD + " INTEGER ," +
            SUB + " INTEGER ," +
            MUL + " INTEGER ," +
            DIV + " INTEGER ," +
            RAND + " INTEGER ," +
            SQRT + " INTEGER ," +
            SQUARE + " INTEGER ," +
            FACTORIAL + " INTEGER ," +
            TOTAL_SCORE + " INTEGER ) ";

    private static final String LAVEL_LIST_DATABASE = "CREATE TABLE " + LAVEL_LIST +
            " ( " +
            PLAYER_EMAIL + " VARCHAR (255) PRIMARY KEY," +
            ADD + " INTEGER ," +
            SUB + " INTEGER ," +
            MUL + " INTEGER ," +
            DIV + " INTEGER ," +
            RAND + " INTEGER ," +
            SQRT + " INTEGER ," +
            SQUARE + " INTEGER ," +
            FACTORIAL + " INTEGER ," +
            TOTAL_SCORE + " INTEGER ) ";

    private static final String UPGRATE = "DROP TABLE IF EXISTS " + SCORE_LIST;

    public int position = 0;

    public Context context;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SCORE_HISTORY_DATABASE);
        db.execSQL(SCORE_LIST_DATABASE);
        db.execSQL(LAVEL_LIST_DATABASE);

        Toast toast = Toast.makeText(context, "WELCOME TO BRAIN GAME", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UPGRATE);
        onCreate(db);
    }

    public boolean saveScores(String playerEmail, String playerName, String playName, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLAYER_EMAIL, playerEmail);
        values.put(PLAYER_NAME, playerName);
        values.put(playName, score);

        long insert = db.insert(SCORE_LIST, null, values);
        if (insert == -1)
            return false;
        else
            return true;
    }

    public boolean updateEmai_Name(String playerEmail, String playerName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLAYER_EMAIL, playerEmail);
        values.put(PLAYER_NAME, playerName);

        long insert = db.update(SCORE_LIST, values, PLAYER_EMAIL + " = ?", new String[]{playerEmail});
        if (insert == -1)
            return false;
        else
            return true;
    }

    public boolean updateScore(String playerEmail, String playerName, String arithmaticName, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLAYER_EMAIL, playerEmail);
        values.put(PLAYER_NAME, playerName);
        values.put(arithmaticName, score);

        long insert = db.update(SCORE_LIST, values, PLAYER_EMAIL + " = ?", new String[]{playerEmail});
        if (insert == -1)
            return false;
        else
            return true;
    }

    public Cursor getSingleData(String playerEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SCORE_LIST + " WHERE " +
                PLAYER_EMAIL + " = ?", new String[]{playerEmail});
        return cursor;
    }

    public Cursor getHighScore() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + SCORE_LIST + " ORDER BY " +
                TOTAL_SCORE + " ASC", null);
        return res;
    }

    public boolean userPosition(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SCORE_LIST + " ORDER BY " +
                TOTAL_SCORE + " DESC", null);
        boolean result = false;

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String mEmail = cursor.getString(0);
                position++;
                if (mEmail.equals(email)) {
                    result = true;
                    break;
                }
            }
        }
        return result;

    }

    public boolean insertHistory(String email, String historyName, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PLAYER_EMAIL, email);
        values.put("HISTORY", historyName);
        values.put("SCORE", score);

        long result = db.insert(SCORE_HISTORY, null, values);
        if (result == -1) {
            return false;
        } else
            return true;

    }

    public Cursor getHistory(String playerEmail) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + SCORE_HISTORY + " WHERE " +
                PLAYER_EMAIL + " = ?", new String[]{playerEmail}, null);

        return res;
    }

    public Cursor getLeaders() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + SCORE_LIST + " ORDER BY " +
                TOTAL_SCORE + " DESC", null);
        return res;
    }

    public boolean completeLavel(String playerEmail, String playName, int lavelName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLAYER_EMAIL, playerEmail);
        values.put(playName, lavelName);

        long insert = db.insert(LAVEL_LIST, null, values);
        if (insert == -1)
            return false;
        else
            return true;
    }

    public boolean updateLavel(String playerEmail, String arithmaticName, int lavel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(arithmaticName, lavel);

        long insert = db.update(LAVEL_LIST, values, PLAYER_EMAIL + " = ?", new String[]{playerEmail});
        if (insert == -1)
            return false;
        else
            return true;
    }

    public Cursor getCompletedLavel(String playerEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + LAVEL_LIST + " WHERE " +
                PLAYER_EMAIL + " = ?", new String[]{playerEmail});
        return cursor;
    }

}
