package com.example.hotelmanagetg380.controllers;

import com.example.hotelmanagetg380.services.DashboardService;
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
