package com.example.myjoblist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddJobActivity extends AppCompatActivity {

    EditText et1,et2;
    Button bt1,bt2;
    TextView tv1,tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_job);

        et1 = this.findViewById(R.id.editTextText8);
        et2 = this.findViewById(R.id.editTextText9);
        bt1 = this.findViewById(R.id.button8);
        bt2 = this.findViewById(R.id.button9);
        tv1 = this.findViewById(R.id.textView8);
        tv2 = this.findViewById(R.id.textView9);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = et1.getText().toString();
                String description = et2.getText().toString();
                String titleError = "";
                String descriptionError = "";

                if("".equals(title)) {
                    titleError = "Please enter your title";
                } else {
                    titleError = "";
                }
                tv1.setText(titleError);

                if("".equals(description)) {
                    descriptionError = "Please enter your description";
                } else {
                    descriptionError = "";
                }
                tv2.setText(descriptionError);

                if(titleError.equals("") && descriptionError.equals("")) {
                    // Can save

                    ArrayList<Job> jobList = JobListSingleton.getInstance().getJobList();
                    jobList.add(new Job(title,description));
                    et1.setText("");
                    et2.setText("");
                    AddJobActivity.this.finish();


                } else {
                    // Cannot save
                }

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddJobActivity.this.finish();

            }
        });

    }
}