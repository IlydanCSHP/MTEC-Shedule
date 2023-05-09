package com.example.mtecschedule.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtecschedule.R;
import com.example.mtecschedule.model.Pair;

import java.util.List;

public class PairAdapter extends RecyclerView.Adapter<PairAdapter.PairViewHolder> {
    Context context;
    List<Pair> pairs;
    Activity activity;

    public PairAdapter(Context context, List<Pair> pairs, Activity activity) {
        this.context = context;
        this.pairs = pairs;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pair_item, parent, false);
        return new PairViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PairViewHolder holder, int position) {
        Pair pair = pairs.get(position);
        holder.coupleTitle.setText(pair.getTitle() + "\n" + pair.getTeacher());
        holder.coupleNumber.setText(pair.getNumber() + " Пара");
        holder.coupleRoom.setText(pair.getRoom() + " АУД.");
    }

    @Override
    public int getItemCount() {
        return pairs.size();
    }

    public static class PairViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView coupleTitle;
        TextView coupleNumber;
        TextView coupleRoom;

        public PairViewHolder(@NonNull View itemView) {
            super(itemView);

            coupleTitle = itemView.findViewById(R.id.couple_title);
            coupleNumber = itemView.findViewById(R.id.couple_number);
            coupleRoom = itemView.findViewById(R.id.couple_room);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(), 0, 0, "Изменить");
            menu.add(getAdapterPosition(), 1, 0, "Удалить");
        }
    }
}
