package com.cvd.names;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button submit;
    List<String> name;
    TextView output;
    EditText edittext;
    String formatFirstLetter;
    String formatName;
    Button remove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.outputName);
        name = new ArrayList<>();


        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                output.setText("");
                EditText input = (EditText) findViewById(R.id.inputName);
                formatFirstLetter = (input.getText().toString().substring(0, 1).toUpperCase());
                formatName = (input.getText().toString().substring(1).toLowerCase());
                name.add(formatFirstLetter + formatName);
                for (int i = 0; i < name.size(); i++) {
                    output.append((i + 1) + " " + name.get(i) + " \n");
                }
                input.setText("");
            }
        });

        remove = (Button) findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.remove(0);
                output.setText("");
                if (name.size() > 0) {
                    remove.setEnabled(true);
                } else {
                    remove.setEnabled(false);
                }
                for (int i = 0; i < name.size(); i++) {
                    output.append((i + 1) + " " + name.get(i) + " \n");
                }
            }
        });


    }
}