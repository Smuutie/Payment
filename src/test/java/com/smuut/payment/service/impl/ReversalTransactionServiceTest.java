package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.ReversalTransaction;
import com.smuut.payment.repository.ReversalTransactionRepository;
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
public class ReversalTransactionServiceTest {

  @Mock private ReversalTransactionRepository reversalTransactionRepository;

  @InjectMocks private ReversalTransactionService reversalTransactionService;

  @Test
  public void whenCreateReversalTransactionIsCalled_ThenReturnReversalTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(ReversalTransaction.class);
    Mockito.when(reversalTransactionRepository.save(any())).thenReturn(transactionEntity);

    final var savedTransaction = reversalTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    Mockito.verify(reversalTransactionRepository, Mockito.times(1)).save(any());
  }

  @Test
  public void whenSearchReversalTransactionsIsCalled_ThenReturnListOfReversalTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(ReversalTransaction.class);
    Mockito.when(reversalTransactionRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(transactionEntity)));

    final var transactionsList = reversalTransactionService.getTransactions(Pageable.unpaged());

    assertFalse(transactionsList.isEmpty());
    assertEquals(1, transactionsList.size());
    Mockito.verify(reversalTransactionRepository, Mockito.times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void whenGetReversalTransactionIsCalled_ThenReturnReversalTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(ReversalTransaction.class);
    Mockito.when(reversalTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));

    final var transaction = reversalTransactionService.getTransaction(UUID.randomUUID());

    assertTrue(transaction.isPresent());
    Mockito.verify(reversalTransactionRepository, Mockito.times(1)).findById(any(UUID.class));
  }

  @Test
  public void whenDeleteReversalTransactionIsCalled_ThenReturnTrue() {
    final var transactionEntity = Mockito.mock(ReversalTransaction.class);
    Mockito.when(reversalTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));

    final var deleted = reversalTransactionService.deleteTransaction(UUID.randomUUID());

    assertTrue(deleted);
    Mockito.verify(reversalTransactionRepository, Mockito.times(1)).delete(eq(transactionEntity));
  }
}
