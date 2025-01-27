package com.example.demo.service;

import com.example.demo.bindings.PasswordReset;
import com.example.demo.bindings.UserRequest;
import com.example.demo.entity.Authorities;
import com.example.demo.entity.Users;
import com.example.demo.repo.AuthorityRepo;
import com.example.demo.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private AuthorityRepo authorityRepo;

    public Users registerUser(UserRequest request){
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.getEnabled());
        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhno(request.getPhno());

        usersRepo.save(user);

        // In your CommandLineRunner or a service
        Authorities userAuthority = new Authorities();
        userAuthority.setUser(user);  // Associate user1 with this authority
        userAuthority.setAuthority("ROLE_USER");
        authorityRepo.save(userAuthority);

        return user;
    }

    public Users registerAdmin(UserRequest request){
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.getEnabled());
        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhno(request.getPhno());

        usersRepo.save(user);

        // In your CommandLineRunner or a service
        Authorities userAuthority = new Authorities();
        userAuthority.setUser(user);  // Associate user1 with this authority
        userAuthority.setAuthority("ROLE_ADMIN");
        authorityRepo.save(userAuthority);
        return user;
    }

    public Users passwordReset(PasswordReset passwordReset){
        Optional<Users> usersOptional = usersRepo.findByUsername(passwordReset.getUsername());
        if(usersOptional.isPresent()){
            Users user = usersOptional.get();
            user.setPassword(passwordEncoder.encode(passwordReset.getPassword()));
            usersRepo.save(user);
            return user;
        } else {
            throw new RuntimeException("User is not present");
        }
    }

}
