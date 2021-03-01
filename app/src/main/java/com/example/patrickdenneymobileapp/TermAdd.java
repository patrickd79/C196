package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        //method to add a new term when the add term btn is clicked
    public void addTerm(View v){
        Term term;
        try{
            //create a Term object instance
            term = new Term( editTermTitle.getText().toString(),  editTermStart.getText().toString(), editTermEnd.getText().toString(), false);
            //create a db object
            Database db = new Database(TermAdd.this);
            //add the term object to the DB
            boolean added = db.addTermToDB(term);
            //if addTermToDB returns true then this Toast displays that
            Toast.makeText(TermAdd.this, "Term added: " + added, Toast.LENGTH_LONG).show();
            Intent refresh = new Intent(this, TermList.class);
            startActivity(refresh);
        }catch(Exception e){
            Toast.makeText(TermAdd.this, "Error adding term.", Toast.LENGTH_SHORT).show();
        }
    }





}