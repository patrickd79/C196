package com.example.patrickdenneymobileapp;

public class Course {
    private int courseId;
    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private String instructorName;
    private int courseInstructorID;
    private String courseTermTitle;
    private String courseNotes;

    public Course(int courseId, String courseTitle) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
    }





    public Course(int courseId, String courseTitle, String courseStart, String courseEnd, String courseStatus, int courseInstructorID, String courseNotes, String courseTermTitle) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.courseInstructorID = courseInstructorID;
        this.courseNotes = courseNotes;
        this.courseTermTitle = courseTermTitle;
    }

    public Course(String courseTitle, String courseStart, String courseEnd, String courseStatus, int courseInstructorID, String courseNotes, String courseTermTitle) {
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.courseInstructorID = courseInstructorID;
        this.courseNotes = courseNotes;
        this.courseTermTitle = courseTermTitle;
    }



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


    public Course(String courseTitle, String courseStart, String courseEnd, String courseStatus, String instructor, String courseTermTitle, String courseNotes) {
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorName = instructor;
        this.courseTermTitle = courseTermTitle;
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

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructor) {
        this.instructorName = instructor;
    }

    public String getCourseNotes() {
        return courseNotes;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    public String getCourseTermTitle() {
        return courseTermTitle;
    }

    public void setCourseTermTitle(String courseTermTitle) {
        this.courseTermTitle = courseTermTitle;
    }
}
