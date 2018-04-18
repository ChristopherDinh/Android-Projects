package com.example.max_student.List;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.max_student.test.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button submit;
    List<String> name;
    TextView output;
    EditText edittext;
    String formatFirstLetter;
    String formatRestName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.outputName);
        name = new ArrayList<>();

        new CountDownTimer(54000,1) {
            public void onTick(long timer) {

            }
            public void onFinish() {

            }
        }.start();

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                output.setText("");
                EditText input = (EditText) findViewById(R.id.inputName);
                formatFirstLetter = (input.getText().toString().substring(0,1).toUpperCase());
                formatRestName = (input.getText().toString().substring(1).toLowerCase());
                name.add(formatFirstLetter + formatRestName);
                for (int i = 0; i < name.size(); i++) {
                    output.append((i + 1) + " " + name.get(i) + " \n");
                }
                input.setText("");
            }
           });

    }
}
