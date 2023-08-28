package com.smuut.payment.config.mapping.merchant;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.MerchantUpdateDTO;
import com.smuut.payment.entity.Merchant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MerchantUpdateDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap = modelMapper.typeMap(MerchantUpdateDTO.class, Merchant.class);

    typemap
        .addMappings(
            context -> {
              context.skip(Merchant::setId);
              context.skip(Merchant::setTransactions);
              context.skip(Merchant::setTotalTransactionSum);
              context.skip(Merchant::setCreatedAt);
              context.map(MerchantUpdateDTO::getName, Merchant::setName);
              context.map(MerchantUpdateDTO::getDescription, Merchant::setDescription);
              context.map(MerchantUpdateDTO::getEmail, Merchant::setEmail);
            })
        .implicitMappings();
  }
}
