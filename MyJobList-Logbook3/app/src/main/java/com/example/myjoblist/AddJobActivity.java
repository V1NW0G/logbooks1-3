package com.example.myjoblist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddJobActivity extends AppCompatActivity {

    // EditTexts for entering job title and description
    EditText et1, et2;
    // Buttons for saving the job or canceling the addition
    Button bt1, bt2;
    // TextViews for displaying error messages
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        // Initialize UI elements
        et1 = this.findViewById(R.id.editTextText8); // Job title input field
        et2 = this.findViewById(R.id.editTextText9); // Job description input field
        bt1 = this.findViewById(R.id.button8);       // Save button
        bt2 = this.findViewById(R.id.button9);       // Cancel button
        tv1 = this.findViewById(R.id.textView8);     // Error display for title
        tv2 = this.findViewById(R.id.textView9);     // Error display for description

        // Set OnClickListener for the Save button
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input from EditTexts
                String title = et1.getText().toString();
                String description = et2.getText().toString();
                String titleError = "";
                String descriptionError = "";

                // Validate the title input and show error if empty
                if ("".equals(title)) {
                    titleError = "Please enter your title";
                }
                tv1.setText(titleError);

                // Validate the description input and show error if empty
                if ("".equals(description)) {
                    descriptionError = "Please enter your description";
                }
                tv2.setText(descriptionError);

                // If both title and description are valid, proceed with adding the job
                if (titleError.isEmpty() && descriptionError.isEmpty()) {
                    // Create a new job with the entered title and description
                    Job newJob = new Job(title, description, false);

                    // Add the new job to the job list in the singleton
                    JobListSingleton.getInstance(AddJobActivity.this).getJobList().add(newJob);

                    // Clear input fields and close the activity
                    et1.setText("");
                    et2.setText("");
                    finish();
                }
            }
        });

        // Set OnClickListener for the Cancel button to close the activity without saving
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}