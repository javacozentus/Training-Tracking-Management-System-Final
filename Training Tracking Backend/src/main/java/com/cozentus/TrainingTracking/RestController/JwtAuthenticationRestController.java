package com.cozentus.TrainingTracking.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.TrainingTracking.Model.JwtRequest;
import com.cozentus.TrainingTracking.Model.JwtResponse;
import com.cozentus.TrainingTracking.Service.JwtService;
import com.cozentus.TrainingTracking.Service.TeacherService;

@RestController

@CrossOrigin(origins = "*",allowedHeaders ="*") 
public class JwtAuthenticationRestController {
    
	@Autowired
    private JwtService jwtService;
 
    @Autowired
    private TeacherService teacherService;
 
    @Autowired
    private AuthenticationManager authenticationManager;
 
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String welcomeAdmin() {
        return "Hello ADMIN";
    }
    
    @GetMapping("/roleTeacher")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public String welcomeTeacher() {
        return "Hello TEACHER!";
    }
 
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody JwtRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            String role = teacherService.getRole(authRequest.getUsername());
            return ResponseEntity.ok(new JwtResponse(token, role));
 
        } else {
            throw new UsernameNotFoundException("Invalid User Request !");
        }
    }
}
