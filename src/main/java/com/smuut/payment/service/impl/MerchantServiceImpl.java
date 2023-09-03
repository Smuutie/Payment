package com.smuut.payment.service.impl;

import com.smuut.payment.dto.MerchantCreateDTO;
import com.smuut.payment.dto.MerchantGetDTO;
import com.smuut.payment.dto.MerchantUpdateDTO;
import com.smuut.payment.entity.Merchant;
import com.smuut.payment.repository.MerchantRepository;
import com.smuut.payment.service.MerchantService;
import jakarta.validation.Validator;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

  private final MerchantRepository merchantRepository;

  private final Validator validator;

  private final ModelMapper modelMapper;

  @Override
  public Optional<MerchantGetDTO> createMerchant(MerchantCreateDTO merchantCreateDTO) {
    final var merchantEntity = modelMapper.map(merchantCreateDTO, Merchant.class);
    if (!validator.validate(merchantEntity).isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(merchantRepository.save(merchantEntity))
        .map(m -> modelMapper.map(m, MerchantGetDTO.class));
  }

  @Override
  public Page<MerchantGetDTO> getMerchants(Pageable pageable) {
    return merchantRepository.findAll(pageable).map(m -> modelMapper.map(m, MerchantGetDTO.class));
  }

  @Override
  public Optional<MerchantGetDTO> getMerchant(UUID merchantId) {
    return merchantRepository
        .findById(merchantId)
        .map(m -> modelMapper.map(m, MerchantGetDTO.class));
  }

  @Override
  public boolean deleteMerchant(UUID merchantId) {
    final var optionalMerchant = merchantRepository.findById(merchantId);
    if (optionalMerchant.isEmpty() || !optionalMerchant.get().getTransactions().isEmpty()) {
      return false;
    }
    merchantRepository.delete(optionalMerchant.get());
    return true;
  }

  @Override
  public Optional<MerchantGetDTO> updateMerchant(
      UUID merchantId, MerchantUpdateDTO merchantUpdateDTO) {
    return merchantRepository
        .findById(merchantId)
        .map(
            merchant -> {
              modelMapper.map(merchantUpdateDTO, merchant);
              return modelMapper.map(merchantRepository.save(merchant), MerchantGetDTO.class);
            });
  }
}
