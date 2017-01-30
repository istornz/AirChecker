package com.example.dessusdi.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dessusdi.myfirstapp.model.WaqiObject;
import com.example.dessusdi.myfirstapp.recycler_view.AqcinListAdapter;
import com.example.dessusdi.myfirstapp.tools.AqcinRequestService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private RecyclerView recyclerView;
    private List<WaqiObject> cities = new ArrayList<>();
    private AqcinListAdapter adpater = new AqcinListAdapter(cities);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        AqcinRequestService async = new AqcinRequestService(MainActivity.this);
        WaqiObject grenoble     = new WaqiObject("https://api.waqi.info/api/feed/@3067/obs.en.json", async, adpater);
        WaqiObject grenoble2    = new WaqiObject("https://api.waqi.info/api/feed/@3069/obs.en.json", async, adpater);
        WaqiObject smh          = new WaqiObject("https://api.waqi.info/api/feed/@3071/obs.en.json", async, adpater);

        grenoble.fetchData();
        grenoble2.fetchData();
        smh.fetchData();

        cities.add(grenoble);
        cities.add(grenoble2);
        cities.add(smh);

        this.refreshRecyclerList();
    }

    public void refreshRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.adpater);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
