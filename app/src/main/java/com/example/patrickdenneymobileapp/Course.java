package com.example.patrickdenneymobileapp;

public class Course {
    private int courseId;
    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;
    private String courseTermTitle;
    private String courseNotes;

    public Course(int courseId, String courseTitle) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
    }

    public Course(int courseId, String courseTitle, String courseStart, String courseEnd, String courseStatus, String instructorName, String instructorPhone, String instructorEmail, String courseTermTitle, String courseNotes) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.courseTermTitle = courseTermTitle;
        this.courseNotes = courseNotes;
    }

    public Course(String courseTitle, String courseStart, String courseEnd, String courseStatus, String instructorName, String instructorPhone, String instructorEmail, String courseTermTitle, String courseNotes) {
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.courseTermTitle = courseTermTitle;
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


    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructor) {
        this.instructorName = instructor;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
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
