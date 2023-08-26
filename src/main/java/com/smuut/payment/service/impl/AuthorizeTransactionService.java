package com.smuut.payment.service.impl;

import com.smuut.payment.dto.AuthorizeTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.repository.AuthorizeTransactionRepository;
import com.smuut.payment.service.TransactionService;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizeTransactionService implements TransactionService<AuthorizeTransactionGetDTO> {

  private final AuthorizeTransactionRepository authorizeTransactionRepository;

  @Override
  public Optional<AuthorizeTransactionGetDTO> createTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<AuthorizeTransactionGetDTO> getTransactions(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<AuthorizeTransactionGetDTO> getTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    throw new UnsupportedOperationException();
  }
}
