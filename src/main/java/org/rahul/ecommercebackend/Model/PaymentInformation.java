package org.rahul.ecommercebackend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;


public class PaymentInformation {

    @Column(name = "cardholder_name")
    private String cardholderName;

    @Column(name = "card_number")
    private  String cardName;

    @Column(name = "expiration_date")
    private LocalDateTime expirtionDate;

    @Column(name = "cvv")
    private  String cvv;

}
