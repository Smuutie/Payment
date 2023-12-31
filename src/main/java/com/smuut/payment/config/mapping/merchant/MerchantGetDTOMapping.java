package com.smuut.payment.config.mapping.merchant;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.MerchantGetDTO;
import com.smuut.payment.entity.Merchant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MerchantGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    modelMapper.typeMap(Merchant.class, MerchantGetDTO.class).implicitMappings();
  }
}
