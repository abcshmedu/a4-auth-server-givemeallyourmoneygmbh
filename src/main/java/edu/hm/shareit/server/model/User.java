package edu.hm.shareit.server.model;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/26/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public class User {


    private String userName;
    private boolean isAdmin;
    private String password;
    private String userId;

    public User() {

    }

    public User(String userId, String username, String password) {
        this.userName = username;
        this.password = password;
        this.userId = userId;

    }

    public User(String userId, String username, String password, boolean isAdmin) {
        this.userName = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.userId = userId;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", isAdmin=" + isAdmin +
                ", password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
