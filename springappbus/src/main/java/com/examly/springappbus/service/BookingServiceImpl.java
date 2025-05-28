package com.examly.springappbus.service;

import com.examly.springappbus.model.Booking;
import com.examly.springappbus.model.Bus;
import com.examly.springappbus.model.User;
import com.examly.springappbus.repository.BookingRepo;
import com.examly.springappbus.repository.BusRepo;
import com.examly.springappbus.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BusRepo busRepo;

    @Override
    public Booking addBooking(Booking booking) {
        Integer userId = booking.getUser().getUserId();
        User user = userRepo.findById(userId).orElse(null);

        Integer busId = booking.getBus().getBusId();
        Bus bus = busRepo.findById(busId).orElse(null);

        booking.setUser(user);
        booking.setBus(bus);

        return bookingRepo.save(booking);
    }

    @Override
    public Booking getBookingById(int bookingId) {
        return bookingRepo.findById(bookingId).orElse(null);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public Booking updateBooking(int bookingId, Booking bookingDetails) {
        Booking existingBooking = bookingRepo.findById(bookingId).orElse(null);

        if(existingBooking == null)
            return null;

        existingBooking.setBookingDate(bookingDetails.getBookingDate());
        existingBooking.setBus(bookingDetails.getBus());
        existingBooking.setUser(bookingDetails.getUser());
        existingBooking.setStatus(bookingDetails.getStatus());
        existingBooking.setSeatNumbers(bookingDetails.getSeatNumbers());
        existingBooking.setNumberOfSeats(bookingDetails.getNumberOfSeats());

        return bookingRepo.save(existingBooking);
    }

    @Override
    public Booking deleteBooking(int bookingId) {
        Optional<Booking> existingBooking = bookingRepo.findById(bookingId);

        if(existingBooking.isPresent())
            bookingRepo.delete(existingBooking.get());

        return existingBooking.orElse(null);
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        return bookingRepo.findByUserUserId(userId).orElse(null);
    }
}