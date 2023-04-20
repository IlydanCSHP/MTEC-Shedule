package com.example.mtecschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.example.mtecschedule.adapter.CoupleAdapter;
import com.example.mtecschedule.databinding.ActivityMainBinding;
import com.example.mtecschedule.model.Couple;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    List<Couple> coupleList = new ArrayList<>();
    RecyclerView coupleRecycler;
    CoupleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_schedule);

        setActionBar();

        loadCouples();

        setGroupRecycler(coupleList);

        SharedPreferences sPref = getSharedPreferences("saved_schedule", MODE_PRIVATE);
        long id = getIntent().getLongExtra("id", 0L);
        long savedId = sPref.getLong("saved_id_" + id, 0L);

        TextView pageTitle = findViewById(R.id.pageTitle);
        pageTitle.setText(getIntent().getStringExtra("title"));
        TextView date = findViewById(R.id.date);
        date.setText((new SimpleDateFormat("dd.MM.yyyy")).format((new Date())));
        ImageView favoriteImage = findViewById(R.id.favorite);

        if (savedId == id) {
            favoriteImage.setImageResource(R.drawable.heart_filled);
        } else {
            favoriteImage.setImageResource(R.drawable.heart);
        }

        favoriteImage.setOnClickListener((v) -> {

            if (savedId != id) {
                saveSchedule(sPref, id, favoriteImage);
                Toast.makeText(this, "Сохранено на главный экран!", Toast.LENGTH_SHORT).show();
            } else {
                removeSchedule(sPref, id, favoriteImage);
                Toast.makeText(this, "Удалено с главного экрана!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void removeSchedule(SharedPreferences sPref, long id, ImageView favoriteImage) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.remove("saved_id_" + id);
        ed.remove("saved_title_" + id);
        ed.apply();
        favoriteImage.setImageResource(R.drawable.heart);
    }

    private void saveSchedule(SharedPreferences sPref, long id, ImageView favoriteImage) {
        String savedTitle = getIntent().getStringExtra("title");
        SharedPreferences.Editor ed = sPref.edit();
        ed.putLong("saved_id_" + id, id);
        ed.putString("saved_title_" + id, savedTitle);
        ed.apply();
        favoriteImage.setImageResource(R.drawable.heart_filled);
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.red_gradient_navbar));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void loadCouples() {
        coupleList.add(new Couple(1, "Учебная практика по программированию", "(Янцевич Л.А.)", "8:00-9:40", 42, 1));
        coupleList.add(new Couple(2, "Учебная практика по программированию", "(Янцевич Л.А.)", "8:00-9:40", 42, 2));
        coupleList.add(new Couple(3, "Основы экономики", "(Янцевич Л.А.)", "8:00-9:40", 42, 3));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
        return true;
    }

    private void setGroupRecycler(List<Couple> items) {
        LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        coupleRecycler = findViewById(R.id.couples_recycler);
        adapter = new CoupleAdapter(this, items);
        coupleRecycler.setLayoutManager(layoutManager);
        coupleRecycler.setAdapter(adapter);
    }
}