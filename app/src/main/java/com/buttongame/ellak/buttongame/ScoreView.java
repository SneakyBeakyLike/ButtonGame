package com.buttongame.ellak.buttongame;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.Currency;

public class ScoreView extends AppCompatActivity implements View.OnClickListener {

    TextView name;
    TextView hits;
    Button next;
    Button prev;
    Button back;
    SQLiteDatabase db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_view);
        name = (TextView) findViewById(R.id.scname2);
        hits = (TextView) findViewById(R.id.schits2);
        next = (Button) findViewById(R.id.next);
        prev = (Button) findViewById(R.id.prev);
        back = (Button) findViewById(R.id.back);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        back.setOnClickListener(this);
        try {
            db = openOrCreateDatabase("Score", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        }catch (SQLException e){}
        c = db.rawQuery("SELECT * FROM scores",null);
        c.moveToFirst();
        name.setText(c.getString(1));
        hits.setText(c.getString(0));

    }

    @Override
    public void onStop(){
        db.close();
        c.close();
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        if(view == next && !c.isLast()){
            c.moveToNext();
            name.setText(c.getString(1));
            hits.setText(c.getString(0));
        }
        else if(view == prev && !c.isFirst()){
            c.moveToPrevious();
            name.setText(c.getString(1));
            hits.setText(c.getString(0));
        }
        else if(view == back){
            finish();
        }
    }
}
