package com.smuut.payment.config.mapping;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.RefundTransactionGetDTO;
import com.smuut.payment.entity.RefundTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RefundTransactionGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typeMap = modelMapper.typeMap(RefundTransaction.class, RefundTransactionGetDTO.class);
    typeMap
        .addMappings(
            mapping ->
                mapping.map(
                    RefundTransaction::getChargeTransactionId,
                    RefundTransactionGetDTO::setChargeTransactionId))
        .implicitMappings();
  }
}
