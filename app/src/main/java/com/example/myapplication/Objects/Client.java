package com.example.myapplication.Objects;

public class Client {
    String username;
    String password;

    public Client() {}

    public Client(String username, String password) {
        this.username = username;
        this.password = password;

    }


    public void setUsername(String Username) {
        username = Username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String Password) {
        password = Password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
