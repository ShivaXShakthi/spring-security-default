package com.example.demo.controller;

import com.example.demo.bindings.ApiRequest;
import com.example.demo.bindings.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test");
    }


    @PostMapping("/test")
    public ResponseEntity<ApiResponse> postTest(@RequestBody ApiRequest request){
        ApiResponse response = new ApiResponse();
        BeanUtils.copyProperties(request,response);
        return ResponseEntity.ok(response);
    }


}
