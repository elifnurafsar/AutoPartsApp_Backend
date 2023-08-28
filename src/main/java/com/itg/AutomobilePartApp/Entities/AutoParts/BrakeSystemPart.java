package com.itg.AutomobilePartApp.Entities.AutoParts;

public class BrakeSystemPart extends AutoPart {
    public BrakeSystemPart(String code, String name, String brand, int stock, String description, double price) {
        super(code, name, brand, "brake system", stock, description, price);
    }
}