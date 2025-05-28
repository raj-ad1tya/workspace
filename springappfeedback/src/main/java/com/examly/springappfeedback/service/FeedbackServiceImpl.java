package com.examly.springappfeedback.service;

import com.examly.springappfeedback.model.Bus;
import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.model.User;
import com.examly.springappfeedback.repository.BusRepo;
import com.examly.springappfeedback.repository.FeedbackRepo;
import com.examly.springappfeedback.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BusRepo busRepo;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        String email = feedback.getUser().getEmail();
        User user = userRepo.findByEmail(email).orElse(null);

        Integer busId = feedback.getBus().getBusId();
        Bus bus = busRepo.findById(busId).orElse(null);

        feedback.setUser(user);
        feedback.setBus(bus);

        return feedbackRepo.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(int id) {
        return feedbackRepo.findById(id).orElse(null);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll();
    }

    @Override
    public Feedback deleteFeedback(int id) {
        Feedback fetchedFeedback = feedbackRepo.findById(id).orElse(null);

        if(fetchedFeedback != null)
            feedbackRepo.delete(fetchedFeedback);

        return fetchedFeedback;
    }

    @Override
    public List<Feedback> getFeedbacksByUserId(int userId) {
        return feedbackRepo.findByUserUserId(userId).orElse(null);
    }
}
