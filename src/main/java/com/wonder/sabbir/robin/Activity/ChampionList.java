package com.wonder.sabbir.robin.Activity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wonder.sabbir.robin.R;
import com.wonder.sabbir.robin.SQLiteDatabaseHelper.SQLiteHelper;

import java.util.ArrayList;

public class ChampionList extends AppCompatActivity {
    private static final String PLAYER = "PLAYER_EMAIL";
    private static final String PLAYER_EMAIL = "PLAYER_EMAIL";

    private ListView listView;
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_champion_list);
        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("History");

        listView = findViewById(R.id.listView);
        sqLiteHelper = new SQLiteHelper(this);
        loadData();
    }

    public void loadData() {
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String mPlayerEmail = (mSharedPreference.getString(PLAYER_EMAIL, ""));
        ArrayList<String> listName = new ArrayList<>();
        Cursor cursor = sqLiteHelper.getLeaders();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                listName.add(cursor.getString(1) + "\n" +
                        "\tTotal Score: " + cursor.getInt(10));
            }
        }
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                R.layout.store_all_data_to_listview,
                R.id.listView, listName);

        listView.setAdapter(adapter);
    }


}
