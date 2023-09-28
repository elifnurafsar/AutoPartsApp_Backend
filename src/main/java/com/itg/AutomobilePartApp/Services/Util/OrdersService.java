package com.itg.AutomobilePartApp.Services.Util;


import com.itg.AutomobilePartApp.DTO.AutoPart.PurchaseInfoDTO;
import com.itg.AutomobilePartApp.Entities.AutoParts.AutoPart;
import com.itg.AutomobilePartApp.Repositories.AutoPart.AutoPartRepository;
import com.itg.AutomobilePartApp.Repositories.Util.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersService {

    private final AutoPartRepository autoPartRepository;
    private final OrdersRepository orderRepository;

    @Autowired
    public OrdersService(AutoPartRepository _autoPartRepository, OrdersRepository _orderRepository) {
        this.autoPartRepository = _autoPartRepository;
        this.orderRepository = _orderRepository;
    }

    public boolean purchaseProduct(PurchaseInfoDTO purchaseInfo) {
        Optional<AutoPart> ap = autoPartRepository.findProductByCode(purchaseInfo.getCode());
        if(ap.isPresent() && ap.get().getStock() >= purchaseInfo.getCount()){
            int res = orderRepository.purchaseProduct(purchaseInfo);
            if(res>0)
                return true;
            else return false;
        }
        return false;
    }
}
