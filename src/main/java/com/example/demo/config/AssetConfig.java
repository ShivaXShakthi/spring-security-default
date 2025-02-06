package com.example.demo.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AssetConfig {

    @Bean
    public Cloudinary cloudinary(){
        Map map = new HashMap<String,String>();
        map.put("cloud_name","dqa6tvk8p");
        map.put("api_key","958867442577535");
        map.put("api_Secret","kvZfnnr6gmzI_2D1PB782vN2YF4");

        //map.put("secure",true);
        return new Cloudinary(map);
    }

}
