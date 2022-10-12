package com.example.hotel_management_sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel_management_sys.repositories.UserRepository;

@Service
public class DashboardService {
    @Autowired
    private UserRepository userRepository;
    public int getActiveUsers() {
        return userRepository.getActiveUsers();
    }
}
