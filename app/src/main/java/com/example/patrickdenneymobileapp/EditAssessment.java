package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditAssessment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        this.setTitle("Edit an Assessment");
    }
}