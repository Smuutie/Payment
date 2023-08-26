package com.smuut.payment.service.impl;

import com.smuut.payment.dto.ChargeTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.ChargeTransaction;
import com.smuut.payment.service.TransactionService;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargeTransactionService implements TransactionService<ChargeTransactionGetDTO> {
  @Override
  public Optional<ChargeTransactionGetDTO> createTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  Optional<ChargeTransaction> createTransactionInternal(TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<ChargeTransactionGetDTO> getTransactions(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<ChargeTransactionGetDTO> getTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }
}
