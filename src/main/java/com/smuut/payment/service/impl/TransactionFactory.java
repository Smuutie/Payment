package com.smuut.payment.service.impl;

import com.smuut.payment.dto.*;
import com.smuut.payment.entity.*;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionFactory {

  private final AuthorizeTransactionService authorizeTransactionService;

  private final ChargeTransactionService chargeTransactionService;

  private final RefundTransactionService refundTransactionService;

  private final ReversalTransactionService reversalTransactionService;

  private final ModelMapper modelMapper;

  Optional<TransactionGetDTO> createTransaction(TransactionCreateDTO transactionCreateDTO) {

    return switch (transactionCreateDTO.getType()) {
      case AUTHORIZE -> authorizeTransactionService
          .createTransactionInternal(transactionCreateDTO)
          .map(at -> modelMapper.map(at, AuthorizeTransactionGetDTO.class));
      case CHARGE -> chargeTransactionService
          .createTransactionInternal(transactionCreateDTO)
          .map(ct -> modelMapper.map(ct, ChargeTransactionGetDTO.class));
      case REFUND -> refundTransactionService
          .createTransactionInternal(transactionCreateDTO)
          .map(rt -> modelMapper.map(rt, RefundTransactionGetDTO.class));
      case REVERSAL -> reversalTransactionService
          .createTransactionInternal(transactionCreateDTO)
          .map(rt -> modelMapper.map(rt, ReversalTransactionGetDTO.class));
    };
  }
}
