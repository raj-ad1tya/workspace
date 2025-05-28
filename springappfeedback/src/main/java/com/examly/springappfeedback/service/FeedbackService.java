package com.examly.springappfeedback.service;

import com.examly.springappfeedback.model.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {

    Feedback createFeedback(Feedback feedback);
    Feedback getFeedbackById(int id);
    List<Feedback> getAllFeedbacks();
    Feedback deleteFeedback(int id);
    List<Feedback> getFeedbacksByUserId(int userId);

}
