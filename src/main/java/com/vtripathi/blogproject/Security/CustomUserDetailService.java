package com.vtripathi.blogproject.Security;

import com.vtripathi.blogproject.Entity.User;
import com.vtripathi.blogproject.Exception.ResourceNotFoundException;
import com.vtripathi.blogproject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Loading user from database
        User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email"+ username, 0));
        return user;
    }
}
