package com.example.myjoblist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateJobActivity extends AppCompatActivity {

    // EditTexts for job title and description
    private EditText et1, et2;
    // Buttons for save and cancel actions
    private Button bt1, bt2;
    // TextViews for error messages
    private TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_job);

        // Retrieve the job position from the intent
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        Job job = JobListSingleton.getInstance(this).getJobList().get(position);

        // Initialize UI components
        et1 = findViewById(R.id.editTextText18); // Title EditText
        et2 = findViewById(R.id.editTextText19); // Description EditText
        bt1 = findViewById(R.id.button18);       // Save Button
        bt2 = findViewById(R.id.button19);       // Cancel Button
        tv1 = findViewById(R.id.textView18);     // Error message for title
        tv2 = findViewById(R.id.textView19);     // Error message for description

        // Pre-fill EditTexts with the current job title and description
        et1.setText(job.getTitle());
        et2.setText(job.getDescription());

        // Set OnClickListener for the Save button
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input from EditTexts
                String title = et1.getText().toString();
                String description = et2.getText().toString();
                String titleError = "";
                String descriptionError = "";

                // Validate title input; show error if empty
                if (title.isEmpty()) {
                    titleError = "Please enter your title";
                }
                tv1.setText(titleError);

                // Validate description input; show error if empty
                if (description.isEmpty()) {
                    descriptionError = "Please enter your description";
                }
                tv2.setText(descriptionError);

                // If no errors, update the job details
                if (titleError.isEmpty() && descriptionError.isEmpty()) {
                    // Update job details and save to singleton and database
                    job.setTitle(title);
                    job.setDescription(description);
                    JobListSingleton.getInstance(UpdateJobActivity.this).updateJob(position, job);

                    // Clear input fields and close the activity
                    et1.setText("");
                    et2.setText("");
                    finish();
                }
            }
        });

        // Set OnClickListener for the Cancel button to close activity without saving changes
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}