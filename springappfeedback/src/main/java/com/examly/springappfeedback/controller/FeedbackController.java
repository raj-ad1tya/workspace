package com.examly.springappfeedback.controller;

import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        return new ResponseEntity<Feedback>(feedbackService.createFeedback(feedback), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int id){
        Feedback fetchedFeedback = feedbackService.getFeedbackById(id);
        int status = 200;

        if (fetchedFeedback == null)
            status = 400;

        return new ResponseEntity<Feedback>(fetchedFeedback, HttpStatusCode.valueOf(status));
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> fetchedFeedbacksList = feedbackService.getAllFeedbacks();
        int status = 200;

        if (fetchedFeedbacksList == null)
            status = 400;

        return new ResponseEntity<List<Feedback>>(fetchedFeedbacksList, HttpStatusCode.valueOf(status));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable int userId) {
        List<Feedback> fetchedFeedbacksListByUserId = feedbackService.getFeedbacksByUserId(userId);
        int status = 200;

        if (fetchedFeedbacksListByUserId == null)
            status = 404;

        return new ResponseEntity<List<Feedback>>(fetchedFeedbacksListByUserId, HttpStatusCode.valueOf(status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable int id) {
        Feedback deletedFeedback = feedbackService.deleteFeedback(id);
        int status = 200;

        if (deletedFeedback == null)
            status = 404;

        return new ResponseEntity<Feedback>(deletedFeedback, HttpStatusCode.valueOf(status));
    }

}
