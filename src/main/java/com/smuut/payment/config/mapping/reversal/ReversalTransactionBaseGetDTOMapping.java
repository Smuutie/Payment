package com.smuut.payment.config.mapping.reversal;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.TransactionGetDTO;
import com.smuut.payment.entity.ReversalTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReversalTransactionBaseGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typemap =
        modelMapper.typeMap(ReversalTransaction.class, TransactionGetDTO.class).implicitMappings();
  }
}
