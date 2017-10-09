package com.chomptech.easyconverter;

import android.content.Intent;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Spinner spinner1;
    private Toast toast1;
    private ListView listView;
    private ArrayAdapter adapter2;
    private ArrayList<String> choices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9999083812241050~1052891448");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        toast1 = Toast.makeText(getApplicationContext(), "To be implemented in future update.", Toast.LENGTH_LONG);

        choices.add("Volume");
        choices.add("Distance");
        choices.add("Numbers");
        listView = (ListView)findViewById(R.id.listView1);
        adapter2 = new ArrayAdapter<>(MainActivity.this, R.layout.simplerow, choices);
        listView.setAdapter(adapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                makeChoice(listView.getItemAtPosition(i).toString());
            }
        });
    }
    public void makeChoice(String input) {
        //String userSelection = spinner1.getSelectedItem().toString();
        String userSelection = input;
        if (userSelection.equals("Numbers")) {
            Intent intent = new Intent(getApplicationContext(), NumberActivity.class);
            startActivity(intent);
        } else if(userSelection.equals("Distance")) {
            toast1.show();
            //Intent intent = new Intent(getApplicationContext(), DistanceActivity.class);
            //startActivity(intent);
        } else if(userSelection.equals("Volume")) {
            //toast1.show();
            Intent intent = new Intent(getApplicationContext(), VolumeActivity.class);
            startActivity(intent);
        } else {
            toast1.show();
        }
    }
// add methods to change activities
}
