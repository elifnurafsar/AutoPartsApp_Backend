package com.itg.AutomobilePartApp.Entities.AutoParts;

import org.springframework.stereotype.Component;

@Component
public class FakeFactory {

    public AutoPart getAutoPart(AutoPart product) {
        if ("exhaust system".equalsIgnoreCase(product.getCategory())) {
            ExhaustSystemPart esp = new ExhaustSystemPart(product.getCode(), product.getName(), product.getBrand(), product.getStock(), product.getDescription(), product.getPrice());
            return esp;
        }
        else if ("fuel and injection system".equalsIgnoreCase(product.getCategory())) {
            FuelInjectionSystem fes = new FuelInjectionSystem(product.getCode(), product.getName(), product.getBrand(), product.getStock(), product.getDescription(), product.getPrice());
            return fes;
        }
        else if("brake system".equalsIgnoreCase(product.getCategory())) {
            BrakeSystemPart bs = new BrakeSystemPart(product.getCode(), product.getName(), product.getBrand(), product.getStock(), product.getDescription(), product.getPrice());
            return bs;
        }
        else{
            Other other = new Other(product.getCode(), product.getName(), product.getBrand(), product.getStock(), product.getDescription(), product.getPrice());
            return other;
        }
    }
}
