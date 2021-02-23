package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class TermAdd extends AppCompatActivity {
    //declare the View components
    EditText editTermTitle;
    EditText editTermStart;
    EditText editTermEnd;
    Button addTermBtn;
    // create the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_add);
        this.setTitle("Add a Term");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //set the view components to their ids
         editTermTitle = findViewById(R.id.editTermTitle);
         editTermStart = findViewById(R.id.editTermStart);
         editTermEnd = findViewById(R.id.editTermEnd);
         addTermBtn = findViewById(R.id.addTermBtn);

    }



    public void addTerm(View v){
        try{
            //create a Term object instance
            Term term = new Term( editTermTitle.getText().toString(),  editTermStart.getText().toString(), editTermEnd.getText().toString(), false);
            Toast.makeText(TermAdd.this, "" + term.getCourses(), Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(TermAdd.this, "Error adding term.", Toast.LENGTH_SHORT).show();
        }
    }





}