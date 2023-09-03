package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.smuut.payment.dto.ReversalTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import com.smuut.payment.entity.ReversalTransaction;
import com.smuut.payment.entity.TransactionStatus;
import com.smuut.payment.repository.AuthorizeTransactionRepository;
import com.smuut.payment.repository.ReversalTransactionRepository;
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
public class ReversalTransactionServiceTest {

  @Mock private ReversalTransactionRepository reversalTransactionRepository;

  @Mock private AuthorizeTransactionRepository authorizeTransactionRepository;

  @Mock private Validator validator;

  @Mock private ModelMapper modelMapper;

  @InjectMocks private ReversalTransactionService reversalTransactionService;

  @Test
  public void whenCreateReversalTransactionIsCalled_ThenReturnReversalTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = new ReversalTransaction();
    final var authTransaction = new AuthorizeTransaction();
    authTransaction.setTransactionStatus(TransactionStatus.APPROVED);
    transactionEntity.setAuthorizeTransactionId(authTransaction.getId());
    when(authorizeTransactionRepository.findById(any())).thenReturn(Optional.of(authTransaction));
    Mockito.when(reversalTransactionRepository.save(any())).thenReturn(transactionEntity);
    when(modelMapper.map(createTransactionDTO, ReversalTransaction.class))
        .thenReturn(transactionEntity);
    when(validator.validate(transactionEntity)).thenReturn(new HashSet<>());
    when(reversalTransactionRepository.save(any())).thenReturn(transactionEntity);
    when(modelMapper.map(transactionEntity, ReversalTransactionGetDTO.class))
        .thenReturn(new ReversalTransactionGetDTO());

    final var savedTransaction = reversalTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    Mockito.verify(reversalTransactionRepository, Mockito.times(1)).save(any());
  }

  @Test
  public void whenSearchReversalTransactionsIsCalled_ThenReturnListOfReversalTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(ReversalTransaction.class);
    Mockito.when(reversalTransactionRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(transactionEntity)));
    when(modelMapper.map(any(ReversalTransaction.class), eq(ReversalTransactionGetDTO.class)))
        .thenReturn(new ReversalTransactionGetDTO());

    final var transactionsList = reversalTransactionService.getTransactions(Pageable.unpaged());

    assertFalse(transactionsList.isEmpty());
    assertEquals(1, transactionsList.getNumberOfElements());
    Mockito.verify(reversalTransactionRepository, Mockito.times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void whenGetReversalTransactionIsCalled_ThenReturnReversalTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(ReversalTransaction.class);
    Mockito.when(reversalTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));
    when(modelMapper.map(any(ReversalTransaction.class), eq(ReversalTransactionGetDTO.class)))
        .thenReturn(new ReversalTransactionGetDTO());

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
