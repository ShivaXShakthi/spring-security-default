package com.example.demo.controller;

import com.example.demo.bindings.*;
import com.example.demo.entity.RefreshToken;
import com.example.demo.entity.Users;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.service.RefreshTokenServiceImpl;
import com.example.demo.service.UsersServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    @Autowired
    private UsersServiceImpl usersService;

    //user - role
    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test");
    }


    //admin - role
    @PostMapping("/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> postTest(@RequestBody ApiRequest request){
        ApiResponse response = new ApiResponse();
        BeanUtils.copyProperties(request,response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody ApiRequest request){
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            //throw new Exception("erorr please try");
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String jwtToken = jwtUtils.generateTokenFromUsername(username);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(username);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        com.example.demo.bindings.RefreshToken rt = new com.example.demo.bindings.RefreshToken();
        BeanUtils.copyProperties(refreshToken,rt);
        LoginResponse response = new LoginResponse(jwtToken,roles, userDetails.getUsername(), rt);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) throws Exception {
        RefreshToken token = refreshTokenService.findByToken(request.getToken());
        RefreshToken refreshToken = refreshTokenService.verifyExpiration(token);
        Users userInfo = refreshToken.getUserInfo();
        String refreshedToken = jwtUtils.generateTokenFromUsername(userInfo.getUsername());
        RefreshTokenResponse response = new RefreshTokenResponse(refreshedToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registeruser")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest user){
        Users users = usersService.registerUser(user);
        return ResponseEntity.ok("User created succesfully");
    }

    @PostMapping("/registeradmin")
    public ResponseEntity<?> registerAdmin(@RequestBody UserRequest user){
        Users users = usersService.registerAdmin(user);
        return ResponseEntity.ok("admin created succesfully");
    }

    @PostMapping("/passwordreset")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordReset passwordReset){
        Users user = usersService.passwordReset(passwordReset);
        return ResponseEntity.ok(user);
    }

}
