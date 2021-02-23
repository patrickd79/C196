package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class EditTerm extends AppCompatActivity {


    //NEED TO ADD FUNCTIONALITY FOR UPDATING NEW TERM INFORMATION AND DELETING OLD TERM INFORMATION


    //declare the View components
    EditText editTermTitle;
    EditText editTermStart;
    EditText editTermEnd;
    Button addTermBtn;
    // create the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        this.setTitle("Edit a Term");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //set the view components to their ids
        editTermTitle = findViewById(R.id.editTermTitle);
        editTermStart = findViewById(R.id.editTermStart);
        editTermEnd = findViewById(R.id.editTermEnd);
        addTermBtn = findViewById(R.id.addTermBtn);

    }
    //method to add a new term when the add term btn is clicked
    public void updateTerm(View v){
        Term term;
        try{
            //create a Term object instance
            term = new Term( -1, editTermTitle.getText().toString(),  editTermStart.getText().toString(), editTermEnd.getText().toString(), false);
            //create a db object
            SchedulerDataBase db = new SchedulerDataBase(EditTerm.this);
            //add the term object to the DB
            boolean added = db.addTermToDB(term);
            //if addTermToDB returns true then this Toast displays that
            Toast.makeText(EditTerm.this, "Term updated: " + added, Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(EditTerm.this, "Error updating term.", Toast.LENGTH_SHORT).show();
        }
    }

}