package com.itg.AutomobilePartApp.DTO.AutoPart;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseInfoDTO {
    private String code;
    private String username;
    private String address;
    private int count;
}
