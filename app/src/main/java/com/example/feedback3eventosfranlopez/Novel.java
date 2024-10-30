
package com.example.feedback3eventosfranlopez;

public class Novel {
    private int id;
    private String title;
    private String author;
    private boolean isFavorite;

    // Constructor con ID para cuando se lee desde la base de datos
    public Novel(int id, String title, String author, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isFavorite = isFavorite;
    }

    // Constructor sin ID para cuando se crea una nueva novela
    public Novel(String title, String author, boolean isFavorite) {
        this.title = title;
        this.author = author;
        this.isFavorite = isFavorite;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
