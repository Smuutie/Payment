package com.smuut.payment.config.mapping;

import com.smuut.payment.dto.MerchantCreateDTO;
import com.smuut.payment.entity.Merchant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MerchantCreateDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap = modelMapper.typeMap(MerchantCreateDTO.class, Merchant.class);

    typemap.setProvider(
        request -> {
          final var merchant = new Merchant();
          merchant.setActive(true);
          return merchant;
        });

    typemap.addMappings(
        context -> {
          context.skip(Merchant::setId);
          context.skip(Merchant::setTransactions);
          context.skip(Merchant::setTotalTransactionSum);
          context.skip(Merchant::setCreatedAt);
        });
  }
}
