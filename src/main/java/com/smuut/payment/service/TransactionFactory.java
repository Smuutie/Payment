package com.smuut.payment.service;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.*;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class TransactionFactory {

  Optional<BaseTransaction> createTransaction(TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  private Optional<AuthorizeTransaction> createAuthorizeTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  private Optional<ChargeTransaction> createChargeTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  private Optional<RefundTransaction> createRefundTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  private Optional<ReversalTransaction> createReversalTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }
}
