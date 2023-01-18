package com.example.proiect;

public class UserSearch {

    Integer id;
    String Firstname;
    String Lastname;
    String Username;

    public UserSearch(Integer id, String firstname, String lastname, String username) {
        this.id = id;
        Firstname = firstname;
        Lastname = lastname;
        Username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
