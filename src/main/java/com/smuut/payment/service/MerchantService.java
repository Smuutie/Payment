package com.smuut.payment.service;

import com.smuut.payment.dto.MerchantCreateDTO;
import com.smuut.payment.dto.MerchantGetDTO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MerchantService {

  Optional<MerchantGetDTO> createMerchant(MerchantCreateDTO merchantCreateDTO);

  Page<MerchantGetDTO> getMerchants(Pageable pageable);

  Optional<MerchantGetDTO> getMerchant(UUID merchantId);

  boolean deleteMerchant(UUID merchantId);
}
