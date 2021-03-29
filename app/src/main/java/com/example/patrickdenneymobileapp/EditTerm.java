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
    public static String coursesForTerm = "";
    public static String courseDisplay = "";
    public Term term;
    //declare the View components
    TextView editTermID;
    TextView editTermTitle;
    EditText editTermStart;
    EditText editTermEnd;
    TextView editCourses;
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
        //get courses string from the term list activity for this term
        //coursesForTerm = intent.getStringExtra(TermList.courses);
        //courseDisplay = TermList.courses;
        //setCoursesToTrue();
        //set the view components to their ids
        editTermID = findViewById(R.id.editTermIDTextView);
         editTermTitle = findViewById(R.id.editTitle);
         editTermStart = findViewById(R.id.editStart);
         editTermEnd = findViewById(R.id.editEnd);
         editCourses = findViewById(R.id.editTermCourseList);
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
            editCourses.setText(TermList.courses);
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
        if(TermList.courses.length() != 0){
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
        try{
            term.setStart(String.valueOf(editTermStart.getText()));
            term.setEnd(String.valueOf(editTermEnd.getText()));
            /*if(term.getCourses()){
                term.setCourses(true);
            }*/
            Database db = new Database(EditTerm.this);
            db.updateTermInformation(term);
            Intent refresh = new Intent(this, TermList.class);
            startActivity(refresh);



        }catch(Exception e){
            Toast.makeText(EditTerm.this, "Error updating term.", Toast.LENGTH_SHORT).show();
        }
    }

}