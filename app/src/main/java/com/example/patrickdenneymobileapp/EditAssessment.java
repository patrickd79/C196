package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.patrickdenneymobileapp.Database.assessmentList;
import static com.example.patrickdenneymobileapp.Database.courseList;

public class EditAssessment extends AppCompatActivity {
    public TextView editAssessmentIDTV;
    public TextView editAssessmentTitleTV;
    public TextView editAssessEndDateTV;
    public Spinner editAssessPerfObjSpinner;
    public Spinner editAssessAssociatedCourseSpinner;
    public static long assessID;
    public Assessment assessment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        this.setTitle("Edit an Assessment");
        //get courseID from CourseList
        Intent intent = getIntent();
        assessID = intent.getLongExtra("id", AssessmentList.assessID);
        assessment = assessmentList.get((int) assessID);
        //set views to ids
        editAssessmentIDTV = findViewById(R.id.editAssessmentIDTV);
        editAssessmentTitleTV = findViewById(R.id.editAssessmentTitleTV);
        editAssessEndDateTV = findViewById(R.id.editAssessEndDateTV);
        editAssessPerfObjSpinner = findViewById(R.id.editAssessPerfObjSpinner);
        editAssessAssociatedCourseSpinner = findViewById(R.id.editAssessAssociatedCourseSpinner);

        loadAssessment(assessID);
    }




    private void loadAssessment(long id) {
        Database db = new Database(EditAssessment.this);
        //get assessment object from the assessment list of all assessments in the Database class
        Assessment assessment = assessmentList.get((int) id);
        //place the assessment information in the text fields
        String titleField = "Assessment ID: " + assessment.getAssessmentID();
        editAssessmentIDTV.setText(titleField);
        editAssessmentTitleTV.setText(assessment.getAssessmentTitle());
        editAssessEndDateTV.setText(assessment.getAssessmentEndDate());
        //NEED TO SET SPINNERS TO CORRECT INFORMATION

        //NEED TO CREATE DB METHODS TO FACILITATE THIS

        //NEED METHODS HERE TO FILL ARRAYS WITH THE STRINGS TO FEED THE SPINNERS

        //need to check if assessment is perf or obj and then set spinner accordingly

        //need to do same with associated course as well as set other choices there




    }










}