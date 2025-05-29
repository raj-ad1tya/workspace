package com.examly.springappbus.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway")
public interface UserServiceClient {

    @GetMapping("/api/user/{userId}/exists")
    ResponseEntity<Boolean> userExistsById(@PathVariable Integer userId);

}
