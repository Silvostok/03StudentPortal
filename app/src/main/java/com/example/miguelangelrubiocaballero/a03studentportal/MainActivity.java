package com.example.miguelangelrubiocaballero.a03studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private GridView mGridView;
    private ArrayAdapter<GridItem> mAdapter;
    private List<GridItem> mItems = new ArrayList<>();


    public static final int REQUEST_CODE = 2;
    public static final String INTENT_ADD_WEBSITE_URL = "Add URL";
    public static final String INTENT_ADD_WEBSITE_TITLE = "Add Title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mItems.add(new GridItem("VLO", " https://vlo.informatica.hva.nl/"));
        mItems.add(new GridItem("HVA", " http://hva.nl/"));
        mItems.add(new GridItem("StudieGids", " http://studiegids.hva.nl/"));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, AddWebsiteActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                GridItem gridItem = (GridItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", gridItem.getUrl());
                startActivity(intent);
            }
        });

        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                mItems.remove(position);
                Snackbar.make(view, "Removed!", Snackbar.LENGTH_LONG).show();
                updateUI();

                return true;
            }
        });

        updateUI();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                String newUrl = data.getStringExtra(INTENT_ADD_WEBSITE_URL);
                String newTitle = data.getStringExtra(INTENT_ADD_WEBSITE_TITLE);

                mItems.add(new GridItem(newTitle, newUrl));
                updateUI();
            }
        }
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




    private void updateUI()
    {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItems);
            mGridView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }






}
