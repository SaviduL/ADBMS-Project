package com.example.hotel_management_sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_management_sys.repositories.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public boolean updateProfile(String reg, String name, String phone, String email) {
        userRepository.userProfileUpdate(reg,name,phone,email);
        return true;
    }
}
