package com.example.demo.service;

import com.example.demo.entity.RefreshToken;
import com.example.demo.entity.Users;
import com.example.demo.repo.RefreshTokenRepo;
import com.example.demo.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl {

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    @Autowired
    private UsersRepo usersRepo;

    public RefreshToken createRefreshToken(String username){
        Users user = usersRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        RefreshToken refreshToken = new RefreshToken(UUID.randomUUID().toString(), Instant.now().plusMillis(600000), user);
        return refreshTokenRepo.save(refreshToken);
    }

    public RefreshToken findByToken(String token) throws Exception {
        return refreshTokenRepo.findByToken(token).orElseThrow(() -> new Exception("Details not found"));
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepo.delete(token);
            throw new RuntimeException("token expired. Please sign in...");
        }
        return token;
    }



}
