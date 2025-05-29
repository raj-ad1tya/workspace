package com.examly.springappfeedback.service;

import com.examly.springappfeedback.client.BusServiceClient;
import com.examly.springappfeedback.client.UserServiceClient;
import com.examly.springappfeedback.model.Bus;
import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.model.User;
import com.examly.springappfeedback.repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private BusServiceClient busServiceClient;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        Integer userId = feedback.getUser().getUserId();
        Boolean userExists = userServiceClient.userExistsById(userId).getBody();

        if(!userExists)
            return null;

        Integer busId = feedback.getBus().getBusId();
        Bus bus = busServiceClient.getBusById(busId).getBody();

        if(bus == null)
            return null;

        feedback.setUserId(userId);
        feedback.setBusId(busId);

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
        return feedbackRepo.findByUserId(userId).orElse(null);
    }
}
