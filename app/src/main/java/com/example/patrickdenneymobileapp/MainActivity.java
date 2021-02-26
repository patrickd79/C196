package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.patrickdenneymobileapp.AssessmentList;
import com.example.patrickdenneymobileapp.CourseList;
import com.example.patrickdenneymobileapp.R;
import com.example.patrickdenneymobileapp.TermList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Home Page");

    }


    public void openTermsScreen(View v){
        Intent intent = new Intent(this, TermList.class);
        startActivity(intent);
    }
    public void openCoursesScreen(View v){
        Intent intent = new Intent(this, CourseList.class);
        startActivity(intent);
    }
    public void openAssessmentsScreen(View v){
        Intent intent = new Intent(this, AssessmentList.class);
        startActivity(intent);
    }
}