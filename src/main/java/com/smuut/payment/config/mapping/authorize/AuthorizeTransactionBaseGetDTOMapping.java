package com.smuut.payment.config.mapping.authorize;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.TransactionGetDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeTransactionBaseGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    modelMapper.typeMap(AuthorizeTransaction.class, TransactionGetDTO.class).implicitMappings();
  }
}
