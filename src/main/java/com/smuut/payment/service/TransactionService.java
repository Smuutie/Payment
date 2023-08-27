package com.smuut.payment.service;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.dto.TransactionGetDTO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService<TG extends TransactionGetDTO> {

  Optional<TG> createTransaction(TransactionCreateDTO transactionCreateDTO);

  Page<TG> getTransactions(Pageable pageable);

  Optional<TG> getTransaction(UUID transactionId);

  boolean deleteTransaction(UUID transactionId);
}
