package com.example.misha.modulea;


import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.misha.modulea.MESSAGE";
    Button btn;
    EditText edt;
    MainActivity context = this;

    public void sendMessage(View view) {
//        Intent intent = new Intent(this, SecondActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        edt = findViewById(R.id.editText);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View.OnClickListener oclbtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String date_local = dateFormat.format(date);

                DatabaseInintializer.populateSync(AppDatabase.getAppDatabase(context));

                DatabaseInintializer.addLink(AppDatabase.getAppDatabase(context),new com.example.misha.modulea.modul.Link(edt.getText().toString(),1,date_local));
                int count = DatabaseInintializer.getCount(AppDatabase.getAppDatabase(context));
                Intent intent = new Intent(context, AnotherActivity.class);
                startActivity(intent);
//                Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.moduleb");
//                startActivity(intent);
            }
        };
        btn.setOnClickListener(oclbtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.arrow_first:
                Intent intent = new Intent(context, AnotherActivity.class);
                startActivity(intent);
        }
        return true;
    }
}

