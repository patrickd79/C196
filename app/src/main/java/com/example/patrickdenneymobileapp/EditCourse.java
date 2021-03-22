package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static com.example.patrickdenneymobileapp.Database.courseList;
import static com.example.patrickdenneymobileapp.Database.termList;

public class EditCourse extends AppCompatActivity {
    public static long courseID;
    public Course course;
    private EditText editCourseTitleTV;
    private EditText editTextDateEditCourseStart;
    private EditText editCourseEndTV;
    private Spinner editCourseStatusSpinner;
    private Spinner editCourseInstructorSpinner;
    private Spinner courseEditAssociatedTermSpinner;
    private EditText editCourseNotesTV;
    private Button updateCourseBtn;
    private Button deleteCourseBtn;
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
        editCourseInstructorSpinner = findViewById(R.id.editCourseInstructorSpinner);
        courseEditAssociatedTermSpinner = findViewById(R.id.courseEditAssociatedTermSpinner);
        editCourseNotesTV = findViewById(R.id.editCourseNotesTV);
        updateCourseBtn = findViewById(R.id.updateCourseBtn);
        deleteCourseBtn = findViewById(R.id.deleteCourseBtn);

        getCourse(courseID);

    }

    public void getCourse(long id) {
        //get course object from the course list of all courses in the Database class
        Course course = courseList.get((int)id);
        //place the course information in the text fields
        String idField = "Course ID: " + course.getCourseId();
        String titleField = course.getCourseTitle() + idField;
        editCourseTitleTV.setText(titleField);
        editTextDateEditCourseStart.setText(course.getCourseStart());
        editCourseEndTV.setText(course.getCourseEnd());
        //NEED TO ADD COURSE INFO TO SPINNERS


    }


    public void deleteCourse(View v){
        Log.d("course to delete", String.valueOf(course.getCourseId()));
        Database db = new Database(EditCourse.this);

            db.deleteOneCourse(course);
            Intent refresh = new Intent(this, CourseList.class);
            startActivity(refresh);
        }


    }

