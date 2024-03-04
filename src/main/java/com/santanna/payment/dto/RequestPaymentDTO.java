package com.santanna.payment.dto;


import com.santanna.payment.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPaymentDTO {
       private Long id;
        private double price;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private Status status;
    private Long orderId;
    private Long methodPaymentId;

}
