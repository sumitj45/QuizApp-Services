package com.sumit.quizapp.service;


import com.sumit.quizapp.model.Users;
import com.sumit.quizapp.respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
   private  JWTService jwtService;

    @Autowired
   private  UserRepo repo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(Users user) {
        System.out.println("i am inside verify");
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return  jwtService.generateToken(user.getUsername());
            }
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return "Login Fail..";
        }

        return "Login Fail..";
    }



}
