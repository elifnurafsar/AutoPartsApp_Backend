package com.itg.AutomobilePartApp.Entities.Util;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "creditcardinfo", schema = "public")
@Data
@NoArgsConstructor
public class CreditCardInfo {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "cardnumber", unique = true, nullable = false)
    private String cardnumber;

    @Column(name = "cardholder", nullable = false)
    private String cardholder;

    @Column(name = "cvv2", nullable = false)
    private String cvv2;

    @Column(name = "expirationdate", nullable = false)
    private String expirationdate;

}
