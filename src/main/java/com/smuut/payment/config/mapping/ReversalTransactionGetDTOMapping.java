package com.smuut.payment.config.mapping;

import com.smuut.payment.dto.ReversalTransactionGetDTO;
import com.smuut.payment.entity.ReversalTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReversalTransactionGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    modelMapper.typeMap(ReversalTransaction.class, ReversalTransactionGetDTO.class);
  }
}
