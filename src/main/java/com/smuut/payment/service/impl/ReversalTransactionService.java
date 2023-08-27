package com.smuut.payment.service.impl;

import com.smuut.payment.dto.ReversalTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.ReversalTransaction;
import com.smuut.payment.entity.TransactionStatus;
import com.smuut.payment.repository.ReversalTransactionRepository;
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
public class ReversalTransactionService implements TransactionService<ReversalTransactionGetDTO> {

  private final ReversalTransactionRepository reversalTransactionRepository;

  private final Validator validator;

  private final ModelMapper modelMapper;

  @Override
  public Optional<ReversalTransactionGetDTO> createTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    return this.createTransactionInternal(transactionCreateDTO)
        .map(at -> modelMapper.map(at, ReversalTransactionGetDTO.class));
  }

  Optional<ReversalTransaction> createTransactionInternal(
      TransactionCreateDTO transactionCreateDTO) {
    final var reversalTransaction = modelMapper.map(transactionCreateDTO, ReversalTransaction.class);
    if (!validator.validate(reversalTransaction).isEmpty()) {
      return Optional.empty();
    }
    final var referencedAuthorizeTransaction = reversalTransaction.getAuthorizeTransaction();
    if(!referencedAuthorizeTransaction.getTransactionStatus().equals(TransactionStatus.APPROVED)){
      reversalTransaction.setTransactionStatus(TransactionStatus.ERROR);
    } else {
      reversalTransaction.setTransactionStatus(TransactionStatus.APPROVED);
      referencedAuthorizeTransaction.setTransactionStatus(TransactionStatus.REVERSED);
    }
    return Optional.of(reversalTransactionRepository.save(reversalTransaction));
  }

  @Override
  public Page<ReversalTransactionGetDTO> getTransactions(Pageable pageable) {
    return reversalTransactionRepository
        .findAll(pageable)
        .map(rt -> modelMapper.map(rt, ReversalTransactionGetDTO.class));
  }

  @Override
  public Optional<ReversalTransactionGetDTO> getTransaction(UUID transactionId) {
    return reversalTransactionRepository
        .findById(transactionId)
        .map(rt -> modelMapper.map(rt, ReversalTransactionGetDTO.class));
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    final var reversalTransaction = reversalTransactionRepository.findById(transactionId);
    if (reversalTransaction.isEmpty()) {
      return false;
    }
    reversalTransactionRepository.delete(reversalTransaction.get());
    return true;
  }
}
