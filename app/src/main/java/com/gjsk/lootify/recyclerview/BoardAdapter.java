package com.gjsk.lootify.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;

import com.gjsk.lootify.R;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private final List<Board> dataList;

    public BoardAdapter(List<Board> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        Board data = dataList.get(position);
        holder.contents.setText(data.getName());
        holder.contentsDetail.setText(data.getDetail());

        holder.itemView.findViewById(R.id.delete_icon).setOnClickListener(view -> {
            removeItem(position);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void removeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataList.size());
    }

    public static class BoardViewHolder extends RecyclerView.ViewHolder {
        private final TextView contents;
        private final TextView contentsDetail;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            contents = itemView.findViewById(R.id.contents);
            contentsDetail = itemView.findViewById(R.id.contents_details);
        }
    }
}
