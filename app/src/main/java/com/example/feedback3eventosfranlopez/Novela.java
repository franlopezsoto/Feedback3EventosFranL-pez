package com.example.feedback3eventosfranlopez;
public class Novela {
    private int id;
    private String titulo;
    private String autor;
    private String resena;
    private boolean favorito;

    public Novela(int id, String titulo, String autor, String resena, boolean favorito) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.resena = resena;
        this.favorito = favorito;
    }

    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getResena() {
        return resena;
    }
    public boolean isFavorito() {
        return favorito;
    }
    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
    public void setResena(String resena) {
        this.resena = resena;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    @Override
    public String toString() {
        return "Novela{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", resena='" + resena + '\'' +
                ", favorito=" + favorito +
                '}';
    }
}

