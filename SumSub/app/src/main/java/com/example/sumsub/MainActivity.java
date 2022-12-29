package com.example.sumsub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText1, editText2;
    private Button sum, sub;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editTextId1);
        editText2 = (EditText) findViewById(R.id.editTextId2);

        sum = (Button) findViewById(R.id.buttonSumId);
        sub = (Button) findViewById(R.id.buttonSubId);

        textView = (TextView) findViewById(R.id.textViewId);

        sum.setOnClickListener(this);
        sub.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String num1 = editText1.getText().toString();
        String num2 = editText2.getText().toString();
        if(num1.isEmpty() || num2.isEmpty())
        {

            Toast.makeText(MainActivity.this, "Please enter any digit",Toast.LENGTH_SHORT).show();
        }
        else
        {

            double x = Double.parseDouble(num1);
            double y = Double.parseDouble(num2);

            if(view.getId() == R.id.buttonSumId){
                double s = x + y;
                textView.setText("Sum is = "+s);
            }
            if(view.getId() == R.id.buttonSubId){
                double s = x - y;
                textView.setText("Sub is = "+s);
            }

        }

    }
}