package org.rahul.ecommercebackend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class PaymentDetails {

    private  String paymentMethod;
    private String status;
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceId;
    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentId;

    public PaymentDetails() {
    }


}
