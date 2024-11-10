package com.example.myjoblist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class UpdateJobActivity extends AppCompatActivity {

    // EditTexts for title and description input, Buttons for save and cancel actions, TextViews for error messages
    EditText et1, et2;
    Button bt1, bt2;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout file for this activity
        setContentView(R.layout.activity_update_job);

        // Retrieve the position of the selected job from the intent and get the corresponding job object
        Intent i1 = this.getIntent();
        int position = i1.getIntExtra("position", 0);
        Job job = JobListSingleton.getInstance().getJobList().get(position);

        // Initialize EditText, Button, and TextView fields from the layout
        et1 = this.findViewById(R.id.editTextText18); // EditText for job title
        et2 = this.findViewById(R.id.editTextText19); // EditText for job description
        bt1 = this.findViewById(R.id.button18); // Save button
        bt2 = this.findViewById(R.id.button19); // Cancel button
        tv1 = this.findViewById(R.id.textView18); // Error TextView for title
        tv2 = this.findViewById(R.id.textView19); // Error TextView for description

        // Pre-fill the EditTexts with the current title and description of the job
        et1.setText(job.getTitle());
        et2.setText(job.getDescription());

        // Set OnClickListener for the Save button (bt1)
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get updated title and description from input fields
                String title = et1.getText().toString();
                String description = et2.getText().toString();
                String titleError = "";
                String descriptionError = "";

                // Validate title input: show error message if empty
                if ("".equals(title)) {
                    titleError = "Please enter your title";
                } else {
                    titleError = "";
                }
                tv1.setText(titleError);

                // Validate description input: show error message if empty
                if ("".equals(description)) {
                    descriptionError = "Please enter your description";
                } else {
                    descriptionError = "";
                }
                tv2.setText(descriptionError);

                // If both title and description are valid (no error messages)
                if (titleError.equals("") && descriptionError.equals("")) {
                    // Update job object with new title and description
                    job.setTitle(et1.getText().toString());
                    job.setDescription(et2.getText().toString());

                    // Update job in singleton list
                    JobListSingleton.getInstance().updateJob(position, job);

                    // Clear input fields and close activity after saving
                    et1.setText("");
                    et2.setText("");
                    UpdateJobActivity.this.finish();
                }
            }
        });

        // Set OnClickListener for the Cancel button (bt2)
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the activity without saving changes
                UpdateJobActivity.this.finish();
            }
        });
    }
}