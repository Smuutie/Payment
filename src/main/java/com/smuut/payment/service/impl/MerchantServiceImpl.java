package com.smuut.payment.service.impl;

import com.smuut.payment.dto.MerchantCreateDTO;
import com.smuut.payment.dto.MerchantGetDTO;
import com.smuut.payment.repository.MerchantRepository;
import com.smuut.payment.service.MerchantService;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

  private final MerchantRepository merchantRepository;

  @Override
  public Optional<MerchantGetDTO> createMerchant(MerchantCreateDTO merchantCreateDTO) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<MerchantGetDTO> getMerchants(Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<MerchantGetDTO> getMerchant(UUID merchantId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean deleteMerchant(UUID merchantId) {
    throw new UnsupportedOperationException();
  }
}
