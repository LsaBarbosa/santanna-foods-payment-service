package com.santanna.payment.service;

import com.santanna.payment.dto.RequestPaymentDTO;
import com.santanna.payment.dto.ResponsePaymentDTO;
import com.santanna.payment.enums.Status;
import com.santanna.payment.http_client.OrderClient;
import com.santanna.payment.model.Payment;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.santanna.payment.repository.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private OrderClient order;
    @Autowired
    private ModelMapper modelMapper;
    public Page<RequestPaymentDTO> getAllpayments(Pageable page) {
        var listPayments = repository.findAll(page).map(p -> modelMapper.map(p, RequestPaymentDTO.class));
        return listPayments;
    }
    public RequestPaymentDTO getPaymentById(Long id) {
        var paymentById = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        Payment payment = paymentById;
        var entityToDto = modelMapper.map(payment, RequestPaymentDTO.class);
        return entityToDto;
    }
    public ResponsePaymentDTO createPaymentData(RequestPaymentDTO dto) {
        var dtoToEntity = modelMapper.map(dto, Payment.class);
        Payment payment = dtoToEntity;
        payment.setStatus(Status.CREATED);
        repository.save(payment);
        var entityToDto = modelMapper.map(payment, ResponsePaymentDTO.class);
        return entityToDto;
    }
    public ResponsePaymentDTO updatePaymentData(Long id, RequestPaymentDTO dto) {
        var dtoToEntity = modelMapper.map(dto, Payment.class);
        Payment payment = dtoToEntity;
        payment.setId(id);
        payment = repository.save(payment);
        var entityToDto = modelMapper.map(payment, ResponsePaymentDTO.class);
        return entityToDto;
    }
    public void deletePayment(Long id) {
        repository.deleteById(id);
    }

    public void confirmPayment(Long id){
        Optional<Payment> payment = repository.findById(id);
        payment.get().setStatus(Status.CONFIRMED);
        repository.save(payment.get());
        order.updatePayment(payment.get().getOrderId());
    }
    public  void changeStatus(Long id) {
        Optional<Payment> payment = repository.findById(id);
        if (!payment.isPresent()) {
            throw new EntityNotFoundException();
        }
        payment.get().setStatus(Status.CONFIRMED_WITHOUT_INTEGRATION);
        repository.save(payment.get());
    }
}