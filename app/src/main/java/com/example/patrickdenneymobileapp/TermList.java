package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TermList extends AppCompatActivity {
    ListView termListView;
    public static long termID;


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
                TextView item = (TextView) itemClicked;

                //Test Toast message to see that the correct item was clicked
                String message = "Clicked on " + position + " Text: " + item.getText().toString();
                Log.d("message ", message);
                Log.d("Term ID ", String.valueOf(id));
                termID =  id;
                openEditTermScreen();
            }
        });
    }

    public void populateTermList(){
        //create a db object
        Database db = new Database(TermList.this);
        //get a list of the terms from the DB object
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

    public void openEditTermScreen(){
        Intent intent = new Intent(this, EditTerm.class);
        intent.putExtra("id", termID);
            startActivity(intent);


    }
}