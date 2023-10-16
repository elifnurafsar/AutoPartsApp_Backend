package com.itg.AutomobilePartApp.Entities.AutoParts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;

@Service
public class FakeFactory {

    @Autowired
    public ExhaustSystemPart exhaustSystemPart = new ExhaustSystemPart();

    @Autowired
    public FuelInjectionSystem fuelInjectionSystem = new FuelInjectionSystem();

    @Autowired
    public BrakeSystemPart brakeSystemPart = new BrakeSystemPart();

    @Autowired
    public Other other = new Other();

    private EnumMap<Category, AutoPart> categoryAutoPartEnumMap;

    public FakeFactory() {
        this.categoryAutoPartEnumMap = new EnumMap<Category, AutoPart>(Category.class);
        this.categoryAutoPartEnumMap.put(Category.brake_system, brakeSystemPart);
        this.categoryAutoPartEnumMap.put(Category.fuel_and_injection_system, fuelInjectionSystem);
        this.categoryAutoPartEnumMap.put(Category.exhaust_system, exhaustSystemPart);
        this.categoryAutoPartEnumMap.put(Category.other, other);
    }

    public AutoPart getAutoPart(AutoPart autoPart) {

        AutoPart obj = this.categoryAutoPartEnumMap.get(autoPart.getCategory());
        if(obj == null){
            obj = this.categoryAutoPartEnumMap.get(Category.other);
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
        return autoPart;
    }
}
