package com.example.hotel_management_sys.controllers;

import com.example.hotel_management_sys.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    public int getActiveUsers()
    {
        return dashboardService.getActiveUsers();
    }
}
