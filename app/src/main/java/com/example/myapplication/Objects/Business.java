package com.example.myapplication.Objects;

import java.util.ArrayList;
import java.util.List;

public class Business {

    public String mail;
    public String password;
    public String phone;
    public String id;
    public String name_rest;
    public List<Order> Orders=new ArrayList<>();
    public int max_people;




    public Business() {};

    public Business(String mail, String password,String phone, String id) {
        this.mail = mail;
        this.password = password;
        this.phone=phone;
        this.name_rest=" ";
        this.id=id;
        this.Orders.add(new Order());

    }

    public String getname_rest(){return this.name_rest;}

    public void setname_rest(String r){this.name_rest=r;}

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

    public int getmax_people(){ return this.max_people ;}

    public void setMax_people(int max_people) { this.max_people = max_people; }



}
