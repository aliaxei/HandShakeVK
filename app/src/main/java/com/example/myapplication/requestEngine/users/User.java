package com.example.myapplication.requestEngine.users;

public class User {
    private String photoUrl;
    private String firstName;
    private String lastName;
    private int id;

    public User() {
    }

    public User(String photoUrl, String firstName, String lastName, int id) {
        this.photoUrl = photoUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }
}

