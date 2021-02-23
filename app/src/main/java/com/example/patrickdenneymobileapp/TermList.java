package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TermList extends AppCompatActivity {
    ListView termListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        termListView = findViewById(R.id.termListView);
        this.setTitle("Terms");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        populateTermList();
        clickOnTerm();


    }
    public void clickOnTerm(){
        termListView = findViewById(R.id.termListView);
        termListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                //Test Toast message to see that the correct item was clicked
                String message = "Clicked on " + position + "Text: " + textView.getText().toString();
                Toast.makeText(TermList.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void populateTermList(){

        //create a db object
        SchedulerDataBase db = new SchedulerDataBase(TermList.this);
        List<Term> termList = db.getAllTermsFromDB();
        List<String> termStrings = new ArrayList<>();
        for( Term term : termList) {
            termStrings.add("ID: " + term.getTermId() + " Title: " + term.getTitle() + " Start Date: " + term.getStart() + " End Date: " + term.getEnd() + " Courses: " + term.getCourses());
        }
        ArrayAdapter<String> termArray = new ArrayAdapter<String>(TermList.this, android.R.layout.simple_list_item_1, termStrings);
        termListView.setAdapter(termArray);
    }

    public void openAddTermScreen(View v){
        Intent intent = new Intent(this, TermAdd.class);
        startActivity(intent);
    }
}