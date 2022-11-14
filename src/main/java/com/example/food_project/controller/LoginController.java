package com.example.food_project.controller;

import com.example.food_project.jwt.JwtTokenHelper;
import com.example.food_project.payload.request.SignInRequest;
import com.example.food_project.payload.response.DataResponse;
import com.example.food_project.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@CrossOrigin
@RequestMapping("/signin")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    LoginService loginService;
    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @GetMapping("/test")
    public String text(){
        return "Hello";
    }

    //docblocks
    /**
     * @param
     * @return
     */


    @PostMapping("")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request){
//        boolean isSuccess = loginService.checkLogin(request.getUsername(), request.getPassword());
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                request.getUsername(),request.getPassword());

        Authentication auth = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        //Generate token
        String token = jwtTokenHelper.generateToken(request.getUsername());
        String decodeToken = jwtTokenHelper.decodeToken(token);


        DataResponse dataResponse = new DataResponse();
        dataResponse.setData(HttpStatus.OK.value());
//        dataResponse.setSuccess(isSuccess);
        dataResponse.setDesc(decodeToken);
        dataResponse.setData(token);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }




}
