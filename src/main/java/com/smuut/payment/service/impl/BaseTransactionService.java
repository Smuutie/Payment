package com.smuut.payment.service.impl;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.dto.TransactionGetDTO;
import com.smuut.payment.service.TransactionService;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseTransactionService implements TransactionService<TransactionGetDTO> {

  private final TransactionFactory transactionFactory;

  @Override
  public Optional<TransactionGetDTO> createTransaction(TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<TransactionGetDTO> getTransactions(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<TransactionGetDTO> getTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }
}
