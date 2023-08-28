package com.smuut.payment.config.mapping;

import com.smuut.payment.config.MappingConfiguration;
import com.smuut.payment.dto.ChargeTransactionGetDTO;
import com.smuut.payment.dto.TransactionGetDTO;
import com.smuut.payment.entity.AuthorizeTransaction;
import com.smuut.payment.entity.ChargeTransaction;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ChargeTransactionBaseGetDTOMapping implements MappingConfiguration {

  @Override
  public void configure(ModelMapper modelMapper) {
    final var typeMap = modelMapper.typeMap(ChargeTransaction.class, TransactionGetDTO.class).implicitMappings();



  }
}
