package com.example.chetanrajjain.filemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private UIjava uijava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main);
        uijava = (UIjava) getFragmentManager().findFragmentById(R.id.file_list);




    }
}
