package com.example.mtecschedule;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mtecschedule.adapter.GroupAdapter;
import com.example.mtecschedule.dao.GroupDao;
import com.example.mtecschedule.database.MtecRoomDatabase;
import com.example.mtecschedule.model.Group;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GroupsFragment extends Fragment {

    FloatingActionButton addGroupButton;
    RecyclerView groupsRecycler;
    GroupAdapter groupAdapter;
    List<Group> groupList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_groups, container, false);
        groupList = new ArrayList<>();
        getGroups();

        addGroupButton = fragment.findViewById(R.id.add_group_button);
        addGroupButton.setOnClickListener(view -> addDialog());
        setRecycler(fragment);
        return fragment;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                updateDialog(item.getGroupId());
                break;
            case 1:
                deleteGroup(item.getGroupId());
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void deleteGroup(int position) {
        new Thread(() -> {
            GroupDao groupDao = MtecRoomDatabase.getInstance(getActivity()).groupDao();
            Long id = groupList.get(position).getId();
            Group group = groupDao.findById(id);
            groupDao.delete(group);

            getActivity().runOnUiThread(() -> {
                Toast.makeText(getActivity(), "Удалено!", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    private void updateDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflater = LayoutInflater.from(getActivity()).inflate(R.layout.update_group_dialog, null);
        builder.setView(inflater);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        EditText groupTitle = inflater.findViewById(R.id.group_title);
        EditText groupFullTitle = inflater.findViewById(R.id.group_full_title);
        EditText groupSpeciality = inflater.findViewById(R.id.group_speciality);
        EditText groupCourse = inflater.findViewById(R.id.group_course);
        getCurrentGroup(position, groupTitle, groupFullTitle, groupSpeciality, groupCourse);

        AppCompatButton cancelButton = inflater.findViewById(R.id.cancel_button);
        AppCompatButton applyButton = inflater.findViewById(R.id.apply_button);

        cancelButton.setOnClickListener(view -> dialog.dismiss());
        applyButton.setOnClickListener(view -> {
            if (!isInputEmpty(groupTitle)
                    && !isInputEmpty(groupFullTitle)
                    && !isInputEmpty(groupSpeciality)
                    && !isInputEmpty(groupCourse)) {
                updateGroup(position, groupTitle, groupFullTitle, groupSpeciality, groupCourse);
                dialog.dismiss();
            } else {
                Toast.makeText(getActivity(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCurrentGroup(int position,
                                 EditText groupTitle,
                                 EditText groupFullTitle,
                                 EditText groupSpeciality,
                                 EditText groupCourse) {
        new Thread(() -> {
            GroupDao groupDao = MtecRoomDatabase.getInstance(getActivity()).groupDao();
            Long id = groupList.get(position).getId();
            Group group = groupDao.findById(id);
            groupTitle.setText(group.getTitle());
            groupFullTitle.setText(group.getFullTitle());
            groupSpeciality.setText(group.getSpeciality());
            groupCourse.setText(String.valueOf(group.getCourse()));
        }).start();
    }

    private void updateGroup(int position, EditText groupTitle, EditText groupFullTitle, EditText groupSpeciality, EditText groupCourse) {
        new Thread(() -> {
            GroupDao groupDao = MtecRoomDatabase.getInstance(getActivity()).groupDao();
            Long id = groupList.get(position).getId();
            Group group = groupDao.findById(id);
            group.setTitle(groupTitle.getText().toString());
            group.setFullTitle(groupFullTitle.getText().toString());
            group.setSpeciality(groupSpeciality.getText().toString());
            group.setCourse(Integer.parseInt(groupCourse.getText().toString()));
            groupDao.update(group);

            getActivity().runOnUiThread(() -> {
                Toast.makeText(getActivity(), "Обнавлено!", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    private void getGroups() {
        GroupDao groupDao = MtecRoomDatabase.getInstance(getActivity()).groupDao();
        LiveData<List<Group>> groupsLive = groupDao.getAllLive();
        groupsLive.observe(getActivity(), new Observer<List<Group>>() {
            @Override
            public void onChanged(List<Group> groups) {
                groupList = groups;
                groupAdapter = new GroupAdapter(getActivity(), groupList, getActivity());
                groupsRecycler.setAdapter(groupAdapter);
            }
        });
    }

    private void addDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflater = LayoutInflater.from(getActivity()).inflate(R.layout.add_group_dialog, null);
        builder.setView(inflater);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        EditText groupTitle = inflater.findViewById(R.id.group_title);
        EditText groupFullTitle = inflater.findViewById(R.id.group_full_title);
        EditText groupSpeciality = inflater.findViewById(R.id.group_speciality);
        EditText groupCourse = inflater.findViewById(R.id.group_course);

        AppCompatButton cancelButton = inflater.findViewById(R.id.cancel_button);
        AppCompatButton applyButton = inflater.findViewById(R.id.apply_button);

        cancelButton.setOnClickListener(view -> dialog.dismiss());
        applyButton.setOnClickListener(view -> {
            if (!isInputEmpty(groupTitle)
                    && !isInputEmpty(groupFullTitle)
                    && !isInputEmpty(groupSpeciality)
                    && !isInputEmpty(groupCourse)) {
                addGroup(groupTitle, groupFullTitle, groupSpeciality, groupCourse);
            } else {
                Toast.makeText(getActivity(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isInputEmpty(EditText input) {
        return input.getText().toString().equals("");
    }

    private void addGroup(EditText groupTitle, EditText groupFullTitle, EditText groupSpeciality, EditText groupCourse) {
        new Thread(() -> {
            GroupDao groupDao = MtecRoomDatabase.getInstance(getActivity()).groupDao();
            Group group = new Group(groupTitle.getText().toString(),
                    groupFullTitle.getText().toString(),
                    groupSpeciality.getText().toString(),
                    Integer.parseInt(groupCourse.getText().toString()));
            groupDao.insert(group);
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getActivity(), "Добавлено!", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    private void setRecycler(View fragment) {
        groupsRecycler = fragment.findViewById(R.id.groups_recycler);
        groupAdapter = new GroupAdapter(getActivity(), groupList, getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        groupsRecycler.setLayoutManager(manager);
        groupsRecycler.setAdapter(groupAdapter);
    }
}