package com.examly.springappfeedback.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int feedbackId;
    private String comments;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name="user_Id", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="bus_Id ", nullable=false)
    private Bus bus;

    public int getFeedbackId() {
        return feedbackId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", bus=" + bus +
                '}';
    }
}
