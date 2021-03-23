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
        populateCourseList();
        clickOnCourse();
    }

    protected void onResume(){
        super.onResume();
        //clear the old term list
        courseList.clear();
        //repopulate list after the update or deletion from EditTerm
        populateCourseList();
    }

    public void populateCourseList(){
        //create a db object
        db = new Database(CourseList.this);
        //get a list of the terms from the DB object
        courseList = db.getAllCoursesFromDB();

        List<String> courseStrings = new ArrayList<>();
        for( Course course : courseList) {

            courseStrings.add("ID: " + course.getCourseId() + "  Title: " + course.getCourseTitle() + "\nStart Date: " + course.getCourseStart() + "  End Date: " + course.getCourseEnd() + "\nStatus: "
                    + course.getCourseStatus() + "\nInstructor: "+ course.getInstructorName()+ "\nInstructor Phone: "+ course.getInstructorPhone()
                    + "\nInstructor Email: "+ course.getInstructorEmail() +"\nTerm: " + course.getCourseTermTitle()
                    + "\nCourse Notes: " + course.getCourseNotes());
        }
        ArrayAdapter<String> courseArray = new ArrayAdapter<String>(CourseList.this, android.R.layout.simple_list_item_1, courseStrings);
        courseListView.setAdapter(courseArray);
    }

    public void clickOnCourse(){
        courseListView = findViewById(R.id.addCourseListView);
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView item = (TextView) itemClicked;

                //Test message to see that the correct item was clicked
                String message = "Clicked on " + position + " Text: " + item.getText().toString();
                Log.d("message ", message);
                Log.d("Course ID ", String.valueOf(id));
                courseID =  id;
                openEditCourseScreen();
            }
        });
    }

    public void openAddCourseScreen(View v){
        Intent intent = new Intent(this, CourseAdd.class);
        startActivity(intent);
    }
    public void openEditCourseScreen(){
        Intent intent = new Intent(this, EditCourse.class);
        intent.putExtra("id", courseID);
        startActivity(intent);
    }
}