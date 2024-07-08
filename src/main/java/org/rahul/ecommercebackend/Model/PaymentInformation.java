package org.rahul.ecommercebackend.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Embeddable
@Setter
@Getter
public class PaymentInformation {


    private String cardType;;

    private  String cardholderName;


    private String cardNumber; // Corrected field name

    @JsonFormat(pattern = "MM/yy")
    private YearMonth expiryDate; // Corrected field name and typo

    private String cvv;

}