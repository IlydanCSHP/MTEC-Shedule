package com.example.mtecschedule;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtecschedule.adapter.ScheduleAdapter;
import com.example.mtecschedule.dao.ScheduleDao;
import com.example.mtecschedule.database.MtecRoomDatabase;
import com.example.mtecschedule.databinding.ActivityMainBinding;
import com.example.mtecschedule.databinding.ActivityScheduleBinding;
import com.example.mtecschedule.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    ActivityScheduleBinding binding;
    AppCompatButton addScheduleButton;
    RecyclerView scheduleRecycler;
    ScheduleAdapter scheduleAdapter;
    List<Schedule> scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        scheduleList = new ArrayList<>();
        getSchedules();
        addScheduleButton = findViewById(R.id.add_schedule_button);
        addScheduleButton.setOnClickListener(view -> openAddSchedule());
        setActionBar();

        scheduleRecycler = findViewById(R.id.schedule_recycler);
        scheduleAdapter = new ScheduleAdapter(this, scheduleList, this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        scheduleRecycler.setLayoutManager(manager);
        scheduleRecycler.setAdapter(scheduleAdapter);
    }

    private void getSchedules() {
        ScheduleDao scheduleDao = MtecRoomDatabase.getInstance(this).scheduleDao();
        LiveData<List<Schedule>> schedulesLive = scheduleDao.getAllLive();
        schedulesLive.observe(this, schedules -> {
            scheduleList = schedules;
            scheduleAdapter = new ScheduleAdapter(this, scheduleList, this);
            scheduleRecycler.setAdapter(scheduleAdapter);
            Log.d(TAG, "getSchedules: " + schedules);
        });
    }

    private void openAddSchedule() {
        Intent intent = new Intent(this, AddScheduleActivity.class);
        startActivity(intent);
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.red_gradient_navbar));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}