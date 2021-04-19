package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.patrickdenneymobileapp.Database.assessmentList;
import static com.example.patrickdenneymobileapp.Database.courseList;
import static com.example.patrickdenneymobileapp.Database.termList;

public class EditAssessment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public TextView editAssessmentIDTV;
    public TextView editAssessmentTitleTV;
    public TextView editAssessEndDateTV;
    public TextView currentTypeExamLabel;
    public Spinner editAssessPerfObjSpinner;
    public TextView editAssessmentCurrentCourse;
    public Spinner editAssessAssociatedCourseSpinner;
    public static long assessID;
    public Assessment assessment;
    private List<Course> coursesList;



    //implement the AdapterView.OnItemSelectedListener methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



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
        currentTypeExamLabel = findViewById(R.id.currentTypeExamLabel);
        editAssessPerfObjSpinner = findViewById(R.id.editAssessPerfObjSpinner);
        editAssessmentCurrentCourse = findViewById(R.id.editAssessmentCurrentCourse);
        editAssessAssociatedCourseSpinner = findViewById(R.id.editAssessAssociatedCourseSpinner);

        loadAssessment(assessID);
    }
    protected void onResume(){
        super.onResume();
        //clear the old courses list
        coursesList.clear();
        //repopulate list
        loadAddAssociatedCoursesSpinnerData();
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
        String currentExamType = "Current exam Type: " + assessment.getPerfOrObjective();
        currentTypeExamLabel.setText(currentExamType);
        //NEED TO SET SPINNERS TO CORRECT INFORMATION
        //setup exam type spinner
        editAssessPerfObjSpinner = findViewById(R.id.editAssessPerfObjSpinner);
        ArrayAdapter<CharSequence> perfOrObjAdapter = ArrayAdapter.createFromResource(this, R.array.add_assessment_spinner_options, android.R.layout.simple_spinner_item);
        perfOrObjAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editAssessPerfObjSpinner.setAdapter(perfOrObjAdapter);
        editAssessPerfObjSpinner.setOnItemSelectedListener(this);

        //Associated Courses spinner
        String currentCourse = "Current Associated Course: " + assessment.getAssociatedCourseTitle();
        editAssessmentCurrentCourse.setText(currentCourse);
        editAssessAssociatedCourseSpinner = findViewById(R.id.editAssessAssociatedCourseSpinner);
        editAssessAssociatedCourseSpinner.setOnItemSelectedListener(this);
        loadAddAssociatedCoursesSpinnerData();


    }

    private void loadAddAssociatedCoursesSpinnerData() {
        // create a database object
        Database db = new Database(getApplicationContext());
        // get courses from DB
        coursesList = db.getAllCoursesFromDB();
        //convert courses to a list of strings
        List<String> courseStrings = new ArrayList<String>();
        //load courseStrings with course info
        for(Course course: coursesList){
            //courseStrings.contains(course);
            courseStrings.add(course.getCourseTitle());
        }
        //create an array adapter for the strings
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(EditAssessment.this,
                android.R.layout.simple_spinner_item, courseStrings);
        // set spinner to courseAdapter
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set courseAdapter as adapter
        editAssessAssociatedCourseSpinner.setAdapter(courseAdapter);
    }

    public void updateAssessment(View v) {
        try {
            //update the assessment setters
            assessment.setPerfOrObjective(editAssessPerfObjSpinner.getSelectedItem().toString());
            assessment.setAssessmentEndDate(editAssessEndDateTV.getText().toString());
            assessment.setAssociatedCourseTitle(editAssessAssociatedCourseSpinner.getSelectedItem().toString());
            //create a db object
            Database db = new Database(EditAssessment.this);
            //add the Assessment object to the DB
            db.updateAssessmentInformation(assessment);
            Intent refresh = new Intent(this, AssessmentList.class);
            startActivity(refresh);
        } catch (Exception e) {
            Toast.makeText(EditAssessment.this, "Error adding assessment.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAssessment(View v) {
        Log.d("assessment to delete", String.valueOf(assessment.getAssessmentID()));
        Database db = new Database(EditAssessment.this);

        db.deleteAssessment(assessment);
        Intent refresh = new Intent(this, AssessmentList.class);
        startActivity(refresh);
    }











}