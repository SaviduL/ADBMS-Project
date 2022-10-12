package com.example.hotelmanagetg380.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Home {

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public  String getIndex(Model model, HttpSession session)
    {
        List<String> users = (List<String>) session.getAttribute("USER_SESSION");
        if (users == null)
        {
            model.addAttribute("account","");
            return "tmp01/index";
        }
        model.addAttribute("account",users.get(2));
        return "tmp01/index";
    }



}
