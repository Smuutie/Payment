package com.smuut.payment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.smuut.payment.dto.MerchantCreateDTO;
import com.smuut.payment.entity.Merchant;
import com.smuut.payment.repository.MerchantRepository;
import com.smuut.payment.service.impl.MerchantServiceImpl;
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
public class MerchantServiceTest {

  @Mock private MerchantRepository merchantRepository;

  @InjectMocks private MerchantServiceImpl merchantService;

  @Test
  public void whenCreateMerchantIsCalled_ThenReturnMerchantGetDTO() {
    final var createMerchantDTO = MerchantCreateDTO.builder().build();
    final var merchantEntity = Mockito.mock(Merchant.class);
    Mockito.when(merchantRepository.save(any())).thenReturn(merchantEntity);

    final var savedMerchant = merchantService.createMerchant(createMerchantDTO);

    assertTrue(savedMerchant.isPresent());
    Mockito.verify(merchantRepository, Mockito.times(1)).save(any());
  }

  @Test
  public void whenSearchMerchantIsCalled_ThenReturnListOfMerchantGetDTO() {
    final var merchantEntity = Mockito.mock(Merchant.class);
    Mockito.when(merchantRepository.findAll(any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(merchantEntity)));

    final var merchantsList = merchantService.getMerchants(Pageable.unpaged());

    assertFalse(merchantsList.isEmpty());
    assertEquals(1, merchantsList.size());
    Mockito.verify(merchantRepository, Mockito.times(1)).findAll(any(Pageable.class));
  }

  @Test
  public void whenGetMerchantIsCalled_ThenReturnMerchantGetDTO() {
    final var merchantEntity = Mockito.mock(Merchant.class);
    Mockito.when(merchantRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(merchantEntity));

    final var merchant = merchantService.getMerchant(UUID.randomUUID());

    assertTrue(merchant.isPresent());
    Mockito.verify(merchantRepository, Mockito.times(1)).findById(any(UUID.class));
  }

  @Test
  public void whenDeleteMerchantIsCalled_ThenReturnMerchantGetDTO() {
    final var merchantEntity = Mockito.mock(Merchant.class);
    Mockito.when(merchantRepository.findById(any(UUID.class)))
        .thenReturn(Optional.of(merchantEntity));

    final var deleted = merchantService.deleteMerchant(UUID.randomUUID());

    assertTrue(deleted);
    Mockito.verify(merchantRepository, Mockito.times(1)).delete(eq(merchantEntity));
  }
}
