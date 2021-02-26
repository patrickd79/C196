package com.example.patrickdenneymobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static final String dbName = "Scheduler Database";
    public static final int version = 1;
    public static final String TERMS = "TERMS";
    public static final String TERM_ID = "ID";
    public static final String TERM_TITLE = "TITLE";
    public static final String TERM_START_DATE = "START_DATE";
    public static final String TERM_END_DATE = "END_DATE";
    public static final String COURSES_IN_TERM = "COURSES";
    //List to hold the term objects
    public static List<Term> termList = new ArrayList<>();
    public static long termToDelete = EditTerm.termID;


    public Database(Context context){
        super(context, dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TERMS + " (" + TERM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + TERM_TITLE + " TEXT, " + TERM_START_DATE + " TEXT, " + TERM_END_DATE + " TEXT, " + COURSES_IN_TERM + " BOOL)");

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
       String query = "SELECT * FROM TERMS";
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
               Term term = new Term(termId, title, start, end, courses);
               termList.add(term);
               Log.d("obj", "getAllTermsFromDB: " + termList.get(i).getTermId());
               Log.d("ID col", "getAllTermsFromDB:" + TERM_ID);
               i++;
           }while(cursor.moveToNext());
       }else{
           //nothing to return
       }
       cursor.close();
       db.close();
       return termList;
    }

    public void updateTermInformation(long termId){
        //first have the edit term activity update the term in the termList in this class with the new information

    }

    public void deleteOneTerm() {
        //get a writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM TERMS WHERE ID = " + termToDelete;
        Cursor cursor = db.rawQuery(query,null);
        cursor.close();
    }





}
