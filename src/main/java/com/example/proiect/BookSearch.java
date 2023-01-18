package com.example.proiect;

import org.json.JSONArray;

public class BookSearch {
    Integer ID;
    String titlu;
    String editura;
    String autori;
    Integer an;
    String ISBN;
    String limba;
    Integer pagini;
    Float Rating;

    public BookSearch(Integer ID, String titlu, String editura, String autori, Integer an, String ISBN, String limba, Integer pagini, Float rating) {
        this.ID = ID;
        this.titlu = titlu;
        this.editura = editura;
        this.autori = autori;
        this.an = an;
        this.ISBN = ISBN;
        this.limba = limba;
        this.pagini = pagini;
        Rating = rating;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public String getAutori() {
        return autori;
    }

    public void setAutori(JSONArray autori) {
        this.autori = String.valueOf(autori);
    }

    public Integer getAn() {
        return an;
    }

    public void setAn(Integer an) {
        this.an = an;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getLimba() {
        return limba;
    }

    public void setLimba(String limba) {
        this.limba = limba;
    }

    public Integer getPagini() {
        return pagini;
    }

    public void setPagini(Integer pagini) {
        this.pagini = pagini;
    }

    public Float getRating() {
        return Rating;
    }

    public void setRating(Float rating) {
        Rating = rating;
    }
}
