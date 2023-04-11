package com.example.mtecschedule;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtecschedule.adapter.ListItemAdapter;
import com.example.mtecschedule.databinding.ActivityMainBinding;
import com.example.mtecschedule.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RecyclerView itemsList;
    ListItemAdapter adapter;
    TextView noSchedule;
    List<ListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noSchedule = findViewById(R.id.no_schedule);
        binding.navbar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    noSchedule.setText("Нет сохраненных расписаний");
                    items.clear();
                    setItemsRecycler(items);
                    break;
                case R.id.groupTitle:
                    items.clear();
                    noSchedule.setText("");
                    items.add(new ListItem(1, "СП 105"));
                    items.add(new ListItem(2, "СП 205"));
                    items.add(new ListItem(3, "СП 305"));
                    items.add(new ListItem(4, "СП 405"));
                    setItemsRecycler(items);
                    break;
                case R.id.teachers:
                    items.clear();
                    noSchedule.setText("");
                    items.add(new ListItem(1, "Апетенок Оксана Николаевна"));
                    items.add(new ListItem(2, "Бекназарян Светлана Александровна"));
                    items.add(new ListItem(3, "Буринская Анна Игоревна"));
                    items.add(new ListItem(4, "Василевич Андрей Еремеевич"));
                    items.add(new ListItem(5, "Васюта Татьяна Валерьевна"));
                    items.add(new ListItem(6, "Гайдукевич Руслан Иванович"));
                    setItemsRecycler(items);
                    break;

            }
            return true;
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_gradient_navbar));

    }

    private void setItemsRecycler(List<ListItem> items) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        itemsList = findViewById(R.id.items_list);
        adapter = new ListItemAdapter(this, items);
        itemsList.setLayoutManager(layoutManager);
        itemsList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
//        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }
}