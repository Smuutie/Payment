package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.smuut.payment.dto.*;
import com.smuut.payment.entity.AuthorizeTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.smuut.payment.entity.ChargeTransaction;
import com.smuut.payment.entity.RefundTransaction;
import com.smuut.payment.entity.ReversalTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class BaseTransactionServiceTest {

  @Mock private TransactionFactory transactionFactory;

  @InjectMocks private BaseTransactionService baseTransactionService;

  @Test
  public void whenCreateBaseTransactionIsCalled_ThenReturnAuthorizeTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(AuthorizeTransaction.class);
    Mockito.when(transactionFactory.createTransaction(eq(createTransactionDTO))).thenReturn(Optional.of(transactionEntity));

    final var savedTransaction =
        baseTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    assertTrue(savedTransaction.get() instanceof AuthorizeTransactionGetDTO);
  }

  @Test
  public void whenCreateBaseTransactionIsCalled_ThenReturnChargeTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(ChargeTransaction.class);
    Mockito.when(transactionFactory.createTransaction(eq(createTransactionDTO))).thenReturn(Optional.of(transactionEntity));

    final var savedTransaction =
            baseTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    assertTrue(savedTransaction.get() instanceof ChargeTransactionGetDTO);
  }

  @Test
  public void whenCreateBaseTransactionIsCalled_ThenReturnRefundTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(RefundTransaction.class);
    Mockito.when(transactionFactory.createTransaction(eq(createTransactionDTO))).thenReturn(Optional.of(transactionEntity));

    final var savedTransaction =
            baseTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    assertTrue(savedTransaction.get() instanceof RefundTransactionGetDTO);
  }

  @Test
  public void whenCreateBaseTransactionIsCalled_ThenReturnReversalTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(ReversalTransaction.class);
    Mockito.when(transactionFactory.createTransaction(eq(createTransactionDTO))).thenReturn(Optional.of(transactionEntity));

    final var savedTransaction =
            baseTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    assertTrue(savedTransaction.get() instanceof ReversalTransactionGetDTO);
  }
}
