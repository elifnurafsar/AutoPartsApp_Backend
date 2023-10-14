package com.itg.AutomobilePartApp.Responses.Util;


import lombok.Data;

@Data
public class CreditCardInfoResponse {
    private String username;
    private String cardholder;
    private String cardnumber;
    private String cvv2;
    private String expirationdate;
}
