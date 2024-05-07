package com.mpp.mppbackend.Controller;

import com.mpp.mppbackend.Model.UserAccount;
import com.mpp.mppbackend.Repository.UserAccountRepository;
import com.mpp.mppbackend.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserAccountController {
    private final AuthenticationManager authenticationManager;

    public UserAccountController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public void register(@RequestBody UserAccount userAccount) {
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
        userAccountRepository.save(userAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAccount userAccount) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAccount.getUsername(), userAccount.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());

        // add jwt to response as 'token' field in json
        return ResponseEntity.ok().body("{\"token\":\"" + jwt + "\"}");
    }
}