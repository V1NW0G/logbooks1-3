package com.example.myjoblist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Database name and version constants
    private static final String DATABASE_NAME = "JobDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_JOB = "job";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_COMPLETED = "completed";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the job table with id, title, description, and completion status
        String sql = "CREATE TABLE " + TABLE_JOB + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_COMPLETED + " INTEGER" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing table and create a new one if the database version is upgraded
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOB);
        onCreate(db);
    }

    // Method to insert a new job into the database
    public void insertJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, job.getTitle());
        values.put(COLUMN_DESCRIPTION, job.getDescription());
        values.put(COLUMN_COMPLETED, job.isCompleted() ? 1 : 0); // Store completion status as 1 or 0
        db.insert(TABLE_JOB, null, values);
        db.close();
    }

    // Method to retrieve all jobs from the database as a list
    public ArrayList<Job> listJobs() {
        ArrayList<Job> jobList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_JOB;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery(sql, null);

        if (result.moveToFirst()) {
            do {
                Job job = new Job();
                job.setId(result.getInt(result.getColumnIndexOrThrow(COLUMN_ID)));
                job.setTitle(result.getString(result.getColumnIndexOrThrow(COLUMN_TITLE)));
                job.setDescription(result.getString(result.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                job.setCompleted(result.getInt(result.getColumnIndexOrThrow(COLUMN_COMPLETED)) == 1);
                jobList.add(job);
            } while (result.moveToNext());
        }
        result.close();
        db.close();
        return jobList;
    }

    // Method to retrieve a job by its id
    public Job getJob(int id) {
        String sql = "SELECT * FROM " + TABLE_JOB + " WHERE " + COLUMN_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Job job = null;
        Cursor result = db.rawQuery(sql, new String[]{String.valueOf(id)});

        if (result.moveToFirst()) {
            job = new Job();
            job.setId(result.getInt(result.getColumnIndexOrThrow(COLUMN_ID)));
            job.setTitle(result.getString(result.getColumnIndexOrThrow(COLUMN_TITLE)));
            job.setDescription(result.getString(result.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
            job.setCompleted(result.getInt(result.getColumnIndexOrThrow(COLUMN_COMPLETED)) == 1);
        }
        result.close();
        db.close();
        return job;
    }

    // Method to search for jobs based on a keyword in the title
    public ArrayList<Job> searchJob(String keyword) {
        ArrayList<Job> jobList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_JOB + " WHERE " + COLUMN_TITLE + " LIKE ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery(sql, new String[]{"%" + keyword + "%"});

        if (result.moveToFirst()) {
            do {
                Job job = new Job();
                job.setId(result.getInt(result.getColumnIndexOrThrow(COLUMN_ID)));
                job.setTitle(result.getString(result.getColumnIndexOrThrow(COLUMN_TITLE)));
                job.setDescription(result.getString(result.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                job.setCompleted(result.getInt(result.getColumnIndexOrThrow(COLUMN_COMPLETED)) == 1);
                jobList.add(job);
            } while (result.moveToNext());
        }
        result.close();
        db.close();
        return jobList;
    }

    // Method to delete a job by its id
    public void deleteJob(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JOB, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Method to update an existing job's details
    public void updateJob(int id, Job newJob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, newJob.getTitle());
        values.put(COLUMN_DESCRIPTION, newJob.getDescription());
        values.put(COLUMN_COMPLETED, newJob.isCompleted() ? 1 : 0);
        db.update(TABLE_JOB, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}