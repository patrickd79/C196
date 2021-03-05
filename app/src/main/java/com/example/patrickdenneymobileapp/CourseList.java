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

public class CourseList extends AppCompatActivity {
    ListView courseListView;
    public static long courseID;
    //database object instance
    Database db;
    //list containing the terms to display
    List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        courseListView = findViewById(R.id.addCourseListView);
        this.setTitle("Courses");
        // back button to parent activity
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //populateCourseList();
    }

    public void populateCourseList(){
        //create a db object
        db = new Database(CourseList.this);
        //get a list of the terms from the DB object
        courseList = db.getAllCoursesFromDB();

        List<String> courseStrings = new ArrayList<>();
        for( Course course : courseList) {

            courseStrings.add("ID: " + course.getCourseId() + " Title: " + course.getCourseTitle() + " Start Date: " + course.getCourseStart() + " End Date: " + course.getCourseEnd() + " Status: "
                    + course.getCourseStatus() + " Course Instructor: "+ course.getCourseInstructorID() + "Course Notes: " + course.getCourseNotes());
        }
        ArrayAdapter<String> courseArray = new ArrayAdapter<String>(CourseList.this, android.R.layout.simple_list_item_1, courseStrings);
        courseListView.setAdapter(courseArray);
    }

    public void openAddCourseScreen(View v){
        Intent intent = new Intent(this, CourseAdd.class);
        startActivity(intent);
    }
}