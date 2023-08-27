package com.smuut.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.smuut.payment.dto.MerchantCreateDTO;
import com.smuut.payment.dto.MerchantGetDTO;
import com.smuut.payment.entity.BaseTransaction;
import com.smuut.payment.entity.Merchant;
import com.smuut.payment.repository.MerchantRepository;
import jakarta.validation.Validator;
import java.util.*;
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
public class MerchantServiceTest {

  @Mock private MerchantRepository merchantRepository;

  @Mock private Validator validator;

  @Mock private ModelMapper modelMapper;

  @InjectMocks private MerchantServiceImpl merchantService;

  @Test
  public void whenCreateMerchantIsCalled_ThenReturnMerchantGetDTO() {
    final var createMerchantDTO = MerchantCreateDTO.builder().build();
    final var merchantEntity = Mockito.mock(Merchant.class);
    Mockito.when(merchantRepository.save(any())).thenReturn(merchantEntity);
    when(modelMapper.map(createMerchantDTO, Merchant.class)).thenReturn(merchantEntity);
    when(validator.validate(merchantEntity)).thenReturn(new HashSet<>());
    when(merchantRepository.save(any())).thenReturn(merchantEntity);
    when(modelMapper.map(merchantEntity, MerchantGetDTO.class)).thenReturn(new MerchantGetDTO());

    final var savedMerchant = merchantService.createMerchant(createMerchantDTO);

    assertTrue(savedMerchant.isPresent());
    Mockito.verify(merchantRepository, Mockito.times(1)).save(any());
  }

  @Test
  public void whenSearchMerchantIsCalled_ThenReturnListOfMerchantGetDTO() {
    final var merchantEntity = Mockito.mock(Merchant.class);
    Mockito.when(merchantRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(merchantEntity)));
    when(modelMapper.map(any(Merchant.class), eq(MerchantGetDTO.class)))
        .thenReturn(new MerchantGetDTO());

    final var merchantsList = merchantService.getMerchants(Pageable.unpaged());

    assertFalse(merchantsList.isEmpty());
    assertEquals(1, merchantsList.getNumberOfElements());
    Mockito.verify(merchantRepository, Mockito.times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void whenGetMerchantIsCalled_ThenReturnMerchantGetDTO() {
    final var merchantEntity = Mockito.mock(Merchant.class);
    Mockito.when(merchantRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(merchantEntity));
    when(modelMapper.map(any(Merchant.class), eq(MerchantGetDTO.class)))
        .thenReturn(new MerchantGetDTO());

    final var merchant = merchantService.getMerchant(UUID.randomUUID());

    assertTrue(merchant.isPresent());
    Mockito.verify(merchantRepository, Mockito.times(1)).findById(any(UUID.class));
  }

  @Test
  public void whenDeleteMerchantIsCalled_ThenReturnTrue() {
    final var merchantEntity = Mockito.mock(Merchant.class);
    Mockito.when(merchantRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(merchantEntity));

    final var deleted = merchantService.deleteMerchant(UUID.randomUUID());

    assertTrue(deleted);
    Mockito.verify(merchantRepository, Mockito.times(1)).delete(eq(merchantEntity));
  }

  @Test
  public void whenDeleteMerchantWithTransactionsIsCalled_ThenReturnFalse() {
    final var merchantEntity = Mockito.mock(Merchant.class);
    when(merchantEntity.getTransactions()).thenReturn(Set.of(new BaseTransaction()));
    Mockito.when(merchantRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(merchantEntity));

    final var deleted = merchantService.deleteMerchant(UUID.randomUUID());

    assertFalse(deleted);
    Mockito.verify(merchantRepository, Mockito.times(0)).delete(eq(merchantEntity));
  }
}
