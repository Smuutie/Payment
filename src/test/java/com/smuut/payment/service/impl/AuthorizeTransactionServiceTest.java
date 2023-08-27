package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.smuut.payment.dto.AuthorizeTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import com.smuut.payment.repository.AuthorizeTransactionRepository;
import jakarta.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class AuthorizeTransactionServiceTest {

  @Mock private AuthorizeTransactionRepository authorizeTransactionRepository;

  @Mock private Validator validator;

  @Mock private ModelMapper modelMapper;

  @InjectMocks private AuthorizeTransactionService authorizeTransactionService;

  @Test
  public void whenCreateAuthorizeTransactionIsCalled_ThenReturnAuthorizeTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(AuthorizeTransaction.class);
    when(modelMapper.map(createTransactionDTO, AuthorizeTransaction.class))
        .thenReturn(transactionEntity);
    when(validator.validate(transactionEntity)).thenReturn(new HashSet<>());
    when(authorizeTransactionRepository.save(any())).thenReturn(transactionEntity);
    when(modelMapper.map(transactionEntity, AuthorizeTransactionGetDTO.class))
        .thenReturn(new AuthorizeTransactionGetDTO());

    final var savedTransaction =
        authorizeTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    Mockito.verify(authorizeTransactionRepository, Mockito.times(1)).save(any());
  }

  @Test
  public void whenSearchAuthorizeTransactionsIsCalled_ThenReturnListOfAuthorizeTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(AuthorizeTransaction.class);
    when(authorizeTransactionRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(transactionEntity)));
    when(modelMapper.map(any(AuthorizeTransaction.class), eq(AuthorizeTransactionGetDTO.class)))
        .thenReturn(new AuthorizeTransactionGetDTO());

    final var transactionsList = authorizeTransactionService.getTransactions(Pageable.unpaged());

    assertFalse(transactionsList.isEmpty());
    assertEquals(1, transactionsList.getNumberOfElements());
    Mockito.verify(authorizeTransactionRepository, Mockito.times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void whenGetAuthorizeTransactionIsCalled_ThenReturnAuthorizeTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(AuthorizeTransaction.class);
    when(authorizeTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));
    when(modelMapper.map(any(AuthorizeTransaction.class), eq(AuthorizeTransactionGetDTO.class)))
        .thenReturn(new AuthorizeTransactionGetDTO());

    final var transaction = authorizeTransactionService.getTransaction(UUID.randomUUID());

    assertTrue(transaction.isPresent());
    Mockito.verify(authorizeTransactionRepository, Mockito.times(1)).findById(any(UUID.class));
  }

  @Test
  public void whenDeleteAuthorizeTransactionIsCalled_ThenReturnTrue() {
    final var transactionEntity = Mockito.mock(AuthorizeTransaction.class);
    when(authorizeTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));

    final var deleted = authorizeTransactionService.deleteTransaction(UUID.randomUUID());

    assertTrue(deleted);
    Mockito.verify(authorizeTransactionRepository, Mockito.times(1)).delete(eq(transactionEntity));
  }
}
