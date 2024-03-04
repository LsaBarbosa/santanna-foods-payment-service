package com.santanna.payment.model;

import com.santanna.payment.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_payments")
@Entity(name = "Payment")
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private double price;
    @NotBlank
    @Size(max=100)
    private String name;
    @NotBlank
    @Size(min=16, max=16)
    private String number;
    @NotBlank
    @Size(max=7)
    private String expiration;
    @NotBlank
    @Size(min=3, max=3)
    private String code;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull
    private Long orderId;
    @NotNull
    private Long methodPaymentId;

}
