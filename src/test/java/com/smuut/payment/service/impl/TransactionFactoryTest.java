package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.smuut.payment.dto.TransactionCreateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionFactoryTest {

  @InjectMocks private TransactionFactory transactionFactory;

  @Test
  public void whenCreateTransactionCalled_ThenReturnCorrectTransactionType() {
    final var newTransaction =
        transactionFactory.createTransaction(TransactionCreateDTO.builder().build());

    assertTrue(newTransaction.isPresent());
  }
}
