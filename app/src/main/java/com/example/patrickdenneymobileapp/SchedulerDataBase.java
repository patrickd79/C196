package com.example.patrickdenneymobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SchedulerDataBase extends SQLiteOpenHelper {

    public static final String dbName = "Scheduler Database";
    public static final int version = 1;

    public SchedulerDataBase(Context context){
        super(context, dbName, null, version);
    }

    public static final class TermTable{
        private static final String TABLE = "terms";
        private static final String COL_ID = "_id";
        private static final String TERM_TITLE = "title";
        private static final String START = "start_date";
        private static final String END = "end_date";
        private static final String COURSES = "courses";
        private static final String STUDENT_ID = "student_id";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TermTable.TABLE + " (" +
                TermTable.COL_ID + " integer primary key autoincrement, " +
                TermTable.TERM_TITLE + " text, " +
                TermTable.START + " text, " +
                TermTable.END + " text, " +
                TermTable.COURSES + " text, " +
                TermTable.STUDENT_ID + " integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TermTable.TABLE);
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
        values.put(TermTable.COURSES , term.isCourses());
        values.put(TermTable.STUDENT_ID, term.getStudentId());
        //insert the value list into the term table and returns -1 if unsuccessful or 0 if successful
        long insert = db.insert(TermTable.TABLE, null, values);
        //returns true for 0, false for -1
        return insert != -1;
    }

}
