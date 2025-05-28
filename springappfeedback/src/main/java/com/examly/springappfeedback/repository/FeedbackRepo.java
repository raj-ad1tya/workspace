package com.examly.springappfeedback.repository;

import com.examly.springappfeedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
    Optional<List<Feedback>> findByUserUserId(Integer userId);
}
