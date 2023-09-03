package com.smuut.payment.config.mapping.refund;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.TransactionGetDTO;
import com.smuut.payment.entity.RefundTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RefundTransactionBaseGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typeMap =
        modelMapper.typeMap(RefundTransaction.class, TransactionGetDTO.class).implicitMappings();
  }
}
