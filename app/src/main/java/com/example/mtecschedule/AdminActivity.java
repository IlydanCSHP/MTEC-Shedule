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
import com.example.mtecschedule.databinding.ActivityAdminBinding;
import com.example.mtecschedule.databinding.ActivityMainBinding;
import com.example.mtecschedule.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private final List<ListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminBinding binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setActionBar(getSupportActionBar());
        getGroups();

        binding.navbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.groups) {
                getGroups();
            }
            if (itemId == R.id.teachers) {
                getTeachers();
            }
            return true;
        });
    }

    private void getTeachers() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new TeachersFragment())
                .commit();
    }

    private void getGroups() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new GroupsFragment())
                .commit();
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