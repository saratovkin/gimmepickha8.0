package com.example.bramaceo.gimmepikcha;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);

        new LoadList().execute();
    }

    class LoadList extends AsyncTask<Void, Void, Void> {

        RecyclerViewAdapter adapter;
        RecyclerView.LayoutManager mLayoutManager;

        @Override
        protected Void doInBackground(Void... voids) {
            adapter = new RecyclerViewAdapter(Constants.items);
            mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(mLayoutManager);
        }
    }
}
