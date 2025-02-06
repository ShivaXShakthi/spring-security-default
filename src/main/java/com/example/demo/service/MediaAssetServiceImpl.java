package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
//import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

@Service
public class MediaAssetServiceImpl implements MediaAssetService{
//    @Autowired
//    private Cloudinary cloudinary;

    @Value("${CLOUDINARY_URL}")
    private String CLOUDINARY_URL;


    @Override
    public Map uploadImage(MultipartFile file) {
      //   Set your Cloudinary credentials

        //Dotenv dotenv = Dotenv.load();
        //Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        System.out.println(cloudinary.config.cloudName);

// Upload the image
        Map params1 = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        try {
            Map upload = cloudinary.uploader().upload(file.getBytes(), params1);
            System.out.println("Cloudinary Response: " + upload);
            return upload;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
