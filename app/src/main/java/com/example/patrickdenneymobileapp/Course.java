package com.example.patrickdenneymobileapp;

public class Course {
    private int courseId;
    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private int courseInstructorID;

    public String getCourseTerm() {
        return courseTerm;
    }

    public void setCourseTerm(String courseTerm) {
        this.courseTerm = courseTerm;
    }

    private String courseNotes;

    public Course(int courseId, String courseTitle, String courseStart, String courseEnd, String courseStatus, int courseInstructorID, String courseNotes, String courseTerm) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.courseInstructorID = courseInstructorID;
        this.courseNotes = courseNotes;
        this.courseTerm = courseTerm;
    }

    private String courseTerm;

    public Course(String courseTitle, String courseStart, String courseEnd, String courseStatus, int courseInstructorID, String courseNotes) {
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.courseInstructorID = courseInstructorID;
        this.courseNotes = courseNotes;
    }

    public Course(int courseId, String courseTitle, String courseStart, String courseEnd, String courseStatus, int courseInstructorID, String courseNotes) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.courseInstructorID = courseInstructorID;
        this.courseNotes = courseNotes;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getCourseInstructorID() {
        return courseInstructorID;
    }

    public void setCourseInstructorID(int courseInstructorID) {
        this.courseInstructorID = courseInstructorID;
    }

    public String getCourseNotes() {
        return courseNotes;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }
}
