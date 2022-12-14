package com.example.hotel_management_sys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hotel_management_sys.entities.Login;
import com.example.hotel_management_sys.helpers.Encription;
import com.example.hotel_management_sys.services.LoginServices;
import com.example.hotel_management_sys.services.ProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private LoginServices loginServices;

    @PostMapping("/saveUpdateProfile")
    public String updateProfile(HttpSession session, HttpServletRequest request,@RequestParam("id_no") String reg, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("email") String email)
    {
        List<String> new_session;
        List<String> users = (List<String>) session.getAttribute("USER_SESSION");

        boolean res = profileService.updateProfile(reg,name,phone,email);
        if (res) {
            new_session = new ArrayList<>();
            new_session.add(users.get(0));
            new_session.add(reg);
            new_session.add(name);
            new_session.add(phone);
            new_session.add(email);
            new_session.add(users.get(5));
            request.getSession().setAttribute("USER_SESSION",new_session);
            return "redirect:/profile?profile_done";
        }
        else
            return "redirect:/profile?profile_error";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("user_id") Integer u_id, @RequestParam("user_t_id") String id,@RequestParam("old_pwd") String old_password,@RequestParam("new_pwd") String new_password,@RequestParam("confirm_pwd") String con_password)
    {
        List<Login> logins = loginServices.getLogins();
        int flag=0;
        String pwd = Encription.sha1(old_password);

        for(Login login : logins)
        {
            if(login.getPassword().equalsIgnoreCase(pwd) && login.getId()==u_id)
            {
                flag =1;
                break;
            }
            else
                flag =0;
        }

        if(flag ==1)
        {
            if(new_password.equalsIgnoreCase(con_password)) {
                boolean res = loginServices.updateUserPassword(id, old_password, new_password);
                if(res)
                    return "redirect:/profile?pwd_updated";
                else
                    return "redirect:/profile?pwd_not_updated";
            }
            else
                return "redirect:/profile?pwd_not_match";
        }
        else
            return "redirect:/profile?pwd_old_not_exists";
    }
}
