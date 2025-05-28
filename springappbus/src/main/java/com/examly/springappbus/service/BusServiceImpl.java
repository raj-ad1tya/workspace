package com.examly.springappbus.service;

import com.examly.springappbus.model.Bus;
import com.examly.springappbus.repository.BusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepo busRepo;

    @Override
    public Bus addBus(Bus bus) {
        return busRepo.save(bus);
    }

    @Override
    public Bus getBusById(int id) {
        return busRepo.findById(id).orElse(null);
    }

    @Override
    public List<Bus> getAllBuses() {
        return busRepo.findAll();
    }

    @Override
    public Bus updateBus(int id, Bus busDetails) {
        Bus existingBusDetails = busRepo.findById(id).orElse(null);

        if(existingBusDetails == null)
            return null;

        existingBusDetails.setBusName(busDetails.getBusName());
        existingBusDetails.setBusType(busDetails.getBusType());
        existingBusDetails.setArrivalTime(busDetails.getArrivalTime());
        existingBusDetails.setDepartureTime(busDetails.getDepartureTime());
        existingBusDetails.setFare(busDetails.getFare());
        existingBusDetails.setSource(busDetails.getSource());
        existingBusDetails.setDestination(busDetails.getDestination());
        existingBusDetails.setTotalSeats(busDetails.getTotalSeats());
        existingBusDetails.setAvailableSeats(busDetails.getAvailableSeats());
        existingBusDetails.setPhoto(busDetails.getPhoto());

        return busRepo.save(existingBusDetails);
    }

    @Override
    public Bus deleteBus(int id) {
        Bus existingBusDetails = busRepo.findById(id).orElse(null);

        if(existingBusDetails != null)
            busRepo.delete(existingBusDetails);

        return existingBusDetails;
    }

}
