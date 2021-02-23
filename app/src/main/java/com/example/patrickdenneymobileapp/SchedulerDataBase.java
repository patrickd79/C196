package com.example.patrickdenneymobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SchedulerDataBase extends SQLiteOpenHelper {

    public static final String dbName = "Scheduler Database";
    public static final int version = 1;
    public static final String TERMS = "TERMS";
    public static final String TERM_ID = "ID";
    public static final String TERM_TITLE = "TITLE";
    public static final String TERM_START_DATE = "START_DATE";
    public static final String TERM_END_DATE = "END_DATE";
    public static final String COURSES_IN_TERM = "COURSES";


    public SchedulerDataBase(Context context){
        super(context, dbName, null, version);
    }

    public static final class TermTable{

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TERMS + " (" + TERM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TERM_TITLE + " TEXT, " + TERM_START_DATE + " TEXT, " + TERM_END_DATE + " TEXT, " + COURSES_IN_TERM + " BOOL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TERMS);

        onCreate(db);
    }
    //method to insert a new term to the database
    public boolean addTermToDB(Term term){
        //get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        //create a key value pair list of the values of the term
        ContentValues values = new ContentValues();
        //add the term values to the list
        values.put(TERM_TITLE, term.getTitle());
        values.put(TERM_START_DATE, term.getStart());
        values.put(TERM_END_DATE, term.getEnd());
        values.put(COURSES_IN_TERM, term.getCourses());
        //insert the value list into the term table and returns -1 if unsuccessful or 0 if successful
        long insert = db.insert(TERMS, null, values);
        //returns true for 0, false for -1
        return insert != -1;
    }

    public List<Term> getAllTermsFromDB(){
       List<Term> termList = new ArrayList<>();
       String query = "SELECT * FROM TERMS";
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(query, null);
       if(cursor.moveToFirst()){
           do{
               int termId = cursor.getInt(0);
               String title = cursor.getString(1);
               String start = cursor.getString(2);
               String end = cursor.getString(3);
               boolean courses = cursor.getInt(4) == 1 ? true: false;


               Term term = new Term(termId, title, start, end, courses);
               termList.add(term);
               Log.d("obj", "getAllTermsFromDB: " + termList.get(0));
           }while(cursor.moveToNext());
       }else{
           //nothing to return
       }
       cursor.close();
       db.close();
       return termList;
    }


}
