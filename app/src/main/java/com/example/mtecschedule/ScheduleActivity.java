package com.example.mtecschedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtecschedule.adapter.CoupleAdapter;
import com.example.mtecschedule.databinding.ActivityMainBinding;
import com.example.mtecschedule.model.Couple;

import java.util.ArrayList;
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_gradient_navbar));
        actionBar.setDisplayHomeAsUpEnabled(true);

        coupleList.add(new Couple(1, "Учебная практика по программированию", "(Янцевич Л.А.)", "8:00-9:40", 42, 1));
        coupleList.add(new Couple(2, "Учебная практика по программированию", "(Янцевич Л.А.)", "8:00-9:40", 42, 2));
        coupleList.add(new Couple(3, "Учебная практика по программированию", "(Янцевич Л.А.)", "8:00-9:40", 42, 3));

        setGroupRecycler(coupleList);

        TextView groupTitle = findViewById(R.id.groupTitle);
        groupTitle.setText(getIntent().getStringExtra("title"));

        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.heart);

        imageView.setOnClickListener((v) -> {

            if (imageView.getDrawable().getConstantState() == getDrawable(R.drawable.heart).getConstantState()){

            imageView.setImageResource(R.drawable.heart_filled);
            Toast.makeText(this, "Сохранено на главный экран!", Toast.LENGTH_SHORT).show();
            } else {
                imageView.setImageResource(R.drawable.heart);
                Toast.makeText(this, "Удалено с главного экрана!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
        return true;
    }
    private void setGroupRecycler(List<Couple> items) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        coupleRecycler = findViewById(R.id.couples_recycler);
        adapter = new CoupleAdapter(this, items);
        coupleRecycler.setLayoutManager(layoutManager);
        coupleRecycler.setAdapter(adapter);
    }
}