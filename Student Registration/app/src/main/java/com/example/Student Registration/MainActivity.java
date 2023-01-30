package com.example.blgm_ogrencilerisql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3;
    Button bt1,bt2,bt3;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    SQLite db;
    TextView tv;
    RadioButton rb1,rb2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.editTextTextPersonName);
        et2 = findViewById(R.id.editTextTextPersonName2);
        et3 = findViewById(R.id.editTextNumberDecimal);
        bt1 = findViewById(R.id.button2);
        bt2 = findViewById(R.id.button3);
        bt3 = findViewById(R.id.button4);
        tv = findViewById(R.id.textView4);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        arrayList.add("CMPE");
        arrayList.add("BLGM");
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        db= new SQLite(this);



                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(rb1.isChecked()){
                            if(et1.getText().toString().equals("")||et2.getText().toString().equals("")||et3.getText().toString().equals("")){
                                tv.setText("Alanlar bos birakilamaz!");
                            }else{
                                try {
                                    db.blgmbilgiekle(et1.getText().toString().trim(),et2.getText().toString().trim(),et3.getText().toString().trim());
                                } finally {
                                    db.close();
                                }
                                et1.setText("");
                                et2.setText("");
                                et3.setText("");
                                tv.setText("BLGM Ekleme Basarili!");
                            }

                        }else if(rb2.isChecked()){
                            if(et1.getText().toString().equals("")||et2.getText().toString().equals("")||et3.getText().toString().equals("")){
                                tv.setText("Alanlar bos birakilamaz!");
                            }else{
                                try {
                                    db.cmpebilgiekle(et1.getText().toString().trim(),et2.getText().toString().trim(),et3.getText().toString().trim());
                                } finally {
                                    db.close();
                                }
                                et1.setText("");
                                et2.setText("");
                                et3.setText("");
                                tv.setText("CMPE Ekleme Basarili!");
                            }
                        }
                        else {
                            tv.setText("Bölüm Seciniz..");
                        }
                    }
                });



        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yeniaktivite = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(yeniaktivite);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yeniaktivite = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(yeniaktivite);
            }
        });

    }
}