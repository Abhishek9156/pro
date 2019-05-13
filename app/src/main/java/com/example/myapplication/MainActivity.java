package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM;


public class MainActivity extends Activity {
    Button b1, b2;
    EditText ed1, ed2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1 = findViewById(R.id.button5);
        ed1 = findViewById(R.id.username);
        ed2 = findViewById(R.id.password);

//       b2 = findViewById(R.id.button3);
//        tx1 = findViewById(R.id.textView3);
//        tx1.setVisibility(View.GONE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "SuccessFull...", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(v.getContext(),flash.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });

//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}
