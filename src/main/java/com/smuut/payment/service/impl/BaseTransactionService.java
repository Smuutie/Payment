package com.smuut.payment.service.impl;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.dto.TransactionGetDTO;
import com.smuut.payment.entity.Merchant;
import com.smuut.payment.repository.BaseTransactionRepository;
import com.smuut.payment.repository.MerchantRepository;
import com.smuut.payment.service.TransactionService;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseTransactionService implements TransactionService<TransactionGetDTO> {

  private final TransactionFactory transactionFactory;

  private final BaseTransactionRepository baseTransactionRepository;

  private final ModelMapper modelMapper;

  private final MerchantRepository merchantRepository;

  @Override
  public Optional<TransactionGetDTO> createTransaction(TransactionCreateDTO transactionCreateDTO) {
    final var isMerchantActive =
        merchantRepository.findById(transactionCreateDTO.getMerchantId()).map(Merchant::isActive);
    if (isMerchantActive.isEmpty() || !isMerchantActive.get()) {
      return Optional.empty();
    }
    return transactionFactory.createTransaction(transactionCreateDTO);
  }

  @Override
  public Page<TransactionGetDTO> getTransactions(Pageable pageable) {
    return baseTransactionRepository
        .findAll(pageable)
        .map(bt -> modelMapper.map(bt, TransactionGetDTO.class));
  }

  @Override
  public Optional<TransactionGetDTO> getTransaction(UUID transactionId) {
    return baseTransactionRepository
        .findById(transactionId)
        .map(bt -> modelMapper.map(bt, TransactionGetDTO.class));
  }

  @Override
  public boolean deleteTransaction(UUID transactionId) {
    final var baseTransaction = baseTransactionRepository.findById(transactionId);
    if (baseTransaction.isEmpty()) {
      return false;
    }
    baseTransactionRepository.delete(baseTransaction.get());
    return true;
  }
}
