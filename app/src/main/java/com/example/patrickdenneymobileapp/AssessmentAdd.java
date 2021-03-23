package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssessmentAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView addAssessmentTitle;
    private TextView addAssessmentEnd;
    private Spinner addAssessPerfOrObj;
    private Spinner addAssessAssociatedCourseSpinner;
    List<Course> courses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_add);
        this.setTitle("Add an Assessment");
        // back button to parent activity
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        // add assess title field
        addAssessmentTitle = findViewById(R.id.addAssessTitleTV);
        //add assess end date field
        addAssessmentEnd = findViewById(R.id.addAssessEndDateTV);
        //setup perf or objective spinner
        addAssessPerfOrObj = findViewById(R.id.addAssessPerfObjSpinner);
        ArrayAdapter<CharSequence> perfOrObjAdapter = ArrayAdapter.createFromResource(this, R.array.add_assessment_spinner_options, android.R.layout.simple_spinner_item);
        perfOrObjAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addAssessPerfOrObj.setAdapter(perfOrObjAdapter);
        addAssessPerfOrObj.setOnItemSelectedListener(this);

        //setup associated courses spinner
        addAssessAssociatedCourseSpinner = findViewById(R.id.addAssessAssociatedCourseSpinner);
        addAssessAssociatedCourseSpinner.setOnItemSelectedListener(this);
        loadAddAssociatedCoursesSpinnerData();
    }
    protected void onResume(){
        super.onResume();
        //clear the old term list
        courses.clear();
        //repopulate list after the update or deletion from EditTerm
        loadAddAssociatedCoursesSpinnerData();
    }

    //method to add a new Assessment when the add Assessment btn is clicked
    public void addAssessment(View v) {
        Assessment assessment;
        try {
            //create an Assessment object instance
            assessment = new Assessment(addAssessmentTitle.getText().toString(), addAssessPerfOrObj.getSelectedItem().toString(), addAssessmentEnd.getText().toString(),
                    addAssessAssociatedCourseSpinner.getSelectedItem().toString() );
            //create a db object
            Database db = new Database(AssessmentAdd.this);
            //add the Assessment object to the DB
            boolean added = db.addAssessmentToDB(assessment);
            //if addAssessmentToDB returns true then this Toast displays that
            Toast.makeText(AssessmentAdd.this, "Assessment added: " + added, Toast.LENGTH_LONG).show();
            Intent refresh = new Intent(this, AssessmentList.class);
            startActivity(refresh);
        } catch (Exception e) {
            Toast.makeText(AssessmentAdd.this, "Error adding assessment.", Toast.LENGTH_SHORT).show();
        }
    }



    private void loadAddAssociatedCoursesSpinnerData() {
        // create a database object
        Database db = new Database(getApplicationContext());
        // get courses from DB
        courses = db.getAllCoursesFromDB();
        //convert courses to a list of strings
        List<String> courseStrings = new ArrayList<String>();
        //load courseStrings with course info
        for(Course course: courses){
            //courseStrings.contains(course);
            courseStrings.add(course.getCourseTitle());
        }
        //create an array adapter for the strings
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(AssessmentAdd.this,
                android.R.layout.simple_spinner_item, courseStrings);
        // set spinner to courseAdapter
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set courseAdapter as adapter
        addAssessAssociatedCourseSpinner.setAdapter(courseAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}