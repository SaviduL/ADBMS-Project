package com.example.hotel_management_sys.controllers;

import com.example.hotel_management_sys.entities.Login;
import com.example.hotel_management_sys.entities.User;
import com.example.hotel_management_sys.helpers.Encription;
import com.example.hotel_management_sys.services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    LoginServices loginServices;

    @PostMapping("/loginAccount")
    public String login(@RequestParam("username") String user_name, @RequestParam("pwd") String password, HttpServletRequest request, HttpSession session)
    {
        int i,flag=0;
        String pwd = Encription.sha1(password);
        User user = loginServices.isUserValid(user_name);
        ArrayList<Login> login = loginServices.isPasswordValid(pwd);

        //session
        List<String> users = (List<String>) request.getSession().getAttribute("USER_SESSION");

        if(Objects.nonNull(user) && Objects.nonNull(login)){
            for (i=0;i<login.size();i++)
            {
                if (user.getId() == login.get(i).getId()) {
                    flag =1;
                    break;
                }
                else
                    flag=0;
            }

            if(flag ==1)
            {
                if (users == null)
                {
                    users = new ArrayList<>();
                    request.getSession().setAttribute("USER_SESSION",users);
                }
                users.add(user.getId().toString());
                users.add(user.getRegistered_no());
                users.add(user.getName());
                users.add(user.getMobile());
                users.add(user.getEmail());
                users.add(user.getRole_id().toString());

                //set newly created session values to session array
                request.getSession().setAttribute("USER_SESSION",users);
                if(Integer.parseInt(users.get(5)) == 1 || Integer.parseInt(users.get(5)) == 2)
                    return "redirect:/dashboard";
                else if(Integer.parseInt(users.get(5)) == 3 || Integer.parseInt(users.get(5)) == 4 || Integer.parseInt(users.get(5)) == 5)
                    return "redirect:/menu";
                else
                    return "redirect:/";
            }
            else
                return "redirect:/login?login_error";
        }
        else
        {
            return "redirect:/login?login_error";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return "redirect:/";
    }
}

