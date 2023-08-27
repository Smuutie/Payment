package com.smuut.payment.service.impl;

import com.smuut.payment.dto.RefundTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.RefundTransaction;
import com.smuut.payment.entity.TransactionStatus;
import com.smuut.payment.repository.RefundTransactionRepository;
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
public class RefundTransactionService implements TransactionService<RefundTransactionGetDTO> {

  private final RefundTransactionRepository refundTransactionRepository;

  private final Validator validator;

  private final ModelMapper modelMapper;

  @Override
  public Optional<RefundTransactionGetDTO> createTransaction(
      TransactionCreateDTO transactionCreateDTO) {
    return this.createTransactionInternal(transactionCreateDTO)
        .map(at -> modelMapper.map(at, RefundTransactionGetDTO.class));
  }

  Optional<RefundTransaction> createTransactionInternal(TransactionCreateDTO transactionCreateDTO) {
    final var refundTransaction = modelMapper.map(transactionCreateDTO, RefundTransaction.class);
    if (!validator.validate(refundTransaction).isEmpty()) {
      return Optional.empty();
    }
    final var referencedChargeTransaction = refundTransaction.getChargeTransaction();
    if (!referencedChargeTransaction.getTransactionStatus().equals(TransactionStatus.APPROVED)) {
      refundTransaction.setTransactionStatus(TransactionStatus.ERROR);
    } else {
      refundTransaction.setTransactionStatus(TransactionStatus.APPROVED);
      referencedChargeTransaction.setTransactionStatus(TransactionStatus.REFUNDED);
      referencedChargeTransaction
          .getAuthorizeTransaction()
          .setTransactionStatus(TransactionStatus.REFUNDED);
    }
    return Optional.of(refundTransactionRepository.save(refundTransaction));
  }

  @Override
  public Page<RefundTransactionGetDTO> getTransactions(Pageable pageable) {
    return refundTransactionRepository
        .findAll(pageable)
        .map(rt -> modelMapper.map(rt, RefundTransactionGetDTO.class));
  }

  @Override
  public Optional<RefundTransactionGetDTO> getTransaction(UUID transactionId) {
    return refundTransactionRepository
        .findById(transactionId)
        .map(rt -> modelMapper.map(rt, RefundTransactionGetDTO.class));
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    final var refundTransaction = refundTransactionRepository.findById(transactionId);
    if (refundTransaction.isEmpty()) {
      return false;
    }
    refundTransactionRepository.delete(refundTransaction.get());
    return true;
  }
}
