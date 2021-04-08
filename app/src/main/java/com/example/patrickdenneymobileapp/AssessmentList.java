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
        clickOnAssessment();
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

    public void clickOnAssessment(){
        assessListView = findViewById(R.id.addAssessListView);
        assessListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView item = (TextView) itemClicked;

                //Test message to see that the correct item was clicked
                String message = "Clicked on " + position + " Text: " + item.getText().toString();
                Log.d("message ", message);
                Log.d("Course ID ", String.valueOf(id));
                assessID =  id;
                openEditAssessmentScreen();
            }
        });
    }

    public void openAddAssessmentScreen(View v){
        Intent intent = new Intent(this, AssessmentAdd.class);
        startActivity(intent);
    }
    public void openEditAssessmentScreen(){
        Intent intent = new Intent(this, EditAssessment.class);
        intent.putExtra("id", assessID);
        startActivity(intent);
    }
}