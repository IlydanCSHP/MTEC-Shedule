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

import com.example.mtecschedule.R;
import com.example.mtecschedule.SingleScheduleActivity;
import com.example.mtecschedule.model.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    Context context;
    List<Schedule> schedules;
    Activity activity;

    public ScheduleAdapter(Context context, List<Schedule> schedules, Activity activity) {
        this.context = context;
        this.schedules = schedules;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.group_item, parent, false);
        return new ScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        holder.scheduleDate.setText(schedules.get(position).getDate());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, SingleScheduleActivity.class);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView scheduleDate;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);

            scheduleDate = itemView.findViewById(R.id.group_title);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(), 0, 0, "Изменить");
            menu.add(getAdapterPosition(), 1, 0, "Удалить");
        }

    }
}
