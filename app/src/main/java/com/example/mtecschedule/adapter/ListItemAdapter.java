package com.example.mtecschedule.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtecschedule.R;
import com.example.mtecschedule.ScheduleActivity;
import com.example.mtecschedule.model.ListItem;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {

    Context context;
    List<ListItem> items;

    public ListItemAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        return new ListItemViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        holder.itemText.setText(items.get(position).getTitle());

        holder.itemView.setOnClickListener((v) -> {

            Intent intent = new Intent(context, ScheduleActivity.class);

            intent.putExtra("title", holder.itemText.getText());
            intent.putExtra("id", (Long) items.get(position).getId());
            Long test = items.get(position).getId();
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class ListItemViewHolder extends RecyclerView.ViewHolder {

        TextView itemText;

        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemText = itemView.findViewById(R.id.list_item_text);
        }
    }
}
