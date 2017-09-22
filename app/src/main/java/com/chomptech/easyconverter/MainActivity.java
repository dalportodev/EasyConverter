package com.chomptech.easyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {
    private Spinner spinner1;
    private Toast toast1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9999083812241050~1052891448");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        toast1 = Toast.makeText(getApplicationContext(), "To be implemented in future update.", Toast.LENGTH_LONG);

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
            //toast1.show();
            Intent intent = new Intent(getApplicationContext(), DistanceActivity.class);
            startActivity(intent);
        } else if(userSelection.equals("Volume")) {
            //toast1.show();
            Intent intent = new Intent(getApplicationContext(), VolumeActivity.class);
            startActivity(intent);
        }

    }
// add methods to change activities
}
