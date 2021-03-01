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
    public static final String terms = "TERMS";
    public static final String term_id = "ID";
    public static final String term_title = "TITLE";
    public static final String term_start = "TERM_START";
    public static final String term_end = "TERM_END";
    public static final String term_got_courses = "TERM_COURSE";

    //List to hold the term objects
    public static List<Term> termList = new ArrayList<>();
    public static long termToDelete = EditTerm.termID;


    public Database(Context context){
        super(context, dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+terms+"("+term_id+" INTEGER PRIMARY KEY, "+term_title+" TEXT, " +  term_start + " TEXT, " +  term_end + " TEXT, " + term_got_courses+ " BOOL)");

    }
    public void dropTable(){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists TERMS");

        onCreate(db);
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

    public void updateTermInformation(long termId){
        //first have the edit term activity update the term in the termList in this class with the new information

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
