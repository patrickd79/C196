package com.example.patrickdenneymobileapp;

public class Term {
    private int termId;
    private String title;
    private String start;
    private String end;
    private boolean courses;

    public Term(String title, String start, String end, boolean courses) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.courses = courses;

    }
    //overloaded constructor for returning from database
    public Term(int termId, String title, String start, String end, boolean courses) {
        this.termId = termId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.courses = courses;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean getCourses() {
        return courses;
    }

    public void setCourses(boolean courses) {
        this.courses = courses;
    }


}
