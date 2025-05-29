package com.examly.springappfeedback.client;

import com.examly.springappfeedback.model.Bus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway")
public interface BusServiceClient {

    @GetMapping("/api/bus/{busId}")
    ResponseEntity<Bus> getBusById(@PathVariable Integer busId);

}
