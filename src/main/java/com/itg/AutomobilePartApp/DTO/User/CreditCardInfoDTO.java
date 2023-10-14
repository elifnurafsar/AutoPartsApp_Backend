package com.itg.AutomobilePartApp.DTO.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCardInfoDTO {
    private String username;
    private String cardholder;
    private String cardnumber;
    private String cvv2;
    private String expirationdate;
}
