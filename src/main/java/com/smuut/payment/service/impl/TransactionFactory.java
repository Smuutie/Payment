package com.smuut.payment.service.impl;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.*;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionFactory {

  private final AuthorizeTransactionService authorizeTransactionService;

  private final ChargeTransactionService chargeTransactionService;

  private final RefundTransactionService refundTransactionService;

  private final ReversalTransactionService reversalTransactionService;

  /** That looks weird I know. Boxing does not work in generics. */
  Optional<BaseTransaction> createTransaction(TransactionCreateDTO transactionCreateDTO) {

    return switch (transactionCreateDTO.getType()) {
      case AUTHORIZE -> authorizeTransactionService
          .createTransactionInternal(transactionCreateDTO)
          .map(at -> at);
      case CHARGE -> chargeTransactionService
          .createTransactionInternal(transactionCreateDTO)
          .map(at -> at);
      case REFUND -> refundTransactionService
          .createTransactionInternal(transactionCreateDTO)
          .map(at -> at);
      case REVERSAL -> reversalTransactionService
          .createTransactionInternal(transactionCreateDTO)
          .map(at -> at);
    };
  }
}
