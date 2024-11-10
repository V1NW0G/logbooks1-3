package com.example.myjoblist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    // List to store job objects (tasks) and a context for accessing resources
    ArrayList<Job> jobList;
    Context context;

    // Constructor to initialize the job list and context
    public JobAdapter(ArrayList<Job> jobList, Context context) {
        this.jobList = jobList;
        this.context = context;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView from XML (job_layout)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_layout, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        // Get the job at the current position
        Job job = jobList.get(position);

        // Set the job title and description
        holder.tv1.setText(job.getTitle());
        holder.tv2.setText(job.getDescription());

        // Set an OnClickListener for the Delete button (bt1)
        holder.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log the delete action for debugging
                Log.d("myapp", "Delete button clicked at position " + position);

                // Remove the job at this position using a singleton method
                JobListSingleton.getInstance().deleteJob(position);

                // Notify the adapter that an item has been removed to update the UI
                notifyItemRemoved(position);
                notifyItemChanged(position, jobList.size());
            }
        });

        // Set an OnClickListener for the Edit button (bt2)
        holder.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log the edit action for debugging
                Log.d("myapp", "Edit button clicked at position " + position);

                // Create an Intent to start UpdateJobActivity, passing the position of the item to edit
                Intent i1 = new Intent(context, UpdateJobActivity.class);
                i1.putExtra("position", position);
                context.startActivity(i1);
            }
        });

        // Set an OnClickListener for the Done button (bt3)
        holder.bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log the done action for debugging
                Log.d("myapp", "Done button clicked at position " + position);

                // Mark the job as done
                job.setDone(true);

                // Remove the job from its current position and add it to the bottom of the list
                jobList.remove(position);
                jobList.add(job);

                // Notify the adapter of item changes to refresh the UI
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the total number of jobs in the list, used by RecyclerView to determine item count
        return jobList.size();
    }

    // ViewHolder class for holding and managing the views in each item of the RecyclerView
    public class JobViewHolder extends RecyclerView.ViewHolder {

        TextView tv1, tv2; // TextViews for displaying job title and description
        Button bt1, bt2, bt3; // Buttons for delete, edit, and done actions

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views from job_layout XML
            tv1 = itemView.findViewById(R.id.textView); // Job title TextView
            tv2 = itemView.findViewById(R.id.textView2); // Job description TextView
            bt1 = itemView.findViewById(R.id.button4); // Delete Button
            bt2 = itemView.findViewById(R.id.button5); // Edit Button
            bt3 = itemView.findViewById(R.id.buttonDone); // Done Button
        }
    }
}