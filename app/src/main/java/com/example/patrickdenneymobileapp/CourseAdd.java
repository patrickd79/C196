package com.example.patrickdenneymobileapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class CourseAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText addCourseTitle;
    private EditText addCourseStart;
    private EditText addCourseEnd;
    private EditText instructorName;
    private EditText instructorPhone;
    private EditText instructorEmail;
    private Spinner addCourseStatus;
    private Spinner courseAddAssociatedTermSpinner;
    private EditText addCourseNotes;
    private List<Term> termList;
    private List<String> termStrings;
    Date currentDate = new Date();
    SimpleDateFormat dateFormat  = new SimpleDateFormat("MM/dd/yyyy");
    String dateString = dateFormat.format(currentDate);
    Date date;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);
        this.setTitle("Add a Course");
        loadFields();
    }

    protected void onResume(){
        super.onResume();
        //clear the old term spinner
        termList.clear();
        termStrings.clear();
        loadFields();

    }
    protected void onDestroy(){
        super.onDestroy();
        termList.clear();
        termStrings.clear();
    }

    private void loadFields(){
        addCourseTitle = findViewById(R.id.addCourseTitleTV);
        addCourseStart = findViewById(R.id.editTextDateAddCourseStart);
        addCourseStart.setHint(dateString);
        addCourseEnd = findViewById(R.id.addCourseEndTV);
        addCourseEnd.setHint(dateString);
        addCourseNotes = findViewById(R.id.addCourseNotesTV);
        instructorName = findViewById(R.id.addCourseInstructorName);
        instructorPhone = findViewById(R.id.addCourseInstructorPhone);
        instructorEmail = findViewById(R.id.addCourseInstructorEmail);
        //create the course status spinner functionality
        addCourseStatus = findViewById(R.id.addCourseStatusSpinner);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.course_status_spinner_options, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addCourseStatus.setAdapter(statusAdapter);
        addCourseStatus.setOnItemSelectedListener(this);
        // add course terms spinner functionality
        fillTermStringsArray();
        courseAddAssociatedTermSpinner = findViewById(R.id.courseAddAssociatedTermSpinner);
        ArrayAdapter<String> termAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, termStrings);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseAddAssociatedTermSpinner.setAdapter(termAdapter);
        courseAddAssociatedTermSpinner.setOnItemSelectedListener(this);
    }

    private void fillTermStringsArray(){
        //create a db object
        Database db = new Database(CourseAdd.this);
        //get a list of the terms from the DB object
        termList = db.getAllTermsFromDB();

        termStrings = new ArrayList<>();
        for( Term term : termList) {
            termStrings.add( term.getTitle());
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


    //method to add a new course when the add course btn is clicked
    public void addCourse(View v) {
        Course course;
        try {
            course = new Course(addCourseTitle.getText().toString(), addCourseStart.getText().toString(), addCourseEnd.getText().toString(), addCourseStatus.getSelectedItem().toString(),
                    instructorName.getText().toString(), instructorPhone.getText().toString(), instructorEmail.getText().toString(),  courseAddAssociatedTermSpinner.getSelectedItem().toString(),
                    addCourseNotes.getText().toString()
            );
            Log.d("Instructor", "addCourse: " + instructorName);
            //create a db object
            Database db = new Database(CourseAdd.this);
            //add the course object to the DB
            boolean added = db.addCourseToDB(course);
            //if addCourseToDB returns true then this Toast displays that
            Toast.makeText(CourseAdd.this, "Course added: " + added, Toast.LENGTH_LONG).show();
            Intent refresh = new Intent(this, CourseList.class);
            startActivity(refresh);
        } catch (Exception e) {
            Toast.makeText(CourseAdd.this, "Error adding course.", Toast.LENGTH_SHORT).show();
        }
    }
    public void shareNotes(View view){
        String notes = addCourseNotes.getText().toString();
        String title = "Course notes for "+ addCourseTitle.getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, notes);
        sendIntent.putExtra(Intent.EXTRA_TITLE, title);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void setReminderStartBtn(View v) throws ParseException {
        //setup date for the notification alarm
        date = dateFormat.parse(addCourseStart.getText().toString());
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        setNotificationStart(calendar.getTimeInMillis());
    }
    public void setReminderEndBtn(View v) throws ParseException {
        //setup date for the notification alarm
        date = dateFormat.parse(addCourseEnd.getText().toString());
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        setNotificationEnd(calendar.getTimeInMillis());
    }
    public void setNotificationStart(long timeInMillis){

        Intent intent = new Intent(CourseAdd.this, Notification.class);
        intent.putExtra("key",addCourseTitle.getText().toString() + " course starts today!" );
        PendingIntent sender = PendingIntent.getBroadcast(CourseAdd.this,++MainActivity.notifyNum, intent, 0 );
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        //these are for testing the alert
        //Calendar now = Calendar.getInstance();
        //long later  = now.getTimeInMillis() + 5000;
        alarm.set(AlarmManager.RTC_WAKEUP, timeInMillis, sender);
    }
    public void setNotificationEnd(long timeInMillis){

        Intent intent = new Intent(CourseAdd.this, Notification.class);
        intent.putExtra("key",addCourseTitle.getText().toString() + " course ends today!" );
        PendingIntent sender = PendingIntent.getBroadcast(CourseAdd.this,++MainActivity.notifyNum, intent, 0 );
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        //these are for testing the alert
        //Calendar now = Calendar.getInstance();
        //long later  = now.getTimeInMillis() + 5000;
        alarm.set(AlarmManager.RTC_WAKEUP, timeInMillis, sender);
    }
}
















