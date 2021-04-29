package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.patrickdenneymobileapp.Database.courseList;
import static com.example.patrickdenneymobileapp.Database.termList;

public class EditCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static long courseID;

    private List<String> termStrings;
    public Course course;
    private TextView editCourseTitleTV;
    private EditText editCourseStart;
    private EditText editCourseEndTV;
    private Spinner editCourseStatusSpinner;
    private EditText editCourseInstructor;
    private EditText editInsPhone;
    private EditText editInsEmail;
    private Spinner courseEditAssociatedTermSpinner;
    private EditText editCourseNotesTV;
    private TextView editCourseAssociatedAssessments;
    Date currentDate = new Date();
    SimpleDateFormat dateFormat  = new SimpleDateFormat("MM/dd/yyyy");
    String dateString = dateFormat.format(currentDate);
    Date date;
    Calendar calendar;


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
        editCourseStart = findViewById(R.id.editTextDateEditCourseStart);
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
    protected void onResume(){
        super.onResume();
        //clear the old terms list
        termStrings.clear();
        //repopulate list
        fillTermStringsArray();
        loadCourse(courseID);
    }

    private void loadCourse(long id) {
        Database db = new Database(EditCourse.this);
        //get course object from the course list of all courses in the Database class
        Course course = courseList.get((int) id);
        //place the course information in the text fields
        String titleField = "Course ID: " + course.getCourseId() + "    " + course.getCourseTitle();
        editCourseTitleTV.setText(titleField);
        editCourseStart.setText(course.getCourseStart());
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
            course.setCourseStart(String.valueOf(editCourseStart.getText()));
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


    //implement the AdapterView.OnItemSelectedListener methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void shareNotesEdit(View view){
        String notes = editCourseNotesTV.getText().toString();
        String title = "Course notes for "+ editCourseNotesTV.getText().toString();
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
        date = dateFormat.parse(editCourseStart.getText().toString());
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        setNotificationStart(calendar.getTimeInMillis());
    }
    public void setReminderEndBtn(View v) throws ParseException {

        //setup date for the notification alarm
        date = dateFormat.parse(editCourseEndTV.getText().toString());
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        setNotificationEnd(calendar.getTimeInMillis());
    }
    public void setNotificationStart(long timeInMillis){

        Intent intent = new Intent(EditCourse.this, Notification.class);
        intent.putExtra("key",editCourseTitleTV.getText().toString() + " course starts today!" );
        PendingIntent sender = PendingIntent.getBroadcast(EditCourse.this,++MainActivity.notifyNum, intent, 0 );
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        //these are for testing the alert
        //Calendar now = Calendar.getInstance();
        //long later  = now.getTimeInMillis() + 5000;
        alarm.set(AlarmManager.RTC_WAKEUP, timeInMillis, sender);
    }
    public void setNotificationEnd(long timeInMillis){

        Intent intent = new Intent(EditCourse.this, Notification.class);
        intent.putExtra("key",editCourseTitleTV.getText().toString() + " course ends today!" );
        PendingIntent sender = PendingIntent.getBroadcast(EditCourse.this,++MainActivity.notifyNum, intent, 0 );
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        //these are for testing the alert
        //Calendar now = Calendar.getInstance();
        //long later  = now.getTimeInMillis() + 5000;
        alarm.set(AlarmManager.RTC_WAKEUP, timeInMillis, sender);
    }

}









