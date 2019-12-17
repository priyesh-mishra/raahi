package com.priyesh.raahi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileResource {

    @GetMapping(path = "/profile/getProfile")
    public ResponseEntity<String> getProfiles(){
        return ResponseEntity.ok().body("Sample profile");
    }
}
