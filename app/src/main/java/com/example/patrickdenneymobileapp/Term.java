package com.example.patrickdenneymobileapp;

public class Term {
    private String title;
    private String start;
    private String end;
    private boolean courses;
    private int studentId;

    public Term(String title, String start, String end, boolean courses, int studentId) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.courses = courses;
        this.studentId = studentId;
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

    public boolean isCourses() {
        return courses;
    }

    public void setCourses(boolean courses) {
        this.courses = courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
