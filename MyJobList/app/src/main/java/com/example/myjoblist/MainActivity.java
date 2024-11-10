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
    Button bt1;
    RecyclerView rv1;
    JobAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout file for this activity
        setContentView(R.layout.activity_main);

        // Initialize the 'Add Job' button and RecyclerView
        bt1 = this.findViewById(R.id.button);
        rv1 = this.findViewById(R.id.recyclerView);

        // Retrieve or create the job list using a singleton and add sample data for testing
        ArrayList<Job> jobList = JobListSingleton.getInstance().getJobList();
        jobList.add(new Job("Do homework", "Need to borrow some books"));
        jobList.add(new Job("Swimming", "Need to book the venue"));
        jobList.add(new Job("Watch video", "Need to subscribe the channel"));

        // Initialize the adapter with the job list and set it to the RecyclerView
        adapter = new JobAdapter(jobList, this);
        rv1.setAdapter(adapter);

        // Set a linear layout manager for the RecyclerView to display items in a list format
        rv1.setLayoutManager(new LinearLayoutManager(this));

        // Set an OnClickListener on the 'Add Job' button to open AddJobActivity when clicked
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the AddJobActivity for adding a new job
                Intent i1 = new Intent(MainActivity.this, AddJobActivity.class);
                MainActivity.this.startActivity(i1);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the adapter to update the list if any changes were made while the app was paused
        adapter.notifyDataSetChanged();
    }
}