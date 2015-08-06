package com.buttongame.ellak.buttongame;

import android.app.Activity;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ScoreSubmit extends Activity {

    Button insert;
    EditText nametext;
    SQLiteDatabase db;
    int hits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_submit);

        insert = (Button) findViewById(R.id.insert);
        nametext = (EditText) findViewById(R.id.name);
        hits = getIntent().getExtras().getInt("score");
        try {
            db = openOrCreateDatabase("Score", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL("create table if not exists scores(hits integer,name text)");
        } catch (SQLException e) {}
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("hits", hits);
                values.put("name", nametext.getText().toString());
                if (db.insert("scores", null, values) != -1) {
                    Toast.makeText(ScoreSubmit.this, "Success", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ScoreSubmit.this, "Failed", Toast.LENGTH_LONG).show();
                }
                finish();

            }
        });

    }

    @Override
    protected void onStop() {
        db.close();
        super.onStop();

    }

}
