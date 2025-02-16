package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageUploadServiceImpl {

    @Autowired
    private MediaAssetService assetService;

    public String uploadImage(MultipartFile file) throws Exception {


        Map map = assetService.uploadImage(file);
        return (String) map.get("secure_url");


    }
}

