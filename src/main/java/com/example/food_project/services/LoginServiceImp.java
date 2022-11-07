package com.example.food_project.services;

import com.example.food_project.entity.UserEntity;
import com.example.food_project.repository.UserRepository;
import com.example.food_project.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean checkLogin(String email, String password) {
        List<UserEntity> listUsers = userRepository.findByEmailAndPassword(email, password);
        return listUsers.size() > 0;
    }
}
