package com.itg.AutomobilePartApp.Responses.AutoPart;

import lombok.Data;

import java.util.UUID;

@Data
public class AutoPartResponse {
    private String code;
    private String name;
    private String brand;
    private String description;
    private String category;
    private int stock;
    private double price;
}
