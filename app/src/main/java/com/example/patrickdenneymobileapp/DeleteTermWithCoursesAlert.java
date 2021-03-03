package com.example.patrickdenneymobileapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteTermWithCoursesAlert extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //create view
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_delete_term_alert, null);
        //create button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView msg = (TextView) getActivity().findViewById(R.id.deleteTermError);
                msg.setText("Please remove all courses from the term before deleting.");
            }
        };
        //build dialog
        return new AlertDialog.Builder(getActivity()).setTitle("Error Deleting Term").setView(v).setPositiveButton(android.R.string.ok, listener).create();

    }
}
