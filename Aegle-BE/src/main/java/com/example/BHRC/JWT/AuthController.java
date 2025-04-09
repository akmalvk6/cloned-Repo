package com.example.BHRC.JWT;

import com.example.BHRC.dto.AuthRequestDTO;
import com.example.BHRC.dto.GenericResponse;
import com.example.BHRC.model.User;
import com.example.BHRC.model.Roles;
import com.example.BHRC.security.CustomUserDetails;
import com.example.BHRC.service.RoleService;
import com.example.BHRC.service.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          CustomUserDetailsService userService,
                          RoleService roleService,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
            String token = jwtUtil.generateToken(userDetails.getUsername());

            GenericResponse response = new GenericResponse(
                    true,
                    "Login successful",
                    token,
                    null
            );

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            GenericResponse response = new GenericResponse(
                    false,
                    "Login failed",
                    null,
                    "Invalid credentials"
            );

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }



    // Sign-Up API
    @PostMapping("/signup")
    public ResponseEntity<GenericResponse> signUp(@RequestBody AuthRequestDTO userSignupRequest) {
        // Check if username already exists
        if (userService.isUserExists(userSignupRequest.getUsername())) {
            GenericResponse response = new GenericResponse(
                    false,
                    "Signup failed",
                    null,
                    "Username already exists"
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // Encrypt the password
        User user = new User();
        user.setUsername(userSignupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userSignupRequest.getPassword()));

        // Assign role based on roleId from the request
        Roles role = roleService.getRoleById(userSignupRequest.getRoleId());
        if (role == null) {
            GenericResponse response = new GenericResponse(
                    false,
                    "Signup failed",
                    null,
                    "Invalid role ID"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Set<Roles> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        // Save the new user
        userService.saveUser(user);

        GenericResponse response = new GenericResponse(
                true,
                "User registered successfully!",
                user.getUsername(),
                null
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
