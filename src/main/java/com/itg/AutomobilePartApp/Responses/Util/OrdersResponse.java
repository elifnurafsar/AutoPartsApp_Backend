package com.itg.AutomobilePartApp.Responses.Util;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrdersResponse {
    private String code;
    private String username;
    private String address;
    private Timestamp created_at;
    private int count;
}
