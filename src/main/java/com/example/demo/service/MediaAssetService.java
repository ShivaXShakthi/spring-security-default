package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface MediaAssetService {

    public Map uploadImage(MultipartFile file);

}
