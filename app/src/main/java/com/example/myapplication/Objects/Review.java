package com.example.myapplication.Objects;

public class Review {
    String review_message;
    float numStars;
    static String review_id;


    public String getReview_message() {
        return this.review_message;
    }

    public void setReview_message(String review_message) {
        this.review_message = review_message;
    }

    public float getNumStars() {
        return this.numStars;
    }

    public void setNumStars(float numStars) {
        this.numStars = numStars;
    }

    public void setReview_id(String review_id) {
        this.review_id=review_id;
    }

    public String getReview_id() {
        return this.review_id;
    }


    public Review(){}
    public Review( String review_message, float numStars) {
        this.review_message = review_message;
        this.numStars = numStars;
    }

    public String toString() {
        return "ביקורת :" + getReview_message()+
                "\nמספר כוכבים :" + getNumStars();

    }




}
