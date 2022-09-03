package app.services;

import app.models.UserDetails;
import app.repositories.UserDetailsRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> userOptional = userDetailsRepository.findByUsername(username);
        UserDetails userData = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));


        if (userData.isAdmin()) {
            return new org.springframework.security.core.userdetails.User(
                    userData.getUsername(),
                    userData.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority("ROLE_USER"),
                            new SimpleGrantedAuthority("ROLE_ADMIN")
                    )
            );
        } else {
            return new org.springframework.security.core.userdetails.User(
                    userData.getUsername(),
                    userData.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority("ROLE_USER")
                    )
            );
        }
    }

    @Transactional
    public UserDetails saveUser(UserDetails userDetails){
        userDetails.setPassword(encoder.encode(userDetails.getPassword()));
        return userDetailsRepository.save(userDetails);
    }
}
