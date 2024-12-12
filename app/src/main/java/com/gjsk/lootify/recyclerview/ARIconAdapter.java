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
    private int selectedPosition = 0;

    public ARIconAdapter(List<Integer> iconList) {
        this.iconList = iconList;
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public void setSelectedIcon(int iconRes){
        if (iconList.contains(iconRes)){
            selectedPosition = iconList.indexOf(iconRes);
        }else{
            selectedPosition = -1;
        }
        selectedPosition = iconList.indexOf(iconRes);
        notifyDataSetChanged();
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
            int previousPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();

            if (previousPosition != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousPosition);
            }
            notifyItemChanged(selectedPosition);

            if (onItemClickListener != null){
                onItemClickListener.onItemClick(iconRes);
            }
        });
    }

    public interface OnItemClickListener{
        void onItemClick(int iconRes);
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
