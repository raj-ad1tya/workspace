package com.examly.springappbus.service;

import com.examly.springappbus.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    Booking addBooking(Booking booking);
    Booking getBookingById(int bookingId);
    List<Booking> getAllBookings();
    Booking updateBooking(int bookingId, Booking bookingDetails);
    Booking deleteBooking(int bookingId);
    List<Booking> getBookingsByUserId(int userId);

}
