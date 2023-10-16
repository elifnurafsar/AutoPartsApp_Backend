package com.itg.AutomobilePartApp.Entities.AutoParts;

public class FuelInjectionSystem extends AutoPart implements AutoPartI {

    private int discount = 0;

    public FuelInjectionSystem(){}

    public FuelInjectionSystem(String code, String name, String brand, int stock, String description, double price) {
        super(code, name, brand, "fuel and injection system", stock, description, price);
    }

    @Override
    public int getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(int _discount) {
        this.discount = _discount;
    }
}