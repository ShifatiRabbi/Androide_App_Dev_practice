package com.example.countryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   ;
    private CustomAdapter adapter;
    private ListView listView;
    private String[] countryNames;
    int[] flags = {
            R.drawable.argentina,
            R.drawable.australia,
            R.drawable.bangladesh,
            R.drawable.brazil,
            R.drawable.england,
            R.drawable.france,
            R.drawable.germany,
            R.drawable.italy,
            R.drawable.pakistan
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewId);

        countryNames = getResources().getStringArray(R.array.countryName);

        adapter = new  CustomAdapter(this, countryNames, flags);
        listView.setAdapter(adapter);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String value = countryNames[i];
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

            }
        });

    }
}