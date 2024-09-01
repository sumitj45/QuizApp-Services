package com.sumit.quizapp.service;


import com.sumit.quizapp.model.UserPrincipal;
import com.sumit.quizapp.model.Users;
import com.sumit.quizapp.respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user =repo.findByUsername(username);

        if(user==null){
            System.out.println("User Not Found !");
            throw  new UsernameNotFoundException("User not found!");
        }


        return new UserPrincipal(user);


    }
}
