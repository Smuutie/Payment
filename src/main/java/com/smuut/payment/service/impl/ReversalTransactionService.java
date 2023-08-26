package com.smuut.payment.service.impl;

import com.smuut.payment.dto.ReversalTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.ReversalTransaction;
import com.smuut.payment.service.TransactionService;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReversalTransactionService implements TransactionService<ReversalTransactionGetDTO> {
  @Override
  public Optional<ReversalTransactionGetDTO> createTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  Optional<ReversalTransaction> createTransactionInternal(
      TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<ReversalTransactionGetDTO> getTransactions(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<ReversalTransactionGetDTO> getTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }
}
