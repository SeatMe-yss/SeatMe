package com.example.myapplication.Objects;

public class Order {
    String Rest_name;
    String Order_id;
    String Order_meal;
    String time;
    String year,day,month;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Order(){}
    public Order(String rest_name, String Order_id,String Order_meal, String time,String year,String month, String day ) {
        this.Rest_name = rest_name;
        this.Order_id = Order_id;
        this.Order_meal=Order_meal;
        this.time=time;
        this.year=year;
        this.month=month;
        this.day=day;
    }


    public void setOrder_id(String Order_id) {
        Order_id=Order_id;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public void setRest_name(String Rest_name) {
        Rest_name = Rest_name;
    }

    public String getRest_name() {
        return Rest_name;
    }

    public String getOrder_meal() {
        return Order_meal;
    }

    public void setOrder_meal(String phone) { Order_meal = Order_meal; }

    public String gettime() {
        return time;
    }

    public void settime(String time) { time = time; }




    @Override
    public String toString() {
        return "order{" +
                "order_id='" + getOrder_id() + '\'' +
                ", time='" + gettime() + '\'' +
                '}';
    }



}
