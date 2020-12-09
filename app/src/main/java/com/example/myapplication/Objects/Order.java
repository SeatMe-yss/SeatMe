package com.example.myapplication.Objects;

 public  class  Order {
    String Rest_name;
    static String Order_id;
    String Order_meal;
    String time;
    String year,day,month;
    String Id_Client, Id_Bus;
    int people;


     public String getId_Bus() {
         return this.Id_Bus;
     }

     public String getId_Client() {
         return this.Id_Client;
     }

     public void setId_Bus(String id_Bus) {
         this.Id_Bus = id_Bus;
     }

     public void setId_Client(String id_Client) {
         this.Id_Client = id_Client;
     }

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
    public Order(String rest_name,String Order_meal, String Id_Client, String Id_Bus , String time,String year,String month, String day) {
        this.Rest_name = rest_name;
        this.Order_meal=Order_meal;
        this.time=time;
        this.year=year;
        this.month=month;
        this.day=day;
        this.Id_Bus=Id_Bus;
        this.Id_Client=Id_Client;
    }


    public void setOrder_id(String Order_id) {
        this.Order_id=Order_id;
    }

    public String getOrder_id() {
        return this.Order_id;
    }

    public void setRest_name(String Rest_name) {
        this.Rest_name = Rest_name;
    }

    public String getRest_name() {
        return this.Rest_name;
    }

    public String getOrder_meal() {
        return this.Order_meal;
    }

    public void setOrder_meal(String Order_meal) { this.Order_meal = Order_meal; }

     public int getPeople(){ return this.people;}

     public void setPeople(int people) { this.people = people; }

     @Override
    public String toString() {
        return "Time: " + getTime() +
                "\nDate: " + getDay() +  "." + getMonth() + "." + getYear() +
                "\nOrder meal: " + getOrder_meal() +
                "\nNumber of people: "+getPeople() +
                "\nOrder Id: " + getOrder_id();

    }



}
