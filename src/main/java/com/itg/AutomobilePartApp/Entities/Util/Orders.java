package com.itg.AutomobilePartApp.Entities.Util;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "public")
@Data
@NoArgsConstructor
public class Orders {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "address", columnDefinition = "text",  nullable = false)
    private String address;

    @Column(name = "count", columnDefinition = "integer",  nullable = false)
    private int count;

    @Column(name = "created_at")
    private Timestamp created_at;

}

