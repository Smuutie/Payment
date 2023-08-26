package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.ChargeTransaction;
import com.smuut.payment.repository.ChargeTransactionRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class ChargeTransactionServiceTest {

  @Mock private ChargeTransactionRepository chargeTransactionRepository;

  @InjectMocks private ChargeTransactionService chargeTransactionService;

  @Test
  public void whenCreateChargeTransactionIsCalled_ThenReturnChargeTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(ChargeTransaction.class);
    Mockito.when(chargeTransactionRepository.save(any())).thenReturn(transactionEntity);

    final var savedTransaction = chargeTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    Mockito.verify(chargeTransactionRepository, Mockito.times(1)).save(any());
  }

  @Test
  public void whenSearchChargeTransactionsIsCalled_ThenReturnListOfChargeTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(ChargeTransaction.class);
    Mockito.when(chargeTransactionRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(transactionEntity)));

    final var transactionsList = chargeTransactionService.getTransactions(Pageable.unpaged());

    assertFalse(transactionsList.isEmpty());
    assertEquals(1, transactionsList.size());
    Mockito.verify(chargeTransactionRepository, Mockito.times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void whenGetChargeTransactionIsCalled_ThenReturnChargeTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(ChargeTransaction.class);
    Mockito.when(chargeTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));

    final var transaction = chargeTransactionService.getTransaction(UUID.randomUUID());

    assertTrue(transaction.isPresent());
    Mockito.verify(chargeTransactionRepository, Mockito.times(1)).findById(any(UUID.class));
  }

  @Test
  public void whenDeleteChargeTransactionIsCalled_ThenReturnTrue() {
    final var transactionEntity = Mockito.mock(ChargeTransaction.class);
    Mockito.when(chargeTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));

    final var deleted = chargeTransactionService.deleteTransaction(UUID.randomUUID());

    assertTrue(deleted);
    Mockito.verify(chargeTransactionRepository, Mockito.times(1)).delete(eq(transactionEntity));
  }
}
