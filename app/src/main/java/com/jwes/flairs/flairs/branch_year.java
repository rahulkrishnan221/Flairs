package com.jwes.flairs.flairs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class branch_year extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Spinner spinner1;
    public static final String preference="pref";
    public static final String preference1="pref1";
    public static final String saveit="savekey";
    public static final String saveit1="savekey1";
    String x,y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_year);

         spinner = (Spinner) findViewById(R.id.department_spinner);
        spinner.setOnItemSelectedListener(this);
        spinner1 = (Spinner) findViewById(R.id.year_spinner);
        spinner1.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.year, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);}


    public void onItemSelected(AdapterView<?> adapter, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String selected1  = spinner.getSelectedItem().toString();
        String selected2  = spinner1.getSelectedItem().toString();

       SharedPreferences sf=getSharedPreferences(preference,Context.MODE_PRIVATE);
        SharedPreferences sf1=getSharedPreferences(preference1,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sf.edit();
        SharedPreferences.Editor editor1=sf1.edit();

        editor.putString(saveit,selected1 );
        editor1.putString(saveit1,selected2);
        editor.commit();
        editor1.commit();

        x = sf.getString(saveit,"");
        y=sf1.getString(saveit1,"");
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, y, Toast.LENGTH_SHORT).show();

    }

    public void onNothingSelected(AdapterView<?> adapter) {
        // Another interface callback

    }
}


