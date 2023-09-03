package com.smuut.payment.service.impl;

import com.smuut.payment.dto.ChargeTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.ChargeTransaction;
import com.smuut.payment.entity.TransactionStatus;
import com.smuut.payment.repository.AuthorizeTransactionRepository;
import com.smuut.payment.repository.ChargeTransactionRepository;
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
public class ChargeTransactionService implements TransactionService<ChargeTransactionGetDTO> {

  private final ChargeTransactionRepository chargeTransactionRepository;

  private final Validator validator;

  private final ModelMapper modelMapper;

  private final AuthorizeTransactionRepository authorizeTransactionRepository;

  @Override
  public Optional<ChargeTransactionGetDTO> createTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    return this.createTransactionInternal(transactionCreateDTO)
        .map(at -> modelMapper.map(at, ChargeTransactionGetDTO.class));
  }

  Optional<ChargeTransaction> createTransactionInternal(TransactionCreateDTO transactionCreateDTO) {
    final var chargeTransaction = modelMapper.map(transactionCreateDTO, ChargeTransaction.class);
    if (!validator.validate(chargeTransaction).isEmpty()) {
      return Optional.empty();
    }
    final var authorizeTransaction =
        authorizeTransactionRepository.findById(chargeTransaction.getAuthorizeTransactionId());

    if (authorizeTransaction.isEmpty()) {
      return Optional.empty();
    }

    if (!TransactionStatus.APPROVED.equals(authorizeTransaction.get().getTransactionStatus())) {
      chargeTransaction.setTransactionStatus(TransactionStatus.ERROR);
    } else {
      chargeTransaction.setTransactionStatus(TransactionStatus.APPROVED);
    }
    return Optional.of(chargeTransactionRepository.save(chargeTransaction));
  }

  @Override
  public Page<ChargeTransactionGetDTO> getTransactions(Pageable pageable) {
    return chargeTransactionRepository
        .findAll(pageable)
        .map(ct -> modelMapper.map(ct, ChargeTransactionGetDTO.class));
  }

  @Override
  public Optional<ChargeTransactionGetDTO> getTransaction(UUID transactionId) {
    return chargeTransactionRepository
        .findById(transactionId)
        .map(ct -> modelMapper.map(ct, ChargeTransactionGetDTO.class));
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    final var chargeTransaction = chargeTransactionRepository.findById(transactionId);
    if (chargeTransaction.isEmpty()) {
      return false;
    }
    chargeTransactionRepository.delete(chargeTransaction.get());
    return true;
  }
}
