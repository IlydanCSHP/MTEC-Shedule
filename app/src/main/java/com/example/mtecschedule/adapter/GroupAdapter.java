package com.example.mtecschedule.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtecschedule.AdminActivity;
import com.example.mtecschedule.R;
import com.example.mtecschedule.ScheduleActivity;
import com.example.mtecschedule.model.Group;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupAdapterViewHolder> {
    Context context;
    List<Group> groups;
    Activity activity;

    public GroupAdapter(Context context, List<Group> groups, Activity activity) {
        this.context = context;
        this.groups = groups;
        this.activity = activity;
    }

    @NonNull
    @Override
    public GroupAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.group_item, parent, false);
        return new GroupAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapterViewHolder holder, int position) {
        Group group = groups.get(position);
        holder.groupTitle.setText(group.getTitle());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ScheduleActivity.class);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public static class GroupAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView groupTitle;

        public GroupAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            groupTitle = itemView.findViewById(R.id.group_title);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(), 0, 0, "Изменить");
            menu.add(getAdapterPosition(), 1, 0, "Удалить");
        }
    }
}
