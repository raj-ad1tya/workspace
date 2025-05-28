package com.examly.springappbus.service;

import com.examly.springappbus.model.Bus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusService {

    Bus addBus(Bus bus);
    Bus getBusById(int id);
    List<Bus> getAllBuses();

    // admin only
    Bus updateBus(int id, Bus busDetails);
    Bus deleteBus(int id);

}
