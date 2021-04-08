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
    //database object instance
    Database db;
    //list containing the terms to display
    List<Term> termList;
    //list containing the associated courses for the term
    List<Course> courseList;
    public static String courses = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        termListView = findViewById(R.id.termListView);
        this.setTitle("Terms");
        //back button to parent activity
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //add terms to the displayed list
        populateTermList();
        //open the EditTerm activity for a term when it is clicked
        clickOnTerm();
    }
    protected void onResume(){
        super.onResume();
        //clear the old term list
        if(termList!=null){
            termList.clear();
        }
        if(courseList!=null){
            courseList.clear();
        }

        //repopulate list after the update or deletion from EditTerm
        populateTermList();
    }

    public void clickOnTerm(){
        termListView = findViewById(R.id.termListView);
        termListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView item = (TextView) itemClicked;

                //Test message to see that the correct item was clicked
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
        db = new Database(TermList.this);
        //get a list of the terms from the DB object
        termList = db.getAllTermsFromDB();

        List<String> termStrings = new ArrayList<>();
        for( Term term : termList) {
            //get associated courses for this term
            courseList = db.getCoursesForASpecificTermFromDB(term);
            //convert the associated courses to a string to append to the termStrings list
            StringBuilder str = new StringBuilder();
                for (int i = 0; i < courseList.size(); i++) {
                    if (i == (courseList.size() - 1)) {
                        str.append(courseList.get(i).getCourseTitle());
                    } else {
                        str.append(courseList.get(i).getCourseTitle()).append(", ");
                    }

                }

                courses = str.toString();
                termStrings.add("ID: " + term.getTermId() + "\nTitle: " + term.getTitle() + "\nStart Date: " + term.getStart() + "\nEnd Date: " + term.getEnd() + "\nCourses: " + courses);

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
        intent.putExtra("courses", courses.toString());
            startActivity(intent);


    }
}