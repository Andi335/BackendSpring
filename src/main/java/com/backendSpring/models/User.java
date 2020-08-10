package com.backendSpring.models;

import javax.validation.constraints.NotNull;


public class User {

    private int userId;
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    private byte[] userFile;
    private String fileName;

    public User(int userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public byte[] getUserFile() {
        return userFile;
    }

    public void setUserFile(byte[] userFile) {
        this.userFile = userFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
