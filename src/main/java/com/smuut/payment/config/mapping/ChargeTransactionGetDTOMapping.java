package com.smuut.payment.config.mapping;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.ChargeTransactionGetDTO;
import com.smuut.payment.entity.ChargeTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ChargeTransactionGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typeMap = modelMapper.typeMap(ChargeTransaction.class, ChargeTransactionGetDTO.class);

    typeMap
        .addMappings(
            mapping ->
                mapping.map(
                    ChargeTransaction::getAuthorizeTransactionId,
                    ChargeTransactionGetDTO::setAuthorizeTransactionId))
        .implicitMappings();
  }
}
