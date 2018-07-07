package com.example.misha.modulea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.misha.modulea.modul.Link;

import java.util.ArrayList;
import java.util.List;

public class AnotherActivity extends AppCompatActivity {
    List<Link> links = new ArrayList<Link>();
    Link[] ar; //= {new Link("some_url",1,"23.09"),new Link("some_url2",2,"43")};
    ImageButton button;
    AnotherActivity context = this;
    LinkAdapter linkAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       links = DatabaseInintializer.getLinks(AppDatabase.getAppDatabase(this));
       // ar = new Link[links.size()];
       // ar = (Link[]) links.toArray();
      //  Link[] ar = links.toArray();
        ArrayList<Link> local = new ArrayList<>(links);

        ListView lv =  findViewById(R.id.listview); // находим список
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);// создаем адаптер
        linkAd = new LinkAdapter(this, android.R.layout.simple_list_item_1, local );
        lv.setAdapter(linkAd);   // присваиваем адаптер списку
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.arrow:
                Intent intent1 = new Intent(context, MainActivity.class);
                startActivity(intent1);
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }
}
