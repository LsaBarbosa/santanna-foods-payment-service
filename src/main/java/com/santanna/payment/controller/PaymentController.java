package com.santanna.payment.controller;

import com.santanna.payment.dto.RequestPaymentDTO;
import com.santanna.payment.dto.ResponsePaymentDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.santanna.payment.service.PaymentService;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;
    @GetMapping
    public ResponseEntity<Page<RequestPaymentDTO>> listAllPayment(@PageableDefault(size = 10) Pageable paginacao) {
         service.getAllpayments(paginacao);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<RequestPaymentDTO> paymentById(@PathVariable @NotNull Long id) {
        RequestPaymentDTO dto = service.getPaymentById(id);

        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<ResponsePaymentDTO> createPayment(@RequestBody @Valid RequestPaymentDTO dto, UriComponentsBuilder uriBuilder) {
        ResponsePaymentDTO payment = service.createPaymentData(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(endereco).body(payment);
    }

    @PutMapping
    public ResponseEntity<ResponsePaymentDTO> updatePayment(@PathVariable @NotNull Long id, @RequestBody @Valid RequestPaymentDTO dto) {
        ResponsePaymentDTO atualizado = service.updatePaymentData(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RequestPaymentDTO> deletePayment(@PathVariable @NotNull Long id) {
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

}

