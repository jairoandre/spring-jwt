package br.com.netprecision.configapi.service;

import br.com.netprecision.configapi.constant.ERole;
import br.com.netprecision.configapi.model.Role;
import br.com.netprecision.configapi.model.User;
import br.com.netprecision.configapi.payload.response.JwtResponse;
import br.com.netprecision.configapi.payload.request.SignupRequest;
import br.com.netprecision.configapi.payload.response.MessageResponse;
import br.com.netprecision.configapi.repository.RoleRepository;
import br.com.netprecision.configapi.repository.UserRepository;
import br.com.netprecision.configapi.security.jwt.JwtUtils;
import br.com.netprecision.configapi.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public JwtResponse authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return JwtResponse.builder()
                .jwt(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByName(signupRequest.getUsername()))
            return badRequest("Error: username already taken!");
        if (userRepository.existsByEmail(signupRequest.getEmail()))
            return badRequest("Error: email is already in use!");
        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElse(null);
        if (userRole == null)
            return badRequest("Error: there is no role to set!");
        User user = User.builder()
                .name(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .roles(Collections.singleton(userRole))
                .build();
        userRepository.save(user);
        return ResponseEntity.ok()
                .body(MessageResponse.builder()
                .message("User created!")
                .build());

    }

    public ResponseEntity<?> badRequest(String message) {
        return ResponseEntity
                .badRequest()
                .body(MessageResponse.builder()
                .message(message)
                .build());
    }



}
