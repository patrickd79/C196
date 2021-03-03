package com.example.patrickdenneymobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static com.example.patrickdenneymobileapp.Database.*;

public class EditTerm extends AppCompatActivity {


    //NEED TO ADD FUNCTIONALITY FOR UPDATING NEW TERM INFORMATION AND DELETING OLD TERM INFORMATION

    public static long termID;
    public Term term;
    //declare the View components
    TextView editTermID;
    EditText editTermTitle;
    EditText editTermStart;
    EditText editTermEnd;
    EditText editCourses;
    Button updateTermBtn;
    Button delete;

    // create the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        this.setTitle("Edit a Term");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //get termID from TermList
        Intent intent = getIntent();
        termID = intent.getLongExtra("id", TermList.termID);
        term = termList.get((int) termID);
        //setCoursesToTrue();
        //set the view components to their ids
        editTermID = findViewById(R.id.editTermIDTextView);
         editTermTitle = findViewById(R.id.editTitle);
         editTermStart = findViewById(R.id.editStart);
         editTermEnd = findViewById(R.id.editEnd);
         editCourses = findViewById(R.id.editCoursesInTerm);
         updateTermBtn = findViewById(R.id.updateTermBtn);
         delete = findViewById(R.id.deleteTermBtn);

        getTerm(termID);

    }
    public void getTerm(long id) {
        //get term object from the term list of all terms in the Database class
        Term term = termList.get((int)id);
        //place the term information in the text fields
            String idField = "Term ID: " + term.getTermId();
            editTermID.setText(idField);
            editTermTitle.setText(term.getTitle());
            editTermStart.setText(term.getStart());
            editTermEnd.setText(term.getEnd());
            editCourses.setText(term.getCourseString());
            //term.getStart()
            //term.getEnd()
            //term.getCourses();

    }
    public void setCoursesToTrue(){
        term.setCourses(true);
    }

    public void deleteTerm(View v){
        Log.d("term to delete", term.getTermId());
        Database db = new Database(EditTerm.this);
        if(term.getCourses()){
            FragmentManager fragmentManager = getSupportFragmentManager();
            DeleteTermWithCoursesAlert dialog = new DeleteTermWithCoursesAlert();
            dialog.show(fragmentManager, "Please remove all courses from term before deleting.");
        }else{
            db.deleteOneTerm(term);
            Intent refresh = new Intent(this, TermList.class);
            startActivity(refresh);
        }


    }


    //method to add a new term when the add term btn is clicked
    public void updateTerm(View v){
        Term term;
        try{
            //
            //
            //
            //need to retrieve the 1 term by ID from the database. Need to create a method in the DB to return the term object by ID, then can update by using setters.
            //
            //
            //
        }catch(Exception e){
            Toast.makeText(EditTerm.this, "Error updating term.", Toast.LENGTH_SHORT).show();
        }
    }

}