package com.itg.AutomobilePartApp.Controllers.Util;

import com.itg.AutomobilePartApp.DTO.AutoPart.PurchaseInfoDTO;
import com.itg.AutomobilePartApp.Services.Util.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Shop")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService _ordersService) {
        this.ordersService = _ordersService;
    }

    @PostMapping("/shop")
    public boolean purchaseProduct(@RequestBody PurchaseInfoDTO purchaseInfo){
        return ordersService.purchaseProduct(purchaseInfo);
    }
}
