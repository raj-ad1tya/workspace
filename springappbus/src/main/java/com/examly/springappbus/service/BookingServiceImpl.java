package com.examly.springappbus.service;

import com.examly.springappbus.client.UserServiceClient;
import com.examly.springappbus.model.Booking;
import com.examly.springappbus.model.Bus;
import com.examly.springappbus.model.User;
import com.examly.springappbus.repository.BookingRepo;
import com.examly.springappbus.repository.BusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private BusRepo busRepo;

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public Booking addBooking(Booking booking) {
        Integer userId = booking.getUser().getUserId();
        Boolean userExists = userServiceClient.userExistsById(userId).getBody();

        if(!userExists)
            return null;

        Integer busId = booking.getBus().getBusId();
        Bus bus = busRepo.findById(busId).orElse(null);

        booking.setUserId(userId);
        booking.setBus(bus);

        System.out.println(booking);

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
        return bookingRepo.findByUserId(userId).orElse(null);
    }
}