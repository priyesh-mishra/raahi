package com.priyesh.raahi.controller;

import com.priyesh.raahi.model.AuthenticationRequest;
import com.priyesh.raahi.model.AuthenticationResponse;
import com.priyesh.raahi.service.MyUserDetailsService;
import com.priyesh.raahi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationResource {

    @Autowired
    AuthenticationResponse response;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping(path = "/authenticate")
    ResponseEntity<AuthenticationResponse> createJwtToken(@RequestBody AuthenticationRequest request) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
