package com.example.feedback3eventosfranlopez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NovelAdapter extends RecyclerView.Adapter<NovelAdapter.NovelViewHolder> {
    private List<Novel> novels;
    private Context context;

    public NovelAdapter(Context context, List<Novel> novels) {
        this.context = context;
        this.novels = novels;
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
        // Aquí puedes hacer binding de los datos de la novela a las vistas del ViewHolder
    }

    @Override
    public int getItemCount() {
        return novels != null ? novels.size() : 0;
    }

    public void setNovels(List<Novel> novels) {
        this.novels = novels;
        notifyDataSetChanged();
    }

    static class NovelViewHolder extends RecyclerView.ViewHolder {
        // Define las vistas de item_novel.xml aquí

        public NovelViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializa las vistas
        }
    }
}
