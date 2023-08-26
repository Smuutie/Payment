package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.*;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionFactoryTest {

  @Mock private AuthorizeTransactionService authorizeTransactionService;

  @Mock private ChargeTransactionService chargeTransactionService;

  @Mock private RefundTransactionService refundTransactionService;

  @Mock private ReversalTransactionService reversalTransactionService;

  @InjectMocks private TransactionFactory transactionFactory;

  @Test
  public void whenCreateTransactionWithAuthorizeTypeCalled_ThenReturnCorrectTransactionType() {
    final var transactionCreateDTO =
        TransactionCreateDTO.builder().type(TransactionType.AUTHORIZE).build();
    when(authorizeTransactionService.createTransactionInternal(transactionCreateDTO))
        .thenReturn(Optional.of(new AuthorizeTransaction()));

    final var newTransaction = transactionFactory.createTransaction(transactionCreateDTO);

    assertTrue(newTransaction.isPresent());
    verify(authorizeTransactionService, times(1)).createTransactionInternal(transactionCreateDTO);
  }

  @Test
  public void whenCreateTransactionWithChargeTypeCalled_ThenReturnCorrectTransactionType() {
    final var transactionCreateDTO =
        TransactionCreateDTO.builder().type(TransactionType.CHARGE).build();
    when(chargeTransactionService.createTransactionInternal(transactionCreateDTO))
        .thenReturn(Optional.of(new ChargeTransaction()));

    final var newTransaction = transactionFactory.createTransaction(transactionCreateDTO);

    assertTrue(newTransaction.isPresent());
    verify(chargeTransactionService, times(1)).createTransactionInternal(transactionCreateDTO);
  }

  @Test
  public void whenCreateTransactionWithRefundTypeCalled_ThenReturnCorrectTransactionType() {
    final var transactionCreateDTO =
        TransactionCreateDTO.builder().type(TransactionType.REFUND).build();
    when(refundTransactionService.createTransactionInternal(transactionCreateDTO))
        .thenReturn(Optional.of(new RefundTransaction()));

    final var newTransaction = transactionFactory.createTransaction(transactionCreateDTO);

    assertTrue(newTransaction.isPresent());
    verify(refundTransactionService, times(1)).createTransactionInternal(transactionCreateDTO);
  }

  @Test
  public void whenCreateTransactionWithReversalTypeCalled_ThenReturnCorrectTransactionType() {
    final var transactionCreateDTO =
        TransactionCreateDTO.builder().type(TransactionType.REVERSAL).build();
    when(reversalTransactionService.createTransactionInternal(transactionCreateDTO))
        .thenReturn(Optional.of(new ReversalTransaction()));

    final var newTransaction = transactionFactory.createTransaction(transactionCreateDTO);

    assertTrue(newTransaction.isPresent());
    verify(reversalTransactionService, times(1)).createTransactionInternal(transactionCreateDTO);
  }
}
