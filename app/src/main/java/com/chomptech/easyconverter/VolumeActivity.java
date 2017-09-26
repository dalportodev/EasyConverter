package com.chomptech.easyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class VolumeActivity extends AppCompatActivity {
    private EditText in, out;
    private Toast toast1, toast2, toast3;
    private Spinner spinner1, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9999083812241050~5410910033");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        toast2 = Toast.makeText(getApplicationContext(), "Select a unit to convert to in second drop down menu.", Toast.LENGTH_LONG);
        toast3 = Toast.makeText(getApplicationContext(), "Input must be in the format of 5 or 1.35", Toast.LENGTH_LONG);

        in = (EditText)findViewById(R.id.editText1);
        out = (EditText)findViewById(R.id.editText2);

        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }
            @Override
            public void afterTextChanged(Editable editable) {
                convert();
            }
        });

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.volume_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter);

        spinner2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                convert();
            }
        });
    }
    public void convert () {

        int numDec = 0;
        char[] temp = in.getText().toString().toCharArray();
        for (int i = 0; i < in.getText().toString().length(); i++) {
            if (temp[i] == '.') {
                ++numDec;
            }
        }
        if (!in.getText().toString().matches("[.]*")) {
            if (numDec < 2 && !in.getText().toString().equals("") && in.getText().toString().matches("[0-9.]*")) { // && !in.getText().toString().contains("[^\\\\d.]")

                if (spinner1.getSelectedItem().toString().equals(spinner2.getSelectedItem().toString())) {

                } else {
                    switch (spinner1.getSelectedItem().toString()) {
                        case "US Gallons":
                            out.setText(convertGallon(spinner2.getSelectedItem().toString(), in.getText().toString()));
                            break;
                        case "US Quarts":
                            out.setText(convertQuart(spinner2.getSelectedItem().toString(), in.getText().toString()));
                            break;
                        case "US Pints":
                            out.setText(convertPint(spinner2.getSelectedItem().toString(), in.getText().toString()));
                            break;
                        case "US Cups":
                            out.setText(convertCup(spinner2.getSelectedItem().toString(), in.getText().toString()));
                            break;
                        case "US Ounces":
                            out.setText(convertOunce(spinner2.getSelectedItem().toString(), in.getText().toString()));
                            break;
                        case "US Tablespoons":
                            out.setText(convertTable(spinner2.getSelectedItem().toString(), in.getText().toString()));
                            break;
                        case "US Teaspoons":
                            out.setText(convertTea(spinner2.getSelectedItem().toString(), in.getText().toString()));
                            break;
                    }
                }
            } else {
                toast3.show();
            }
        } else {
            toast3.show();
        }
    }
    public String convertGallon(String target, String val) {
        Double temp = Double.parseDouble(val);
        Double result = 0.0;
        switch (target) {
            case "US Quarts": result = temp * 4.0;
                break;
            case "US Pints": result = temp * 8.0;
                break;
            case "US Cups": result = temp * 15.7725;
                break;
            case "US Ounces": result = temp * 128.0;
                break;
            case "US Tablespoons": result = temp * 256.0;
                break;
            case "US Teaspoons": result = temp * 768.0;
        }
        return String.valueOf(result);
    }
    public String convertQuart(String target, String val) {
        Double temp = Double.parseDouble(val);
        Double result = 0.0;
        switch (target) {
            case "US Gallons": result = temp * 0.25;
                break;
            case "US Pints": result = temp * 2.0;
                break;
            case "US Cups": result = temp * 3.94314;
                break;
            case "US Ounces": result = temp * 32;
                break;
            case "US Tablespoons": result = temp * 64.0;
                break;
            case "US Teaspoons": result = temp * 192.0;
        }
        return String.valueOf(result);
    }
    public String convertPint(String target, String val) {
        Double temp = Double.parseDouble(val);
        Double result = 0.0;
        switch (target) {
            case "US Quarts": result = temp * 0.5;
                break;
            case "US Gallons": result = temp * 0.125;
                break;
            case "US Cups": result = temp * 1.97157;
                break;
            case "US Ounces": result = temp * 16.0;
                break;
            case "US Tablespoons": result = temp * 32.0;
                break;
            case "US Teaspoons": result = temp * 96.0;
        }
        return String.valueOf(result);
    }
    public String convertCup(String target, String val) {
        Double temp = Double.parseDouble(val);
        Double result = 0.0;
        switch (target) {
            case "US Quarts": result = temp * 0.253605;
                break;
            case "US Pints": result = temp * 0.50721;
                break;
            case "US Gallons": result = temp * 0.0634013;
                break;
            case "US Ounces": result = temp * 8.11537;
                break;
            case "US Tablespoons": result = temp * 16.2307;
                break;
            case "US Teaspoons": result = temp * 48.6922;
        }
        return String.valueOf(result);
    }
    public String convertOunce(String target, String val) {
        Double temp = Double.parseDouble(val);
        Double result = 0.0;
        switch (target) {
            case "US Quarts": result = temp * 0.03125;
                break;
            case "US Pints": result = temp * 0.0625;
                break;
            case "US Cups": result = temp * 0.123223;
                break;
            case "US Gallons": result = temp * 0.0078125;
                break;
            case "US Tablespoons": result = temp * 2.0;
                break;
            case "US Teaspoons": result = temp * 6.0;
        }
        return String.valueOf(result);
    }
    public String convertTable(String target, String val) {
        Double temp = Double.parseDouble(val);
        Double result = 0.0;
        switch (target) {
            case "US Quarts": result = temp * 0.015625;
                break;
            case "US Pints": result = temp * 0.03125;
                break;
            case "US Cups": result = temp * 0.0616115;
                break;
            case "US Ounces": result = temp * 0.5;
                break;
            case "US Gallons": result = temp * 0.00390625;
                break;
            case "US Teaspoons": result = temp * 3.0;
        }
        return String.valueOf(result);
    }
    public String convertTea(String target, String val) {
        Double temp = Double.parseDouble(val);
        Double result = 0.0;
        switch (target) {
            case "US Quarts": result = temp * 0.00520833;
                break;
            case "US Pints": result = temp * 0.0104167;
                break;
            case "US Cups": result = temp * 0.0205372;
                break;
            case "US Ounces": result = temp * 0.166667;
                break;
            case "US Tablespoons": result = temp * 0.333333;
                break;
            case "US Gallons": result = temp * 0.00130208;
        }
        return String.valueOf(result);
    }
}