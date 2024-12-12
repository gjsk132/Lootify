package com.gjsk.lootify.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gjsk.lootify.R;

import java.util.List;

public class ARIconAdapter extends RecyclerView.Adapter<ARIconAdapter.ARIconViewHolder> {
    private final List<Integer> iconList;
    private int selectedPosition = -1;

    public ARIconAdapter(List<Integer> iconList) {
        this.iconList = iconList;
    }

    @NonNull
    @Override
    public ARIconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ar_icon_item, parent, false);
        return new ARIconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ARIconViewHolder holder, int position) {
        int iconRes = iconList.get(position);
        holder.iconImageView.setImageResource(iconRes);

        if (selectedPosition == position) {
            holder.itemView.setBackgroundResource(R.drawable.blue_focus);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.white_frame_mid);
        }

        holder.itemView.setOnClickListener(view -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition == RecyclerView.NO_POSITION) {
                return;
            }
            int previousPosition = selectedPosition;
            selectedPosition = currentPosition;

            if (previousPosition != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousPosition);
            }
            notifyItemChanged(selectedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return iconList.size();
    }

    public static class ARIconViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iconImageView;

        public ARIconViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.ar_icon_image);
        }
    }
}
