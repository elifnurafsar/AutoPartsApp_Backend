package com.itg.AutomobilePartApp.DTO.AutoPart;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutoPartDTO {
    private String code;
    private String name;
    private String brand;
    private String description;
    private String category;
    private int stock;
    private double price;
}