package br.com.netprecision.configapi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestCtrl {
    @GetMapping("/all")
    public String allAccess() {
        return "Public content";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('ROLE_CONFIG')")
    public String configAccess() {
        return "Configuration Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
