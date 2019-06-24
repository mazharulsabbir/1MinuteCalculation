package com.wonder.sabbir.robin.SQLiteDatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class SQLite extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    private static final String DATABASE_NAME = "user.db";

    private static final String TABLE_NAME = "user_details";
    private static final String LAVEL_LIST = "game_lavel";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String GENDER = "GENDER";
    private static final String ADD = "Addition";
    private static final String SUB = "Subtraction";
    private static final String MUL = "Multiplication";
    private static final String DIV = "Division";
    private static final String RAND = "Random";
    private static final String SQRT = "PowerRoot";
    private static final String SQUARE = "Power";
    private static final String FACTORIAL = "Factorial";
    private static final String EMPTY = "EMPTY";
    private static final int VERSION = 5;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" + EMAIL + " VARCHAR(255) PRIMARY KEY," + NAME + " VARCHAR(255)," + PASSWORD + " VARCHAR(255));";

    private static final String LAVEL_LIST_DATABASE = "CREATE TABLE IF NOT EXISTS " + LAVEL_LIST +
            " ( " +
            EMAIL + " VARCHAR (255) PRIMARY KEY," +
            ADD + " INTEGER ," +
            SUB + " INTEGER ," +
            MUL + " INTEGER ," +
            DIV + " INTEGER ," +
            RAND + " INTEGER ," +
            SQRT + " INTEGER ," +
            SQUARE + " INTEGER ," +
            FACTORIAL + " INTEGER ," +
            EMPTY + " INTEGER ) ";

    private static final String UPGRATE = "DROP TABLE IF EXISTS " + LAVEL_LIST;
    private Context context;

    public int position = 0;

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            db.execSQL(LAVEL_LIST_DATABASE);

        } catch (SQLiteException e) {
            Log.d("SQlite Error :" + e, null);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UPGRATE);
        onCreate(db);
        Toast.makeText(context, "DATABASE UPDATED!", Toast.LENGTH_SHORT).show();
    }

    public boolean saveInfo(String fullName, String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(NAME, fullName);
        values.put(PASSWORD, password);

        long insertionConfirmation = db.insert(TABLE_NAME, null, values);

        if (insertionConfirmation == -1)
            return false;
        else
            return true;
    }

    public boolean findUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        boolean result = false;

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String mEmail = cursor.getString(0);
                String mPassword = cursor.getString(2);
                if (mEmail.equals(email) && mPassword.equals(password)) {
                    result = true;
                    break;
                }
            }
        }
        return result;

    }

    public boolean updateUser(String email, String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(NAME, name);
        values.put(PASSWORD, password);

        long insertionConfirmation = db.update(TABLE_NAME, values, EMAIL + " = ?", new String[]{email});

        if (insertionConfirmation == -1)
            return false;
        else
            return true;
    }

    public Cursor getUserName(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL + " = ?", new String[]{email});
        return cursor;
    }

    public boolean completeLavel(String playerEmail, String playName, int lavelName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, playerEmail);
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
        values.put(EMAIL, playerEmail);
        values.put(arithmaticName, lavel);

        long insert = db.update(LAVEL_LIST, values, EMAIL + " = ?", new String[]{playerEmail});
        if (insert == -1)
            return false;
        else
            return true;
    }

    public Cursor getCompletedLavel(String playerEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + LAVEL_LIST + " WHERE " +
                EMAIL + " = ?", new String[]{playerEmail});
        return cursor;
    }
}
