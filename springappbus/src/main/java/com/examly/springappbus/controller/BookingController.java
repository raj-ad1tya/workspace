package com.examly.springappbus.controller;

import com.examly.springappbus.model.Booking;
import com.examly.springappbus.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        return new ResponseEntity<Booking>(bookingService.addBooking(booking), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int bookingId) {
        Booking fetchedBooking = bookingService.getBookingById(bookingId);
        int status = 200;

        if(fetchedBooking == null)
            status = 404;

        return new ResponseEntity<Booking>(fetchedBooking, HttpStatusCode.valueOf(status));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable int userId) {
        List<Booking> fetchedBookingsByUserId = bookingService.getBookingsByUserId(userId);
        int status = 200;

        if(fetchedBookingsByUserId == null)
            status = 404;

        return new ResponseEntity<List<Booking>>(fetchedBookingsByUserId, HttpStatusCode.valueOf(status));
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> fetchedBookingsList = bookingService.getAllBookings();
        int status = 200;

        if(fetchedBookingsList == null)
            status = 400;

        return new ResponseEntity<List<Booking>>(fetchedBookingsList, HttpStatusCode.valueOf(status));
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int bookingId, @RequestBody Booking bookingDetails) {
        Booking updatedBooking = bookingService.updateBooking(bookingId, bookingDetails);
        int status = 200;

        if(updatedBooking == null)
            status = 404;

        return new ResponseEntity<Booking>(updatedBooking, HttpStatusCode.valueOf(status));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable int bookingId) {
        Booking deletedBooking = bookingService.deleteBooking(bookingId);
        int status = 200;

        if(deletedBooking == null)
            status = 404;

        return new ResponseEntity<Booking>(deletedBooking, HttpStatusCode.valueOf(status));
    }

}
