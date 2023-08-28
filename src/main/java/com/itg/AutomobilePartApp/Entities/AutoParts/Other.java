package com.itg.AutomobilePartApp.Entities.AutoParts;

public class Other extends AutoPart {
    public Other(String code, String name, String brand, int stock, String description, double price) {
        super(code, name, brand, "other", stock, description, price);
    }
}
