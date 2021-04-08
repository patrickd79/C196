package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.patrickdenneymobileapp.Database.courseList;
import static com.example.patrickdenneymobileapp.Database.termList;

public class EditCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static long courseID;

    private List<String> termStrings;
    public Course course;
    private TextView editCourseTitleTV;
    private EditText editTextDateEditCourseStart;
    private EditText editCourseEndTV;
    private Spinner editCourseStatusSpinner;
    private EditText editCourseInstructor;
    private EditText editInsPhone;
    private EditText editInsEmail;
    private Spinner courseEditAssociatedTermSpinner;
    private EditText editCourseNotesTV;
    private TextView editCourseAssociatedAssessments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        this.setTitle("Edit a Course");
        //get courseID from CourseList
        Intent intent = getIntent();
        courseID = intent.getLongExtra("id", CourseList.courseID);
        course = courseList.get((int) courseID);
        //set the view components to their ids
        editCourseTitleTV = findViewById(R.id.editCourseTitleTV);
        editTextDateEditCourseStart = findViewById(R.id.editTextDateEditCourseStart);
        editCourseEndTV = findViewById(R.id.editCourseEndTV);
        editCourseStatusSpinner = findViewById(R.id.editCourseStatusSpinner);
        editCourseInstructor = findViewById(R.id.editCourseInstructor);
        editInsPhone = findViewById(R.id.editCourseInsPhone);
        editInsEmail = findViewById(R.id.editCourseInsEmail);
        courseEditAssociatedTermSpinner = findViewById(R.id.courseEditAssociatedTermSpinner);
        editCourseNotesTV = findViewById(R.id.editCourseNotesTV);
        editCourseAssociatedAssessments = findViewById(R.id.editCourseAssociatedAssessments);


        loadCourse(courseID);

    }

    private void loadCourse(long id) {
        Database db = new Database(EditCourse.this);
        //get course object from the course list of all courses in the Database class
        Course course = courseList.get((int) id);
        //place the course information in the text fields
        String titleField = "Course ID: " + course.getCourseId() + "    " + course.getCourseTitle();
        editCourseTitleTV.setText(titleField);
        editTextDateEditCourseStart.setText(course.getCourseStart());
        editCourseEndTV.setText(course.getCourseEnd());
        editCourseInstructor.setText(course.getInstructorName());
        editInsPhone.setText(course.getInstructorPhone());
        editInsEmail.setText(course.getInstructorEmail());
        editCourseNotesTV.setText(course.getCourseNotes());
        editCourseAssociatedAssessments.setText(db.getAssessmentForACourse(course));
        //course status spinner settings
        editCourseStatusSpinner = findViewById(R.id.editCourseStatusSpinner);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.course_status_spinner_options, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editCourseStatusSpinner.setAdapter(statusAdapter);
        editCourseStatusSpinner.setOnItemSelectedListener(this);
        //terms to associate spinner
        fillTermStringsArray();
        courseEditAssociatedTermSpinner = findViewById(R.id.courseEditAssociatedTermSpinner);
        ArrayAdapter<String> termAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, termStrings);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseEditAssociatedTermSpinner.setAdapter(termAdapter);
        courseEditAssociatedTermSpinner.setOnItemSelectedListener(this);



    }

    private void fillTermStringsArray(){
        //create a db object
        Database db = new Database(EditCourse.this);
        //get a list of the terms from the DB object
        termList = db.getAllTermsFromDB();

        termStrings = new ArrayList<>();
        for( Term term : termList) {
            termStrings.add( term.getTitle());
        }
    }


    public void deleteCourse(View v) {
        Log.d("course to delete", String.valueOf(course.getCourseId()));
        Database db = new Database(EditCourse.this);

        db.deleteOneCourse(course);
        Intent refresh = new Intent(this, CourseList.class);
        startActivity(refresh);
    }

    public void updateCourse(View v){
        try{
            course.setCourseStart(String.valueOf(editTextDateEditCourseStart.getText()));
            course.setCourseEnd(String.valueOf(editCourseEndTV.getText()));
            course.setCourseStatus(editCourseStatusSpinner.getSelectedItem().toString());
            course.setInstructorName(String.valueOf(editCourseInstructor.getText()));
            course.setInstructorPhone(String.valueOf(editInsPhone.getText()));
            course.setInstructorEmail(String.valueOf(editInsEmail.getText()));
            course.setCourseTermTitle(courseEditAssociatedTermSpinner.getSelectedItem().toString());
            course.setCourseNotes(String.valueOf(editCourseNotesTV.getText()));
            Database db = new Database(EditCourse.this);
            db.updateCourseInformation(course);
            Intent refresh = new Intent(this, CourseList.class);
            startActivity(refresh);
        }catch(Exception e){
            Toast.makeText(EditCourse.this, "Error updating course.", Toast.LENGTH_SHORT).show();
        }
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

