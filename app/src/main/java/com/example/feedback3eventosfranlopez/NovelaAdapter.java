package com.example.feedback3eventosfranlopez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NovelaAdapter extends RecyclerView.Adapter<NovelaAdapter.NovelaViewHolder> {
    private List<Novela> novelas;
    private Context context;
    private SQLiteHelper dbHelper;

    public NovelaAdapter(List<Novela> novelas, Context context) {
        this.novelas = novelas;
        this.context = context;
        this.dbHelper = new SQLiteHelper(context);
    }

    @Override
    public NovelaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_novela, parent, false);
        return new NovelaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NovelaViewHolder holder, int position) {
        Novela novela = novelas.get(position);
        holder.bind(novela);
    }

    @Override
    public int getItemCount() {
        return novelas.size();
    }

    class NovelaViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private ImageView favorito;

        public NovelaViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            favorito = itemView.findViewById(R.id.icono_favorito);
        }

        public void bind(Novela novela) {
            titulo.setText(novela.getTitulo());
            favorito.setImageResource(novela.isFavorito() ? R.drawable.ic_star_filled : R.drawable.ic_star_empty);

            favorito.setOnClickListener(v -> {
                novela.setFavorito(!novela.isFavorito());
                dbHelper.updateNovela(novela);
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}
