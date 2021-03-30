package com.example.patrickdenneymobileapp;

public class Assessment {
    private int assessmentID;
    private String assessmentTitle;
    private String perfOrObjective;
    private String assessmentEndDate;
    private String associatedCourseTitle;

    public Assessment(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public Assessment(int assessmentID, String assessmentTitle, String perfOrObjective, String assessmentEndDate, String associatedCourseTitle) {
        this.assessmentID = assessmentID;
        this.assessmentTitle = assessmentTitle;
        this.perfOrObjective = perfOrObjective;
        this.assessmentEndDate = assessmentEndDate;
        this.associatedCourseTitle = associatedCourseTitle;
    }
    public Assessment(String assessmentTitle, String perfOrObjective, String assessmentEndDate, String associatedCourseTitle) {
        this.assessmentTitle = assessmentTitle;
        this.perfOrObjective = perfOrObjective;
        this.assessmentEndDate = assessmentEndDate;
        this.associatedCourseTitle = associatedCourseTitle;
    }

    public Assessment(int assessmentID, String assessmentTitle, String perfOrObjective, String assessmentEndDate) {
        this.assessmentID = assessmentID;
        this.assessmentTitle = assessmentTitle;
        this.perfOrObjective = perfOrObjective;
        this.assessmentEndDate = assessmentEndDate;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getPerfOrObjective() {
        return perfOrObjective;
    }

    public void setPerfOrObjective(String perfOrObjective) {
        this.perfOrObjective = perfOrObjective;
    }

    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }

    public String getAssociatedCourseTitle() {
        return associatedCourseTitle;
    }

    public void setAssociatedCourseTitle(String associatedCourseTitle) {
        this.associatedCourseTitle = associatedCourseTitle;
    }


}
