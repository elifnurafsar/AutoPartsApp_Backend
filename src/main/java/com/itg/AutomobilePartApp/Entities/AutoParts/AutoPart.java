package com.itg.AutomobilePartApp.Entities.AutoParts;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "autopart", schema = "public")
@Data
@NoArgsConstructor
public class AutoPart implements AutoPartI{

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "code", columnDefinition = "text", nullable = false)
    private String code;
    @Column(name = "name", columnDefinition = "text", nullable = false)
    private String name;
    @Column(name = "brand", columnDefinition = "text",  nullable = false)
    private String brand;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "category", columnDefinition = "text", nullable = false)
    private String category;
    @Column(name = "stock", columnDefinition = "integer", nullable = false)
    private int stock;
    /*@Column(name = "created_at")
    private Timestamp created_at;*/
    @Column(name = "price", nullable = false)
    private double price;


    public AutoPart( String code, String name, String brand, String category, int stock, String description, double price){
        this.code = code;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.stock = stock;
        this.description = description;
        this.price = price;
    }

    @Override
    public int getDiscount() {
        return 0;
    }

    @Override
    public void setDiscount(int discount) {

    }
}
