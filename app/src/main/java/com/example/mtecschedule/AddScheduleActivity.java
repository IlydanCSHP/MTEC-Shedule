package com.example.mtecschedule;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.mtecschedule.adapter.PairAdapter;
import com.example.mtecschedule.dao.PairDao;
import com.example.mtecschedule.dao.ScheduleDao;
import com.example.mtecschedule.database.MtecRoomDatabase;
import com.example.mtecschedule.databinding.ActivityAddScheduleBinding;
import com.example.mtecschedule.databinding.ActivityScheduleBinding;
import com.example.mtecschedule.databinding.ActivitySingleScheduleBinding;
import com.example.mtecschedule.model.Pair;
import com.example.mtecschedule.model.Schedule;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AddScheduleActivity extends AppCompatActivity {

    ActivityAddScheduleBinding binding;
    RecyclerView pairsRecycler;
    PairAdapter pairAdapter;
    List<Pair> pairList;
    AppCompatButton addPairButton;
    AppCompatButton addScheduleButton;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pairList = new ArrayList<>();
        getPairs();
        addPairButton = findViewById(R.id.add_pair_button);
        addScheduleButton = findViewById(R.id.add_schedule_button);
        datePicker = findViewById(R.id.schedule_date);
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int dayOfMonth = datePicker.getDayOfMonth();
        addScheduleButton.setOnClickListener(view -> addSchedule(year, month, dayOfMonth));
        datePicker.setMaxDate(System.currentTimeMillis() + 604800000L);
        datePicker.setMinDate(System.currentTimeMillis());

        ViewGroup vg = (ViewGroup) datePicker.getChildAt(0);
        vg.getChildAt(0).setVisibility(View.GONE);
        datePicker.init(year, month, dayOfMonth, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Оставить пустым для предотвращения изменения даты
            }
        });

        Field[] datePickerDialogFields = datePicker.getClass().getDeclaredFields();

        for (Field datePickerDialogField : datePickerDialogFields) {

            if (datePickerDialogField.getName().equals("mMonthPicker")) {
                datePickerDialogField.setAccessible(true);
                Object monthPicker = null;
                try {
                    monthPicker = datePickerDialogField.get(datePicker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                ((View) monthPicker).setVisibility(View.GONE);
            }
            addPairButton.setOnClickListener(view -> openPair());

            pairsRecycler = findViewById(R.id.pairs_recycler);
            pairAdapter = new PairAdapter(this, pairList, this);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
            pairsRecycler.setLayoutManager(manager);
            pairsRecycler.setAdapter(pairAdapter);
        }
    }

    private void addSchedule(int year, int month, int dayOfMonth) {
        new Thread(() -> {
            ScheduleDao scheduleDao = MtecRoomDatabase.getInstance(AddScheduleActivity.this).scheduleDao();
            String scheduleDate = dayOfMonth + "." + month + "." + year;
            Schedule schedule = new Schedule(scheduleDate);
            scheduleDao.insert(schedule);
            
            runOnUiThread(() -> {
                Toast.makeText(this, "Добавлено!", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    private void getPairs() {
        PairDao pairDao = MtecRoomDatabase.getInstance(this).pairDao();
        LiveData<List<Pair>> pairsLive = pairDao.getAllLive();
        pairsLive.observe(this, pairs -> {
            pairList = pairs;
            pairAdapter = new PairAdapter(AddScheduleActivity.this, pairList, AddScheduleActivity.this);
            pairsRecycler.setAdapter(pairAdapter);
        });
    }

    private void openPair() {
        Intent intent = new Intent(this, AddPairActivity.class);
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