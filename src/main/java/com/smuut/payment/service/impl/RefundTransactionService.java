package com.smuut.payment.service.impl;

import com.smuut.payment.dto.RefundTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.RefundTransaction;
import com.smuut.payment.service.TransactionService;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundTransactionService implements TransactionService<RefundTransactionGetDTO> {
  @Override
  public Optional<RefundTransactionGetDTO> createTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  Optional<RefundTransaction> createTransactionInternal(TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<RefundTransactionGetDTO> getTransactions(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<RefundTransactionGetDTO> getTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }
}
