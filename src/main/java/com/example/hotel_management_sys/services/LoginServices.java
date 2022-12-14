package com.example.hotel_management_sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel_management_sys.entities.Login;
import com.example.hotel_management_sys.entities.User;
import com.example.hotel_management_sys.repositories.LoginRepository;
import com.example.hotel_management_sys.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServices {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;

    public User isUserValid(String user_name) {
        return userRepository.findByRegId(user_name);
    }

    public ArrayList<Login> isPasswordValid(String password)
    {

        return loginRepository.findByPwd(password);
    }

    public boolean updateUserPassword(String id, String old_password, String new_password) {
        loginRepository.userPasswordUpdate(id,old_password,new_password);

            return true;
    }

    public List<Login> getLogins()
    {
        return (List<Login>) loginRepository.findAll();
    }
}
