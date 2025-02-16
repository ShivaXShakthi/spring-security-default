package com.example.demo.repo;

import com.example.demo.entity.DefaultImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultImageRepository extends JpaRepository<DefaultImage, Long> {
}

