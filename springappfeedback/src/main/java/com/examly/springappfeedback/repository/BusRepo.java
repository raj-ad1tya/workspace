package com.examly.springappfeedback.repository;

import com.examly.springappfeedback.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepo extends JpaRepository<Bus, Integer> {
}
