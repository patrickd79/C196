package com.example.patrickdenneymobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static final String dbName = "Scheduler Database";
    public static final int version = 10;
    //Term table col names
    public static final String terms = "TERMS";
    public static final String term_id = "TERM_ID";
    public static final String term_title = "TERM_TITLE";
    public static final String term_start = "TERM_START";
    public static final String term_end = "TERM_END";
    public static final String term_got_courses = "TERM_COURSE";
    //Courses table col names
    public static final String courses = "COURSES";
    public static final String course_id = "COURSE_ID";
    public static final String course_title = "COURSE_TITLE";
    public static final String course_start = "COURSE_START";
    public static final String course_end = "COURSE_END";
    public static final String course_status = "COURSE_STATUS";
    public static final String ins_name = "INSTRUCTOR_NAME";
    public static final String ins_ph_number = "INSTRUCTOR_PHONE";
    public static final String ins_email = "INSTRUCTOR_EMAIL";
    public static final String course_notes = "COURSE_NOTES";
    //Term and Courses Table
    public static final String terms_and_courses = "TERMS_AND_COURSES";
    public static final String tcTerm_ID = "Term_ID";
    public static final String tcCourseID = "Course_ID";
    //Assessment table col names
    public static final String assessments = "ASSESSMENTS";
    public static final String assess_id = "ASSESSMENT_ID";
    public static final String assess_title = "TITLE";
    public static final String perf_or_obj = "PERF_OR_OBJ";
    public static final String assess_end = "ASSESSMENT_END_DATE";
    public static final String assess_associated_course = "ASSOCIATED_COURSE";
    //List to hold the objects
    public static List<Term> termList = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();
    public static List<Assessment> assessmentList = new ArrayList<>();


    public Database(Context context){
        super(context, dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+terms+"("+term_id+" INTEGER PRIMARY KEY, "+term_title+" TEXT, " +  term_start + " TEXT, " +  term_end + " TEXT, " + term_got_courses+ " BOOL)");
        db.execSQL("CREATE TABLE "+courses+"("+course_id+" INTEGER PRIMARY KEY, "+course_title+" TEXT, " +  course_start + " TEXT, " +  course_end + " TEXT," +
                " " + course_status+ " TEXT, "+ ins_name + " TEXT, " + ins_ph_number + " TEXT, " + ins_email + " TEXT, " + term_title + " TEXT, "+course_notes+" TEXT)");
        //db.execSQL("CREATE TABLE "+terms_and_courses+"("+tcTerm_ID+" INTEGER, "+tcCourseID+" INTEGER, PRIMARY KEY("+tcTerm_ID+","+tcCourseID+"))");
        db.execSQL("CREATE TABLE "+assessments+"("+assess_id+" INTEGER PRIMARY KEY, "+ assess_title + " TEXT,"+perf_or_obj+" TEXT, " +assess_end + " TEXT, " +assess_associated_course+ " TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+terms);
        db.execSQL("drop table if exists "+courses);
        db.execSQL("drop table if exists "+assessments);
        db.execSQL("drop table if exists "+terms_and_courses);
        onCreate(db);
    }







    //CREATE AN UPDATE ASSESSMENT METHOD

    //CREATE A DELETE ASSESSMENT METHOD








    public String getAssessmentForACourse(Course course){
        List<String> assessments = new ArrayList<>();
        String courseTitle = course.getCourseTitle();
        StringBuilder str = new StringBuilder();
        String query = "SELECT TITLE FROM ASSESSMENTS WHERE ASSOCIATED_COURSE=" +"'" + courseTitle + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(0);
                assessments.add(title);
            }while(cursor.moveToNext());
        }
        if(assessments.size() != 0){
            for(int i = 0; i < assessments.size(); i ++){
                if(i == assessments.size() -1 ){
                    str.append(assessments.get(i));
                }else{
                    str.append(assessments.get(i)).append(", ");
                }
            }
        }
        String assessmentStr = str.toString();
        cursor.close();
        db.close();
        return assessmentStr;
    }


    //method to get the courses associated with a specific term
    public List<Course> getCoursesForASpecificTermFromDB(Term term){
        String termTitle = term.getTitle();
        String query = "SELECT COURSE_TITLE FROM COURSES WHERE TERM_TITLE=" +"'" + termTitle + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(0);
                Course course = new Course(title);
                courseList.add(course);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return courseList;
    }



    //method to insert a new assessment to the database
    public boolean addAssessmentToDB(Assessment assessment){
        //get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        //create a key value pair list of the values of the assessment
        ContentValues values = new ContentValues();
        //add the assessment values to the list
        values.put(assess_title, assessment.getAssessmentTitle());
        values.put(perf_or_obj, assessment.getPerfOrObjective());
        values.put(assess_end, assessment.getAssessmentEndDate());
        values.put(assess_associated_course, assessment.getAssociatedCourseTitle());
        //insert the value list into the assessment table and returns -1 if unsuccessful or 0 if successful
        long insert = db.insert(assessments, null, values);
        //returns true for 0, false for -1
        return insert != -1;
    }

    public List<Assessment> getAllAssessmentsFromDB(){
        String query = "SELECT * FROM "+assessments;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            int i = 0;
            do{
                int assessmentId = cursor.getInt(0);
                String title = cursor.getString(1);
                String perfOrObj = cursor.getString(2);
                String end = cursor.getString(3);
                String course = cursor.getString(4);

                Assessment assessment = new Assessment(assessmentId, title, perfOrObj, end, course);
                assessmentList.add(assessment);
                Log.d("obj", "getAllAssessmentsFromDB: " + assessmentList.get(i).getAssessmentID());
                Log.d("ID", "getAllAssessmentsFromDB:" +assess_id);
                i++;
            }while(cursor.moveToNext());
        }else{
            //nothing to return
        }
        cursor.close();
        db.close();
        return assessmentList;
    }

    public boolean deleteOneCourse(Course course) {
        //get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM COURSES WHERE "+course_id+"=" + course.getCourseId();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public void updateCourseInformation(Course course){
        //first have the edit course activity update the course in the courseList in this class with the new information
        //get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        //write SQL query
        String updateQuery = "UPDATE "+courses+ " SET "+course_title+"= ?, "+course_start+"= ?, "+course_end+"= ?, "+course_status+"= ?, "+ins_name+"= ?, "+ins_ph_number+"= ?," +
                " "+ins_email+"= ?, "+course_notes+"= ? WHERE "+course_id+"= ?";
        //update table
        db.execSQL(updateQuery, new String[]{course.getCourseTitle(), course.getCourseStart(), course.getCourseEnd(), course.getCourseStatus(), String.valueOf(course.getInstructorName()),
                String.valueOf(course.getInstructorPhone()), String.valueOf(course.getInstructorEmail()),
                course.getCourseNotes(), String.valueOf(course.getCourseId())});
    }

    //method to insert a new course to the database
    public boolean addCourseToDB(Course course){
        //get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        //create a key value pair list of the values of the courses
        ContentValues values = new ContentValues();
        //add the course values to the list
        values.put(course_title, course.getCourseTitle());
        values.put(course_start, course.getCourseStart());
        values.put(course_end, course.getCourseEnd());
        values.put(course_status, course.getCourseStatus());
        values.put(ins_name, course.getInstructorName());
        values.put(ins_ph_number, course.getInstructorPhone());
        values.put(ins_email, course.getInstructorEmail());
        values.put("TERM_TITLE", course.getCourseTermTitle());
        values.put(course_notes, course.getCourseNotes());
        //insert the value list into the course table and returns -1 if unsuccessful or 0 if successful
        long insert = db.insert(courses, null, values);
        //returns true for 0, false for -1
        return insert != -1;
    }

    public List<Course> getAllCoursesFromDB(){
        String query = "SELECT * FROM "+courses;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            int i = 0;
            do{
                int courseId = cursor.getInt(0);
                String title = cursor.getString(1);
                String start = cursor.getString(2);
                String end = cursor.getString(3);
                String status = cursor.getString(4);
                String instructorName = cursor.getString(5);
                String instructorPhone = cursor.getString(6);
                String instructorEmail = cursor.getString(7);
                String termTitle = cursor.getString(8);
                String notes = cursor.getString(9);

                Course course = new Course(courseId, title, start, end, status,instructorName, instructorPhone, instructorEmail, termTitle, notes);
                courseList.add(course);
                Log.d("obj", "getAllCoursesFromDB: " + courseList.get(i).getCourseId());
                Log.d("ID", "getAllCoursesFromDB:" +course_id);
                i++;
            }while(cursor.moveToNext());
        }else{
            //nothing to return
        }
        cursor.close();
        db.close();
        return courseList;
    }



    //method to insert a new term to the database
    public boolean addTermToDB(Term term){
        //get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        //create a key value pair list of the values of the term
        ContentValues values = new ContentValues();
        //add the term values to the list
        values.put(term_title, term.getTitle());
        values.put(term_start, term.getStart());
        values.put(term_end, term.getEnd());
        values.put(term_got_courses, term.getCourses());
        //insert the value list into the term table and returns -1 if unsuccessful or 0 if successful
        long insert = db.insert(terms, null, values);
        //returns true for 0, false for -1
        return insert != -1;
    }

    public void updateTermInformation(Term term){
        //first have the edit term activity update the term in the termList in this class with the new information
        //get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        //write SQL query
        String updateQuery = "UPDATE "+terms+ " SET "+term_title+"= ?, "+term_start+"= ?, "+term_end+"= ?, "+term_got_courses+"= ? WHERE "+term_id+"= ?";
        //update table
        db.execSQL(updateQuery, new String[]{term.getTitle(), term.getStart(), term.getEnd(), term.getCourseString(), term.getTermId()});


    }

    public List<Term> getAllTermsFromDB(){
       String query = "SELECT * FROM "+terms;
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(query, null);
       if(cursor.moveToFirst()){
           int i = 0;
           do{
               int termId = cursor.getInt(0);
               String title = cursor.getString(1);
               String start = cursor.getString(2);
               String end = cursor.getString(3);
               boolean courses = cursor.getInt(4) == 1 ? true: false;
               //fix this to change courses when a course gets added
               Term term = new Term(termId, title, start, end, courses);
               termList.add(term);
               Log.d("obj", "getAllTermsFromDB: " + termList.get(i).getTermId());
               Log.d("ID", "getAllTermsFromDB:" +term_id);
               i++;
           }while(cursor.moveToNext());
       }else{
           //nothing to return
       }
       cursor.close();
       db.close();
       return termList;
    }




    public boolean deleteOneTerm(Term term) {
        //get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM TERMS WHERE "+term_id+"=" + term.getTermId();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }








}
