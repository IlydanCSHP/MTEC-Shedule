package com.example.mtecschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mtecschedule.adapter.PairAdapter;
import com.example.mtecschedule.dao.PairDao;
import com.example.mtecschedule.database.MtecRoomDatabase;
import com.example.mtecschedule.model.Pair;

import java.util.ArrayList;
import java.util.List;

public class SingleScheduleActivity extends AppCompatActivity {

    RecyclerView pairRecycler;
    PairAdapter pairAdapter;
    List<Pair> pairList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_schedule);

        pairList = new ArrayList<>();
        getPairs();
        pairRecycler = findViewById(R.id.pair_recycler);
        pairAdapter = new PairAdapter(this, pairList, this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        pairRecycler.setLayoutManager(manager);
        pairRecycler.setAdapter(pairAdapter);
    }

    private void getPairs() {
        PairDao pairDao = MtecRoomDatabase.getInstance(this).pairDao();
        LiveData<List<Pair>> pairsLive = pairDao.getAllLive();
        pairsLive.observe(this, pairs -> {
            pairList = pairs;
            pairAdapter = new PairAdapter(this, pairList, this);
            pairRecycler.setAdapter(pairAdapter);
        });
    }
}