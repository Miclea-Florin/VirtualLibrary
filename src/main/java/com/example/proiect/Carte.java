package com.example.proiect;

import org.json.JSONArray;

import java.util.ArrayList;

public class Carte {
    protected String title;
    protected String publisher;
    protected String ISBN;
    protected String language;
    protected JSONArray authors;
    protected int date_published;
    protected int pages;
    protected double rating; // ??
    protected int id=0;

    public void setId(int id) {
        this.id = id;
    }

    public Carte(String title, String publisher, String ISBN, String language, JSONArray authors, int date_published, int pages, double rating) {
        this.title = title;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.language = language;
        this.authors = authors;
        this.date_published = date_published;
        this.pages = pages;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "Titlu='" + title + '\'' +
                ", Editura='" + publisher + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", Limba='" + language + '\'' +
                ", Lista autori:=" + authors +
                ", Data publicarii=" + date_published +
                ", Numar pagini=" + pages +
                ", rating=" + rating +
                ", id=" + id +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public JSONArray getAuthors() {
        return authors;
    }

    public void setAuthors(JSONArray authors) {
        this.authors = authors;
    }

    public int getDate_published() {
        return date_published;
    }

    public void setDate_published(int date_published) {
        this.date_published = date_published;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}



