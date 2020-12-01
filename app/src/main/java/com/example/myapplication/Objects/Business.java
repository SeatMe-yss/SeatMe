package com.example.myapplication.Objects;

import java.util.List;

public class Business {

    public String mail;
    public String password;
    public String phone;
    public String id;
    public List<Order> Orders;
    public List<Integer>tables;


    public Business() {};

    public Business(String mail, String password,String phone, String id) {
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

    public void addOrder(Order o){
        this.Orders.add(o);
    }

    public void gettable(List<Integer> table){
        tables.addAll(table);
    }


}
