package com.itg.AutomobilePartApp.Entities.AutoParts;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
public class FakeFactory {

    private HashMap<String, AutoPart> categoryAutoPartMap;

    public FakeFactory(BrakeSystemPart brakeSystemPart, FuelInjectionSystem fuelInjectionSystem, ExhaustSystemPart exhaustSystemPart, Other other) {
        this.categoryAutoPartMap = new HashMap<>();
        this.categoryAutoPartMap.put(Category.brake_system.name(), brakeSystemPart);
        this.categoryAutoPartMap.put(Category.fuel_and_injection_system.name(), fuelInjectionSystem);
        this.categoryAutoPartMap.put(Category.exhaust_system.name(), exhaustSystemPart);
        this.categoryAutoPartMap.put(Category.other.name(), other);
    }

    public AutoPart getAutoPart(AutoPart autoPart) {

        AutoPart obj = this.categoryAutoPartMap.get(autoPart.getCategory());
        if(obj == null){
            obj = this.categoryAutoPartMap.get(Category.other.name());
        }
        obj.setCode(autoPart.getCode());
        obj.setDescription(autoPart.getDescription());
        obj.setBrand(autoPart.getBrand());
        obj.setName(autoPart.getName());
        obj.setStock(autoPart.getStock());
        double price = autoPart.getPrice();
        if(obj.getDiscount() > 0)
            price = autoPart.getPrice() - (autoPart.getPrice()*obj.getDiscount()*1.0/100);
        obj.setPrice(price);
        return obj;
    }
}
