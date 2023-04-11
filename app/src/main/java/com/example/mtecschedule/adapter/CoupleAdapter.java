package com.example.mtecschedule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtecschedule.R;
import com.example.mtecschedule.model.Couple;

import java.util.List;

public class CoupleAdapter extends RecyclerView.Adapter<CoupleAdapter.CoupleViewHolder> {
    Context context;
    List<Couple> couples;

    public CoupleAdapter(Context context, List<Couple> couples) {
        this.context = context;
        this.couples = couples;
    }

    @NonNull
    @Override
    public CoupleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View coupleItem = LayoutInflater.from(context).inflate(R.layout.schedule_item, parent, false);

        return new CoupleViewHolder(coupleItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CoupleViewHolder holder, int position) {
        holder.coupleTitle.setText(couples.get(position).getTitle() + couples.get(position).getTeacher());
        holder.coupleNumber.setText((couples.get(position).getNumber()) + " " + holder.coupleNumber.getText());
        holder.coupleTime.setText(couples.get(position).getTime());
        holder.coupleRoom.setText("АУД." + couples.get(position).getRoom());
    }

    @Override
    public int getItemCount() {
        return couples.size();
    }

    public static final class CoupleViewHolder extends RecyclerView.ViewHolder {

        TextView coupleTitle;
        TextView coupleNumber;
        TextView coupleTime;
        TextView coupleRoom;

        public CoupleViewHolder(@NonNull View itemView) {
            super(itemView);

            coupleTitle = itemView.findViewById(R.id.couple_title);
            coupleNumber = itemView.findViewById(R.id.couple_number);
            coupleTime = itemView.findViewById(R.id.couple_time);
            coupleRoom = itemView.findViewById(R.id.couple_room);
        }
    }
}
