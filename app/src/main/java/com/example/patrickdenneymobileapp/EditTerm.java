package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        //get termID from TermList
        Intent intent = getIntent();
        long termID = intent.getLongExtra("id", TermList.termID);
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
            //
            //
            //
            //need to retrieve the 1 term by ID from the database. Need to create a method in the DB to return the term object by ID, then can update by using setters.
            //
            //
            //
        }catch(Exception e){
            Toast.makeText(EditTerm.this, "Error updating term.", Toast.LENGTH_SHORT).show();
        }
    }

}