package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.smuut.payment.dto.RefundTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.RefundTransaction;
import com.smuut.payment.repository.RefundTransactionRepository;
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
public class RefundTransactionServiceTest {

  @Mock private RefundTransactionRepository refundTransactionRepository;

  @Mock private Validator validator;

  @Mock private ModelMapper modelMapper;

  @InjectMocks private RefundTransactionService refundTransactionService;

  @Test
  public void whenCreateRefundTransactionIsCalled_ThenReturnRefundTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(RefundTransaction.class);
    Mockito.when(refundTransactionRepository.save(any())).thenReturn(transactionEntity);
    when(modelMapper.map(createTransactionDTO, RefundTransaction.class))
        .thenReturn(transactionEntity);
    when(validator.validate(transactionEntity)).thenReturn(new HashSet<>());
    when(refundTransactionRepository.save(any())).thenReturn(transactionEntity);
    when(modelMapper.map(transactionEntity, RefundTransactionGetDTO.class))
        .thenReturn(new RefundTransactionGetDTO());

    final var savedTransaction = refundTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    Mockito.verify(refundTransactionRepository, Mockito.times(1)).save(any());
  }

  @Test
  public void whenSearchRefundTransactionsIsCalled_ThenReturnListOfRefundTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(RefundTransaction.class);
    Mockito.when(refundTransactionRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(transactionEntity)));
    when(modelMapper.map(any(RefundTransaction.class), eq(RefundTransactionGetDTO.class)))
        .thenReturn(new RefundTransactionGetDTO());

    final var transactionsList = refundTransactionService.getTransactions(Pageable.unpaged());

    assertFalse(transactionsList.isEmpty());
    assertEquals(1, transactionsList.getNumberOfElements());
    Mockito.verify(refundTransactionRepository, Mockito.times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void whenGetRefundTransactionIsCalled_ThenReturnRefundTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(RefundTransaction.class);
    Mockito.when(refundTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));
    when(modelMapper.map(any(RefundTransaction.class), eq(RefundTransactionGetDTO.class)))
        .thenReturn(new RefundTransactionGetDTO());

    final var transaction = refundTransactionService.getTransaction(UUID.randomUUID());

    assertTrue(transaction.isPresent());
    Mockito.verify(refundTransactionRepository, Mockito.times(1)).findById(any(UUID.class));
  }

  @Test
  public void whenDeleteRefundTransactionIsCalled_ThenReturnTrue() {
    final var transactionEntity = Mockito.mock(RefundTransaction.class);
    Mockito.when(refundTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));

    final var deleted = refundTransactionService.deleteTransaction(UUID.randomUUID());

    assertTrue(deleted);
    Mockito.verify(refundTransactionRepository, Mockito.times(1)).delete(eq(transactionEntity));
  }
}
