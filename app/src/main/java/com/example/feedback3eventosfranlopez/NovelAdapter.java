package com.example.feedback3eventosfranlopez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NovelAdapter extends RecyclerView.Adapter<NovelAdapter.NovelViewHolder> {
    private List<Novel> novels;
    private Context context;

    public NovelAdapter(List<Novel> novels, Context context) {
        this.novels = novels;
        this.context = context;
    }

    @NonNull
    @Override
    public NovelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_novel, parent, false);
        return new NovelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NovelViewHolder holder, int position) {
        Novel novel = novels.get(position);
        holder.titleTextView.setText(novel.getTitle());
        holder.authorTextView.setText(novel.getAuthor());

        // Cambia el ícono de la estrella dependiendo de si es favorito o no
        holder.favoriteIcon.setImageResource(
                novel.isFavorite() ? R.drawable.ic_star_filled : R.drawable.ic_star_empty
        );

        // Cambia el estado de favorito al hacer clic en la estrella
        holder.favoriteIcon.setOnClickListener(v -> {
            novel.setFavorite(!novel.isFavorite());
            notifyItemChanged(position);
        });
    }


    @Override
    public int getItemCount() {
        return novels.size();
    }


    static class NovelViewHolder extends RecyclerView.ViewHolder {
        public ImageSwitcher favoriteIcon;
        TextView titleTextView;
        TextView authorTextView;

        NovelViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
        }
    }
}
