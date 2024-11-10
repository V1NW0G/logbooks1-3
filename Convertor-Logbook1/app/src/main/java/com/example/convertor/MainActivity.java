package com.example.convertor;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    Button bt1;
    Spinner sp1, sp2;
    TextView tv1;
    String[] units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        units = new String[]{"Meter", "Millimeter", "Mile", "Foot"};
        et1 = this.findViewById(R.id.editTextText);
        bt1 = this.findViewById(R.id.button);
        sp1 = this.findViewById(R.id.spinner);
        sp2 = this.findViewById(R.id.spinner2);
        tv1 = this.findViewById(R.id.textView);

        // Set up spinners with unit options
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        sp1.setAdapter(adapter1);
        sp2.setAdapter(adapter2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = et1.getText().toString();

                Log.d("myapp", "The input is " + input);
                Log.d("myapp", "The source unit is " + sp1.getSelectedItem());
                Log.d("myapp", "The target unit is " + sp2.getSelectedItem());

                // Data validation to check if input is empty or not a number
                if (TextUtils.isEmpty(input) || !input.matches("\\d+(\\.\\d+)?")) {
                    tv1.setText("Please enter a valid number.");
                    return;
                }

                // limit input to float
                float inputNumber = Float.parseFloat(input);
                int inputChoice = sp1.getSelectedItemPosition();
                int outputChoice = sp2.getSelectedItemPosition();
                float outputNumber = 0;

                //default meters, then allow user to switch to target unit
                float meters = 0;
                switch (inputChoice) {
                    case 0: // Meter
                        meters = inputNumber;
                        break;
                    case 1: // Millimeter to Meter
                        meters = inputNumber / 1000;
                        break;
                    case 2: // Mile to Meter
                        meters = inputNumber / 0.000621371f;
                        break;
                    case 3: // Foot to Meter
                        meters = inputNumber / 3.28084f;
                        break;
                }

                // Convert meters to the target unit
                switch (outputChoice) {
                    case 0: // Meter
                        outputNumber = meters;
                        break;
                    case 1: // Millimeter
                        outputNumber = meters * 1000;
                        break;
                    case 2: // Mile
                        outputNumber = meters * 0.000621371f;
                        break;
                    case 3: // Foot
                        outputNumber = meters * 3.28084f;
                        break;
                }

                tv1.setText("The result is " + outputNumber);
            }
        });
    }
}