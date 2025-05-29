package com.examly.springappfeedback.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer feedbackId;
    private Integer rating;
    private String comments;
    private LocalDate date;
    @Column(name="user_Id", nullable=false)
    private Integer userId;
    @Transient
    private User user;
    @Column(name="bus_Id ", nullable=false)
    private Integer busId;
    @Transient
    private Bus bus;

    public int getFeedbackId() {
        return feedbackId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", comments='" + comments + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                ", user=" + user +
                ", busId=" + busId +
                ", bus=" + bus +
                '}';
    }
}
