package com.example.blgm_ogrencilerisql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    ListView lv;

    ArrayList<String> ogrenciler=new ArrayList<String>();

    ArrayList<Integer> idbilgileri=new ArrayList<Integer>();

    ArrayAdapter<String> adapter;

    SQLite db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lv = findViewById(R.id.blgmogrenciview);

        db=new SQLite(this);

        Cursor c=db.blgmbilgigetir();

        if(c.moveToFirst()){
            do{
                ogrenciler.add(c.getInt(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3));
                idbilgileri.add(c.getInt(0));
            }while (c.moveToNext());
        }

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,ogrenciler);

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ogrenciler.remove(i);
                db.kisisilblgm(idbilgileri.get(i)+"");
                idbilgileri.remove(i);
                adapter.notifyDataSetChanged();
            }
        });

    }
}