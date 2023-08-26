package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import com.smuut.payment.repository.AuthorizeTransactionRepository;
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
public class AuthorizeTransactionServiceTest {

  @Mock private AuthorizeTransactionRepository authorizeTransactionRepository;

  @InjectMocks private AuthorizeTransactionService authorizeTransactionService;

  @Test
  public void whenCreateAuthorizeTransactionIsCalled_ThenReturnAuthorizeTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(AuthorizeTransaction.class);
    Mockito.when(authorizeTransactionRepository.save(any())).thenReturn(transactionEntity);

    final var savedTransaction =
        authorizeTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    Mockito.verify(authorizeTransactionRepository, Mockito.times(1)).save(any());
  }

  @Test
  public void whenSearchAuthorizeTransactionsIsCalled_ThenReturnListOfAuthorizeTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(AuthorizeTransaction.class);
    Mockito.when(authorizeTransactionRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(transactionEntity)));

    final var transactionsList = authorizeTransactionService.getTransactions(Pageable.unpaged());

    assertFalse(transactionsList.isEmpty());
    assertEquals(1, transactionsList.size());
    Mockito.verify(authorizeTransactionRepository, Mockito.times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void whenGetAuthorizeTransactionIsCalled_ThenReturnAuthorizeTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(AuthorizeTransaction.class);
    Mockito.when(authorizeTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));

    final var transaction = authorizeTransactionService.getTransaction(UUID.randomUUID());

    assertTrue(transaction.isPresent());
    Mockito.verify(authorizeTransactionRepository, Mockito.times(1)).findById(any(UUID.class));
  }

  @Test
  public void whenDeleteAuthorizeTransactionIsCalled_ThenReturnTrue() {
    final var transactionEntity = Mockito.mock(AuthorizeTransaction.class);
    Mockito.when(authorizeTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));

    final var deleted = authorizeTransactionService.deleteTransaction(UUID.randomUUID());

    assertTrue(deleted);
    Mockito.verify(authorizeTransactionRepository, Mockito.times(1)).delete(eq(transactionEntity));
  }
}
