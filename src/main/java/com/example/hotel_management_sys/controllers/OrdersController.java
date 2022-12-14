package com.example.hotel_management_sys.controllers;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hotel_management_sys.entities.Orders;
import com.example.hotel_management_sys.helpers.InvoicePDFExporter;
import com.example.hotel_management_sys.services.OrderLogService;
import com.example.hotel_management_sys.services.OrdersServices;
import com.example.hotel_management_sys.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class OrdersController {
    @Autowired
    private OrdersServices ordersServices;
    @Autowired
    private UserService userService;
    @Autowired
    private InvoicePDFExporter exporter;
    @Autowired
    private OrderLogService orderLogService;


    @PostMapping("/orderBakery")
    public String saveBakeryOrders(@RequestParam("acc_id") Integer user_id,@RequestParam("inventory_id") Integer inventory_id,@RequestParam("unit_price") Float unit_price,@RequestParam("qty") Integer qty)
    {
        if(user_id == 0)
            return "redirect:/menu?order_login_error";
        else
        {
            boolean res = ordersServices.placeOrder(inventory_id,qty,unit_price,user_id);
            if(res)
            {
                return "redirect:/menu?order_done";
            }
            else
                return "redirect:/menu?order_error";
        }
    }

    @PostMapping("/orderBeverage")
    public String saveBeverageOrders(@RequestParam("acc_id") Integer user_id,@RequestParam("inventory_id") Integer inventory_id,@RequestParam("unit_price") Float unit_price,@RequestParam("qty") Integer qty)
    {
        if(user_id == 0)
            return "redirect:/menu?order_login_error";
        else
        {
            boolean res = ordersServices.placeOrder(inventory_id,qty,unit_price,user_id);
            if(res)
            {
                return "redirect:/menu?order_done";
            }
            else
                return "redirect:/menu?order_error";
        }
    }

    @PostMapping("/orderChilled")
    public String saveChilledOrders(@RequestParam("acc_id") Integer user_id,@RequestParam("inventory_id") Integer inventory_id,@RequestParam("unit_price") Float unit_price,@RequestParam("qty") Integer qty)
    {
        if(user_id == 0)
            return "redirect:/menu?order_login_error";
        else
        {
            boolean res = ordersServices.placeOrder(inventory_id,qty,unit_price,user_id);
            if(res)
            {
                return "redirect:/menu?order_done";
            }
            else
                return "redirect:/menu?order_error";
        }
    }

    @PostMapping("/orderGrocery")
    public String saveGroceryOrders(@RequestParam("acc_id") Integer user_id,@RequestParam("inventory_id") Integer inventory_id,@RequestParam("unit_price") Float unit_price,@RequestParam("qty") Integer qty)
    {
        if(user_id == 0)
            return "redirect:/menu?order_login_error";
        else
        {
            boolean res = ordersServices.placeOrder(inventory_id,qty,unit_price,user_id);
            if(res)
            {
                return "redirect:/menu?order_done";
            }
            else
                return "redirect:/menu?order_error";
        }
    }

    @PostMapping("/orderMeals")
    public String saveMealsOrders(@RequestParam("acc_id") Integer user_id,@RequestParam("inventory_id") Integer inventory_id,@RequestParam("unit_price") Float unit_price,@RequestParam("qty") Integer qty)
    {
        if(user_id == 0)
            return "redirect:/menu?order_login_error";
        else
        {
            boolean res = ordersServices.placeOrder(inventory_id,qty,unit_price,user_id);
            if(res)
            {
                return "redirect:/menu?order_done";
            }
            else
                return "redirect:/menu?order_error";
        }
    }

    @PostMapping("/orderHouesehold")
    public String saveHouseholdOrders(@RequestParam("acc_id") Integer user_id,@RequestParam("inventory_id") Integer inventory_id,@RequestParam("unit_price") Float unit_price,@RequestParam("qty") Integer qty)
    {
        if(user_id == 0)
            return "redirect:/menu?order_login_error";
        else
        {
            boolean res = ordersServices.placeOrder(inventory_id,qty,unit_price,user_id);
            if(res)
            {
                return "redirect:/menu?order_done";
            }
            else
                return "redirect:/menu?order_error";
        }
    }

    @PostMapping("/orderPharmacy")
    public String savePharmacyOrders(@RequestParam("acc_id") Integer user_id,@RequestParam("inventory_id") Integer inventory_id,@RequestParam("unit_price") Float unit_price,@RequestParam("qty") Integer qty)
    {
        if(user_id == 0)
            return "redirect:/menu?order_login_error";
        else
        {
            boolean res = ordersServices.placeOrder(inventory_id,qty,unit_price,user_id);
            if(res)
            {
                return "redirect:/menu?order_done";
            }
            else
                return "redirect:/menu?order_error";
        }
    }

    @RequestMapping("/canselOrders/{id}")
    public String cancelOrder(@PathVariable("id") Integer id)
    {
        boolean res = ordersServices.canselOrder(id);
        if(res)
            return "redirect:/orders?order_cansel_done";
        else
            return "redirect:/orders?order_cansel_error";
    }

    @RequestMapping("/payOrders/{id}")
    public String payOrder(@PathVariable("id") String id, HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        ArrayList<Orders> orders= (ArrayList<Orders>) ordersServices.getMyOrdersForCustomers(id);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Invoice_" + id + ".pdf";
        response.setHeader(headerKey, headerValue);

        boolean res = orderLogService.addOrderLog(orders);
        exporter.fillValues(orders,currentDateTime,id);
        return exporter.export(response);

    }
}
