package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.smuut.payment.dto.*;
import com.smuut.payment.entity.Merchant;
import com.smuut.payment.repository.MerchantRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BaseTransactionServiceTest {

  @Mock private TransactionFactory transactionFactory;

  @Mock private MerchantRepository merchantRepository;

  @InjectMocks private BaseTransactionService baseTransactionService;

  @Test
  public void whenCreateBaseTransactionIsCalled_ThenReturnAuthorizeTransactionGetDTO() {
    final var createTransactionDTO =
        TransactionCreateDTO.builder().merchantId(UUID.randomUUID()).build();
    final var merchant = new Merchant();
    merchant.setActive(true);
    final var transactionEntity = Mockito.mock(AuthorizeTransactionGetDTO.class);
    when(merchantRepository.findById(any())).thenReturn(Optional.of(merchant));
    when(transactionFactory.createTransaction(eq(createTransactionDTO)))
        .thenReturn(Optional.of(transactionEntity));

    final var savedTransaction = baseTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    assertTrue(savedTransaction.get() instanceof AuthorizeTransactionGetDTO);
  }

  @Test
  public void whenCreateBaseTransactionIsCalled_ThenReturnChargeTransactionGetDTO() {
    final var createTransactionDTO =
        TransactionCreateDTO.builder().merchantId(UUID.randomUUID()).build();
    final var merchant = new Merchant();
    merchant.setActive(true);
    final var transactionEntity = Mockito.mock(ChargeTransactionGetDTO.class);
    when(merchantRepository.findById(any())).thenReturn(Optional.of(merchant));
    when(transactionFactory.createTransaction(eq(createTransactionDTO)))
        .thenReturn(Optional.of(transactionEntity));

    final var savedTransaction = baseTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    assertTrue(savedTransaction.get() instanceof ChargeTransactionGetDTO);
  }

  @Test
  public void whenCreateBaseTransactionIsCalled_ThenReturnRefundTransactionGetDTO() {
    final var createTransactionDTO =
        TransactionCreateDTO.builder().merchantId(UUID.randomUUID()).build();
    final var merchant = new Merchant();
    merchant.setActive(true);
    final var transactionEntity = Mockito.mock(RefundTransactionGetDTO.class);
    when(merchantRepository.findById(any())).thenReturn(Optional.of(merchant));
    when(transactionFactory.createTransaction(eq(createTransactionDTO)))
        .thenReturn(Optional.of(transactionEntity));

    final var savedTransaction = baseTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    assertTrue(savedTransaction.get() instanceof RefundTransactionGetDTO);
  }

  @Test
  public void whenCreateBaseTransactionIsCalled_ThenReturnReversalTransactionGetDTO() {
    final var createTransactionDTO =
        TransactionCreateDTO.builder().merchantId(UUID.randomUUID()).build();
    final var merchant = new Merchant();
    merchant.setActive(true);
    final var transactionEntity = Mockito.mock(ReversalTransactionGetDTO.class);
    when(merchantRepository.findById(any())).thenReturn(Optional.of(merchant));
    when(transactionFactory.createTransaction(eq(createTransactionDTO)))
        .thenReturn(Optional.of(transactionEntity));

    final var savedTransaction = baseTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    assertTrue(savedTransaction.get() instanceof ReversalTransactionGetDTO);
  }
}
