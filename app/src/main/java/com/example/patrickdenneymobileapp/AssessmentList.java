package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssessmentList extends AppCompatActivity {
    ListView assessListView;
    public static long assessID;
    //database object instance
    Database db;
    //list containing the terms to display
    List<Assessment> assessmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        assessListView = findViewById(R.id.addAssessListView);
        this.setTitle("Assessments");
        // back button to parent activity
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        populateAssessmentList();
    }

    protected void onResume(){
        super.onResume();
        if(assessmentList != null){
            assessmentList.clear();
        }

        //repopulate list after the update or deletion from EditTerm
        populateAssessmentList();
    }

    public void populateAssessmentList(){
        //create a db object
        db = new Database(AssessmentList.this);
        //get a list of the terms from the DB object
        assessmentList = db.getAllAssessmentsFromDB();

        List<String> assessStrings = new ArrayList<>();
        for( Assessment assess : assessmentList) {

            assessStrings.add("ID:  " + assess.getAssessmentID() + "    Title:  " + assess.getAssessmentTitle() + "\nAssessment Type:  "+ assess.getPerfOrObjective()+"\nEnd date:  " + assess.getAssessmentEndDate()+
            "\nCourse:  "+ assess.getAssociatedCourseTitle());
        }
        ArrayAdapter<String> assessmentArray = new ArrayAdapter<String>(AssessmentList.this, android.R.layout.simple_list_item_1, assessStrings);
        assessListView.setAdapter(assessmentArray);
    }

    public void openAddAssessmentScreen(View v){
        Intent intent = new Intent(this, AssessmentAdd.class);
        startActivity(intent);
    }
}