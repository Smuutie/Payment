package com.smuut.payment.service.impl;

import com.smuut.payment.dto.AuthorizeTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import com.smuut.payment.repository.AuthorizeTransactionRepository;
import com.smuut.payment.service.TransactionService;
import jakarta.validation.Validator;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizeTransactionService implements TransactionService<AuthorizeTransactionGetDTO> {

  private final AuthorizeTransactionRepository authorizeTransactionRepository;

  private final Validator validator;

  private final ModelMapper modelMapper;

  @Override
  public Optional<AuthorizeTransactionGetDTO> createTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    return this.createTransactionInternal(transactionCreateDTO)
        .map(at -> modelMapper.map(at, AuthorizeTransactionGetDTO.class));
  }

  Optional<AuthorizeTransaction> createTransactionInternal(
      TransactionCreateDTO transactionCreateDTO) {
    final var authorizeTransaction =
        modelMapper.map(transactionCreateDTO, AuthorizeTransaction.class);
    if (!validator.validate(authorizeTransaction).isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(authorizeTransactionRepository.save(authorizeTransaction));
  }

  @Override
  public Page<AuthorizeTransactionGetDTO> getTransactions(Pageable pageable) {
    return authorizeTransactionRepository
        .findAll(pageable)
        .map(at -> modelMapper.map(at, AuthorizeTransactionGetDTO.class));
  }

  @Override
  public Optional<AuthorizeTransactionGetDTO> getTransaction(UUID transactionId) {
    return authorizeTransactionRepository
        .findById(transactionId)
        .map(at -> modelMapper.map(at, AuthorizeTransactionGetDTO.class));
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    final var authTransaction = authorizeTransactionRepository.findById(transactionId);
    if (authTransaction.isEmpty()) {
      return false;
    }
    authorizeTransactionRepository.delete(authTransaction.get());
    return true;
  }
}
