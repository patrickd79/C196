package com.example.patrickdenneymobileapp;

public class Assessment {
    private int assessmentID;
    private String assessmentTitle;
    private String perfOrObjective;
    private String assessmentEndDate;

    public Assessment(String assessmentTitle, String perfOrObjective, String assessmentEndDate) {
        this.assessmentTitle = assessmentTitle;
        this.perfOrObjective = perfOrObjective;
        this.assessmentEndDate = assessmentEndDate;
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
}
