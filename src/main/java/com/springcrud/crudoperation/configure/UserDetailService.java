package com.springcrud.crudoperation.configure;

import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Configuration
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user=userRepository.findByEmail(username);
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
//    }
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
            .map(user -> new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    new ArrayList<>()) // Replace with authorities if needed
            )
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
}

}
