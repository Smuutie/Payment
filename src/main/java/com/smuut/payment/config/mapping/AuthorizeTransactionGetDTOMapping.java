package com.smuut.payment.config.mapping;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.AuthorizeTransactionGetDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeTransactionGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    modelMapper
        .typeMap(AuthorizeTransaction.class, AuthorizeTransactionGetDTO.class)
        .implicitMappings();
    ;
  }
}
