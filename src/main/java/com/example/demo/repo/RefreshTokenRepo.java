package com.example.demo.repo;

import com.example.demo.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);

}
