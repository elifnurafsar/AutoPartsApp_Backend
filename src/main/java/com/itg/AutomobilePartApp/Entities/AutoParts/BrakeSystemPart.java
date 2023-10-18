package com.itg.AutomobilePartApp.Entities.AutoParts;

public class BrakeSystemPart extends AutoPart implements AutoPartI {

    private int discount = 0;

    public BrakeSystemPart(){
        this.setCategory("brake system");
    }

    public BrakeSystemPart(String code, String name, String brand, int stock, String description, double price) {
        super(code, name, brand, "brake system", stock, description, price);
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