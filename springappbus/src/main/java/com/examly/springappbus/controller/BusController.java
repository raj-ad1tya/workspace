package com.examly.springappbus.controller;

import com.examly.springappbus.model.Bus;
import com.examly.springappbus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        return new ResponseEntity<Bus>(busService.addBus(bus), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{busId}")
    public ResponseEntity<Bus> getBusById(@PathVariable int busId) {
        Bus fetchedBus = busService.getBusById(busId);
        int status = 200;

        if(fetchedBus == null)
            status = 404;

        return new ResponseEntity<Bus>(fetchedBus, HttpStatusCode.valueOf(status));
    }

    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> fetchedBusesList = busService.getAllBuses();
        int status = 200;

        if(fetchedBusesList == null)
            status = 400;

        return new ResponseEntity<List<Bus>>(fetchedBusesList, HttpStatusCode.valueOf(status));
    }

    @PutMapping("/{busId}")
    public ResponseEntity<Bus> updateBus(@PathVariable int busId, @RequestBody Bus busDetails) {
        Bus updatedBus = busService.updateBus(busId, busDetails);
        int status = 200;

        if(updatedBus == null)
            status = 404;

        return new ResponseEntity<Bus>(updatedBus, HttpStatusCode.valueOf(status));
    }

    @DeleteMapping("/{busId}")
    public ResponseEntity<Bus> deleteBus(@PathVariable int busId) {
        Bus deletedBusDetails = busService.deleteBus(busId);
        int status = 200;

        if(deletedBusDetails == null)
            status = 404;

        return new ResponseEntity<Bus>(deletedBusDetails, HttpStatusCode.valueOf(status));
    }

}
