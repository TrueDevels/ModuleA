package com.example.misha.modulea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.misha.modulea.modul.Link;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnotherActivity extends AppCompatActivity {
    List<Link> links = new ArrayList<Link>();
    Link[] ar; //= {new Link("some_url",1,"23.09"),new Link("some_url2",2,"43")};
    ImageButton button;
    AnotherActivity context = this;
    LinkAdapter linkAd;
    ListView lv;
    ArrayList<Link> local;
    Map<Link, Integer> status_sort = new HashMap<Link, Integer>();
    Map<Link, String> status_date = new HashMap<Link, String>();
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
        local = new ArrayList<>(links);
        lv =  findViewById(R.id.listview); // находим список
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);// создаем адаптер
        linkAd = new LinkAdapter(this, android.R.layout.simple_list_item_1, local );
        lv.setAdapter(linkAd);   // присваиваем адаптер списку
    }
    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
    private static HashMap sortByValuesBackward(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.arrow:
                Intent intent1 = new Intent(context, MainActivity.class);
                startActivity(intent1);
            case R.id.status:
                for(Link loc : links){ status_sort.put(loc, loc.getStatus());}
                Map<Link, Integer> map = sortByValues((HashMap) status_sort);
                local = new ArrayList<>(map.keySet());
                linkAd = new LinkAdapter(this, android.R.layout.simple_list_item_1, local );
                lv.setAdapter(linkAd);
                Toast toast4 = Toast.makeText(getApplicationContext(), "Sort by status", Toast.LENGTH_SHORT);
                toast4.show();

            case R.id.date:
                for(Link loc : links){ status_date.put(loc, loc.getDate());}
                Map<Link, Integer> map1 = sortByValuesBackward((HashMap) status_date);
                local = new ArrayList<>(map1.keySet());
                linkAd = new LinkAdapter(this, android.R.layout.simple_list_item_1, local );
                lv.setAdapter(linkAd);
                Toast toast1 = Toast.makeText(getApplicationContext(), "Sort by date", Toast.LENGTH_SHORT);
                toast1.show();
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }
}

