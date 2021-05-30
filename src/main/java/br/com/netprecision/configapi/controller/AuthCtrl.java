package br.com.netprecision.configapi.controller;

import br.com.netprecision.configapi.payload.request.LoginRequest;
import br.com.netprecision.configapi.payload.request.SignupRequest;
import br.com.netprecision.configapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthCtrl {

    private AuthService service;

    @Autowired
    public AuthCtrl(AuthService service) {
        this.service = service;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(service.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        return service.registerUser(signupRequest);
    }


}
