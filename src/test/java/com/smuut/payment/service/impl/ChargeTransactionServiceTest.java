package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.smuut.payment.dto.ChargeTransactionGetDTO;
import com.smuut.payment.dto.TransactionCreateDTO;
import com.smuut.payment.entity.ChargeTransaction;
import com.smuut.payment.repository.ChargeTransactionRepository;
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
public class ChargeTransactionServiceTest {

  @Mock private ChargeTransactionRepository chargeTransactionRepository;

  @Mock private Validator validator;

  @Mock private ModelMapper modelMapper;

  @InjectMocks private ChargeTransactionService chargeTransactionService;

  @Test
  public void whenCreateChargeTransactionIsCalled_ThenReturnChargeTransactionGetDTO() {
    final var createTransactionDTO = TransactionCreateDTO.builder().build();
    final var transactionEntity = Mockito.mock(ChargeTransaction.class);
    Mockito.when(chargeTransactionRepository.save(any())).thenReturn(transactionEntity);
    when(modelMapper.map(createTransactionDTO, ChargeTransaction.class))
        .thenReturn(transactionEntity);
    when(validator.validate(transactionEntity)).thenReturn(new HashSet<>());
    when(chargeTransactionRepository.save(any())).thenReturn(transactionEntity);
    when(modelMapper.map(transactionEntity, ChargeTransactionGetDTO.class))
        .thenReturn(new ChargeTransactionGetDTO());

    final var savedTransaction = chargeTransactionService.createTransaction(createTransactionDTO);

    assertTrue(savedTransaction.isPresent());
    Mockito.verify(chargeTransactionRepository, Mockito.times(1)).save(any());
  }

  @Test
  public void whenSearchChargeTransactionsIsCalled_ThenReturnListOfChargeTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(ChargeTransaction.class);
    Mockito.when(chargeTransactionRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(transactionEntity)));
    when(modelMapper.map(any(ChargeTransaction.class), eq(ChargeTransactionGetDTO.class)))
        .thenReturn(new ChargeTransactionGetDTO());

    final var transactionsList = chargeTransactionService.getTransactions(Pageable.unpaged());

    assertFalse(transactionsList.isEmpty());
    assertEquals(1, transactionsList.getNumberOfElements());
    Mockito.verify(chargeTransactionRepository, Mockito.times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void whenGetChargeTransactionIsCalled_ThenReturnChargeTransactionGetDTO() {
    final var transactionEntity = Mockito.mock(ChargeTransaction.class);
    Mockito.when(chargeTransactionRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(transactionEntity));
    when(modelMapper.map(any(ChargeTransaction.class), eq(ChargeTransactionGetDTO.class)))
        .thenReturn(new ChargeTransactionGetDTO());

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
