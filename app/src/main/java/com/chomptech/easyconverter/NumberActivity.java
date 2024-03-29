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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private EditText in, out;
    private Toast toast1, toast2, toast3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9999083812241050~5410910033");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        toast1 = Toast.makeText(getApplicationContext(), "To be implemented in future update.", Toast.LENGTH_LONG);
        toast2 = Toast.makeText(getApplicationContext(), "Select a unit to convert to in second drop down menu.", Toast.LENGTH_LONG);
        toast3 = Toast.makeText(getApplicationContext(), "Non numeric and decimal values not supported.", Toast.LENGTH_LONG);

        in = (EditText)findViewById(R.id.editText1);
        out = (EditText)findViewById(R.id.editText2);

        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                convert();
            }
        });

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.num_array, android.R.layout.simple_spinner_item);
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
        if(spinner1.getSelectedItem().toString().equals(spinner2.getSelectedItem().toString())) {
            //toast2.show();
        } else if (spinner1.getSelectedItem().toString().equals("Decimal") && spinner2.getSelectedItem().toString().equals("Hexadecimal")) {

            if (in.getText().toString().matches("[0-9]+")) { //!in.getText().toString().contains(".") && !in.getText().toString().contains("-")
                out.setText(decToHex(Integer.parseInt(in.getText().toString())));
            } else {
                toast3.show();
            }
            //out.setText(String.valueOf(269));

        } else {
            toast1.show();
        }
    }
    public String decToHex(Integer val) {
        ArrayList<Integer> rem = new ArrayList();
        int temp2 = val;
        Boolean b = true;

        while(b) {
            if (temp2 / 16 == 0) {
                b = false;
            }
            rem.add(temp2 % 16);
            temp2 = temp2 / 16;
        }
        String res = new String("");
        for (int i = rem.size() - 1; i >= 0; i--) {
            if (rem.get(i) > 9) {
                switch (rem.get(i)) {
                    case 10: res += "A";
                        break;
                    case 11: res += "B";
                        break;
                    case 12: res += "C";
                        break;
                    case 13: res += "D";
                        break;
                    case 14: res += "E";
                        break;
                    case 15: res += "F";
                        break;
                }
            } else {
                res += rem.get(i).toString();
            }
        }
        return res;
    }
}
