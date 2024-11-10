package com.example.myjoblist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Button to add a new job and RecyclerView to display the list of jobs
    private Button bt1;
    private RecyclerView rv1;
    private JobAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        bt1 = findViewById(R.id.button); // Add Job button
        rv1 = findViewById(R.id.recyclerView); // RecyclerView to display job list

        // Retrieve the job list from JobListSingleton
        ArrayList<Job> jobList = JobListSingleton.getInstance(this).getJobList();

        // Set up the adapter with the job list and attach it to the RecyclerView
        adapter = new JobAdapter(jobList, this);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this)); // Set layout manager for linear list display

        // Set OnClickListener for Add Job button to open AddJobActivity
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddJobActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the adapter to reflect any changes in the job list (e.g., added, updated, or deleted jobs)
        adapter.notifyDataSetChanged();
    }
}