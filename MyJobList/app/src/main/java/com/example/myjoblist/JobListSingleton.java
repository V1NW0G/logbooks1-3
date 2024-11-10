package com.example.myjoblist;

import java.util.ArrayList;

public class JobListSingleton {

    private static JobListSingleton INSTANCE;
    ArrayList<Job> jobList = new ArrayList<>();

    // Private constructor to enforce singleton pattern
    private JobListSingleton() {
    }

    // Static method to get the single instance of JobListSingleton
    public static JobListSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JobListSingleton();
        }
        return INSTANCE;
    }

    // Method to get the list of jobs
    public ArrayList<Job> getJobList() {
        return jobList;
    }

    // Method to set the list of jobs
    public void setJobList(ArrayList<Job> jobList) {
        this.jobList = jobList;
    }

    // Method to delete a job by position
    public void deleteJob(int position) {
        jobList.remove(position);
    }

    // Method to update an existing job's title and description
    public void updateJob(int position, Job updatedJob) {
        Job job = jobList.get(position);
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
    }

    // New method to mark a job as done and move it to the bottom of the list
    public void markJobAsDone(int position) {
        Job job = jobList.get(position);
        job.setDone(true);  // Mark the job as done
        jobList.remove(position); // Remove the job from its current position
        jobList.add(job); // Add the job to the end of the list
    }
}