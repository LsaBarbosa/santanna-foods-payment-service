package com.santanna.payment.service;

import com.santanna.payment.dto.RequestPaymentDTO;
import com.santanna.payment.dto.ResponsePaymentDTO;
import com.santanna.payment.enums.Status;
import com.santanna.payment.model.Payment;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.santanna.payment.repository.PaymentRepository;

@Service
public class PaymentService {
}