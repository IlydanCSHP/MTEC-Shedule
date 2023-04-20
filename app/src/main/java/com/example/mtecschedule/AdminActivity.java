package com.example.mtecschedule;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mtecschedule.adapter.ListItemAdapter;
import com.example.mtecschedule.databinding.ActivityMainBinding;
import com.example.mtecschedule.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private final List<ListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_admin);
        setActionBar(getSupportActionBar());

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

    private void loadHome() {

    }

    private void loadTeachers() {
        items.clear();
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
        items.add(new ListItem(1, "СП 105"));
        items.add(new ListItem(2, "СП 205"));
        items.add(new ListItem(3, "СП 305"));
        items.add(new ListItem(4, "СП 405"));
        setItemsRecycler(items);
    }

    private void setItemsRecycler(List<ListItem> items) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView itemsList = findViewById(R.id.items_list);
        ListItemAdapter adapter = new ListItemAdapter(this, items);
        itemsList.setLayoutManager(layoutManager);
        itemsList.setAdapter(adapter);
    }
    private void setActionBar(ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.red_gradient_navbar));
            actionBar.setTitle("Администрация");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}