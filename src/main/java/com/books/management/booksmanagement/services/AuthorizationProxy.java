package com.books.management.booksmanagement.services;

import com.books.management.booksmanagement.entities.AuthorizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication-management")
public interface AuthorizationProxy {

    @GetMapping("/user/authorization")
    AuthorizationResponse authorizeUser(@RequestHeader("Authorization") String bearerToken);
}
