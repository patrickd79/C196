package com.example.patrickdenneymobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SchedulerDataBase extends SQLiteOpenHelper {

    public static final String dbName = "Scheduler Database";
    public static final int version = 1;


    public SchedulerDataBase(Context context){
        super(context, dbName, null, version);
    }

    public static final class TermTable{
        private static final String TERM_TABLE = "terms";
        private static final String COL_ID = "_id";
        private static final String TERM_TITLE = "title";
        private static final String START = "start_date";
        private static final String END = "end_date";
        private static final String COURSES = "courses";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TermTable.TERM_TABLE + " (" +
                TermTable.COL_ID + " integer primary key autoincrement, " +
                TermTable.TERM_TITLE + " text, " +
                TermTable.START + " text, " +
                TermTable.END + " text, " +
                TermTable.COURSES + " integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TermTable.TERM_TABLE);
        onCreate(db);
    }
    //method to insert a new term to the database
    public boolean addTerm(Term term){
        //get a writeable database
        SQLiteDatabase db = getWritableDatabase();
        //create a key value pair list of the values of the term
        ContentValues values = new ContentValues();
        //add the term values to the list
        values.put(TermTable.TERM_TITLE, term.getTitle());
        values.put(TermTable.START, term.getStart());
        values.put(TermTable.END, term.getEnd());
        values.put(TermTable.COURSES, term.getCourses());
        //insert the value list into the term table and returns -1 if unsuccessful or 0 if successful
        long insert = db.insert(TermTable.TERM_TABLE, null, values);
        //returns true for 0, false for -1
        return insert != -1;
    }

    public List<Term> allTerms(){
       List<Term> termList = new ArrayList();
       String query = "SELECT * FROM " + TermTable.TERM_TABLE;
       SQLiteDatabase db = getReadableDatabase();
       Cursor cursor = db.rawQuery(query, null);
       if(cursor.moveToFirst()){
           while(cursor.moveToFirst()){
               int termId = cursor.getInt(0);
               String title = cursor.getString(1);
               String start = cursor.getString(2);
               String end = cursor.getString(3);
               boolean courses = cursor.getInt(4) == 1 ? true: false;

               Term term = new Term(termId, title, start, end, courses);
               termList.add(term);

           }
       }
       return termList;
    }


}
