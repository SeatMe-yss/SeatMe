package com.example.myapplication.Objects;

public class Client {
    public String mail;
    public String password;
    public String phone;
    public String id;


    public Client() {}

    public Client(String mail, String password,String phone, String id) {
        this.mail = mail;
        this.password = password;
        this.phone=phone;
        this.id=id;
    }


    public void setMail(String mail) {
        mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setPassword(String Password) {
        password = Password;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) { this.phone = phone; }

    public String getId(){return id;}



    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", phone="+phone+'\''+
                '}';
    }

}
