package com.chomptech.easyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;



public class MainActivity extends AppCompatActivity {
Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.choice_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter);

    }
    public void makeChoice(View view) {
        String userSelection = spinner1.getSelectedItem().toString();
        if (userSelection.equals("Numbers")) {
            Intent intent = new Intent(getApplicationContext(), NumberActivity.class);
            startActivity(intent);
        } else if(userSelection.equals("Distance")) {
            Intent intent = new Intent(getApplicationContext(), DistanceActivity.class);
            startActivity(intent);
        } else if(userSelection.equals("Volume")) {
            Intent intent = new Intent(getApplicationContext(), VolumeActivity.class);
            startActivity(intent);
        }

    }
// add methods to change activities
}
