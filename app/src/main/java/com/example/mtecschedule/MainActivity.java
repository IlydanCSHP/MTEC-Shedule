package com.example.mtecschedule;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.example.mtecschedule.adapter.ListItemAdapter;
import com.example.mtecschedule.databinding.ActivityMainBinding;
import com.example.mtecschedule.model.ListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView noSchedule;
    private final List<ListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noSchedule = findViewById(R.id.no_schedule);

        loadSchedule();
        setItemsRecycler(items);

        ActionBar actionBar = getSupportActionBar();

        setActionBar(actionBar);

        binding.navbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                loadHome();
            } else if (itemId == R.id.groups) {
                loadGroups();
            } else if (itemId == R.id.teachers) {
                loadTeachers();
            }
            return true;
        });
    }

    private void setActionBar(ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.red_gradient_navbar));
        }
    }

    private void loadHome() {
        items.clear();
        setItemsRecycler(items);
        loadSchedule();
    }

    private void loadTeachers() {
        items.clear();
        noSchedule.setText("");
        items.add(new ListItem(5, "Апетенок Оксана Николаевна"));
        items.add(new ListItem(6, "Бекназарян Светлана Александровна"));
        items.add(new ListItem(7, "Буринская Анна Игоревна"));
        items.add(new ListItem(8, "Василевич Андрей Еремеевич"));
        items.add(new ListItem(9, "Васюта Татьяна Валерьевна"));
        items.add(new ListItem(10, "Гайдукевич Руслан Иванович"));
        setItemsRecycler(items);
    }

    private void loadGroups() {
        items.clear();
        noSchedule.setText("");
        items.add(new ListItem(1, "СП 105"));
        items.add(new ListItem(2, "СП 205"));
        items.add(new ListItem(3, "СП 305"));
        items.add(new ListItem(4, "СП 405"));
        setItemsRecycler(items);
    }

    private void loadSchedule() {
        SharedPreferences sPref = getSharedPreferences("saved_schedule", MODE_PRIVATE);
        if (sPref.getAll().isEmpty()) {
            noSchedule.setText("Нет сохраненных расписаний");
        } else {
            noSchedule.setText("");
            for (Map.Entry<String, ?> entry : sPref.getAll().entrySet()) {
                String[] titleDiv = entry.getKey().split("_");
                String title;

                if (entry.getValue() instanceof String) {
                    title = entry.getKey();
                } else {
                    continue;
                }
                Long id = Long.parseLong(titleDiv[2]);

                items.add(new ListItem(sPref.getLong("saved_id_" + id, 0L), sPref.getString(title, "")));
            }

        }
    }

    private void setItemsRecycler(List<ListItem> items) {
        LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView itemsList = findViewById(R.id.items_list);
        ListItemAdapter adapter = new ListItemAdapter(this, items);
        itemsList.setLayoutManager(layoutManager);
        itemsList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}