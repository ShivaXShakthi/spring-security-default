package com.example.demo.repo;

import com.example.demo.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authorities, Integer> {
}
