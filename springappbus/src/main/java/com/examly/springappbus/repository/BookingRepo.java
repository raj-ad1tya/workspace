package com.examly.springappbus.repository;

import com.examly.springappbus.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
    Optional<List<Booking>> findByUserId(Integer userId);
}
