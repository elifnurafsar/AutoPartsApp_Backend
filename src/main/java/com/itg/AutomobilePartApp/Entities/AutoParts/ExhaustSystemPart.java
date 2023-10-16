package com.itg.AutomobilePartApp.Entities.AutoParts;

public class ExhaustSystemPart extends AutoPart implements AutoPartI {

    private int discount = 0;

    public ExhaustSystemPart(){

    }

    public ExhaustSystemPart(String code, String name, String brand, int stock, String description, double price) {
        super(code, name, brand, "exhaust system", stock, description, price);
    }

    @Override
    public int getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(int _discount) {
        this.discount = discount;
    }
}
