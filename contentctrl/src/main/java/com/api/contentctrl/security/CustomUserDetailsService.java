package com.api.contentctrl.security;   

import com.api.contentctrl.modal.User;
import com.api.contentctrl.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
  
     //@Override
     //public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     //     return userRepository.findByUsername(username)
     //               .orElseThrow(() -> new UsernameNotFoundException("User not found"));
     //}
   
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        // Returns the user in the format Spring Security expects
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")  // Define user role
                .build();
    }
    
    
    
    
    
}

