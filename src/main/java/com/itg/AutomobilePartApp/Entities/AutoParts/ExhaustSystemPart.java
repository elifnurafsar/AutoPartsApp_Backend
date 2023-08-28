package com.itg.AutomobilePartApp.Entities.AutoParts;

public class ExhaustSystemPart extends AutoPart {
    public ExhaustSystemPart(String code, String name, String brand, int stock, String description, double price) {
        super(code, name, brand, "exhaust system", stock, description, price);
    }
}
